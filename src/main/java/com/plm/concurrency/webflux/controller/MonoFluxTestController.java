package com.plm.concurrency.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author : cwh
 * 2020/4/13 0013
 * description ：
 */
@RestController
@Slf4j
public class MonoFluxTestController {
    @GetMapping("sync")
    public String sync() {
        log.info("sync method start");
        String result = this.execute();
        log.info("sync method end");
        return result;
    }

    @GetMapping("async/mono")
    public Mono<String> asyncMono() {
        log.info("async method start");
        Mono<String> result = Mono.fromSupplier(this::execute);
        log.info("async method end");
        return result;
    }

    private String execute() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

    @GetMapping(value = "async/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> asyncFlux() {
        log.info("async method start");
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "int value：" + i;
        }));
        log.info("async method end");
        return result;
    }

    public static void main(String[] args) throws InterruptedException{
        //just()：可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束。
        Flux.just("Hello", "World").subscribe(System.out::println);
        // fromArray()，fromIterable()和 fromStream()：可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象。
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
        // empty()：创建一个不包含任何元素，只发布结束消息的序列。
        // error(Throwable error)：创建一个只包含错误消息的序列。
        // never()：创建一个不包含任何消息通知的序列。
        Flux.empty().subscribe(System.out::println);
        // range(int start, int count)：创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
        Flux.range(1, 4).subscribe(System.out::println);
        // interval(Duration period)和 interval(Duration delay, Duration period)：创建一个包含了从 0 开始递增的 Long 对象的序列。
        // 其中包含的元素按照指定的间隔来发布。除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。
        //Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);
        // 线程延迟关闭，不然最后一个例子木有输出
        //Thread.currentThread().join(10000);

        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final Random random = new Random();
        // generate()方法通过同步和逐一的方式来产生 Flux 序列。
        // 序列的产生是通过调用所提供的 SynchronousSink 对象的 next()，complete()
        // 和 error(Throwable)方法来完成的：
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
        // create()方法与 generate()方法的不同之处在于所使用的是 FluxSink 对象。
        // FluxSink 支持同步和异步的消息产生，并且可以在一次调用中产生
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
        /**
         * Mono 的创建方式与之前介绍的 Flux 比较相似。Mono 类中也包含了一些与 Flux 类中相同的静态方法。
         * 这些方法包括 just()，empty()，error()和 never()等。除了这些方法之外，Mono 还有一些独有的静态方法：
         *
         * fromCallable()、fromCompletionStage()、fromFuture()、fromRunnable()和 fromSupplier()：
         * 分别从 Callable、CompletionStage、CompletableFuture、Runnable 和 Supplier 中创建 Mono。
         *
         * delay(Duration duration)：创建一个 Mono 序列，在指定的延迟时间之后，产生数字 0 作为唯一值。
         *
         * ignoreElements(Publisher<T> source)：创建一个 Mono 序列，忽略作为源的 Publisher 中的所有元素，只产生结束消息。
         *
         * justOrEmpty(Optional<? extends T> data)和 justOrEmpty(T data)：从一个 Optional 对象或可能为 null 的对象中创建 Mono。
         * 只有 Optional 对象中包含值或对象不为 null 时，Mono 序列才产生对应的元素。
         *
         * 举些例子:
         *
         * Mono.just("are").subscribe(System.out::println);
         * Mono.empty().subscribe(System.out::println);
         * Mono.fromSupplier(() -> "you").subscribe(System.out::println);
         * Mono.justOrEmpty(Optional.of("ok")).subscribe(System.out::println);
         *
         * 还可以通过 create()方法来使用 MonoSink 来创建 Mono
         * Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
         *
         * 对流中包含的元素进行过滤，只留下满足 Predicate 指定条件的元素：
         * Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
         *
         *
         */
    }

}
