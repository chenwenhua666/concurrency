package com.plm.concurrency.webflux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author : cwh
 * 2020/4/13 0013
 * description ：
 */
@Controller
public class ViewController {
    @GetMapping("flux")
    public String flux() {
        return "flux";
    }

    public static void main(String[] args) {
       Flux.just(5, 10).flatMap(
                x -> Flux.range(1, x).take(x)
        ).subscribe(System.out::println);
        Flux.range(1, 1000).take(10).subscribe(System.out::println);

        /*Flux.just(5, 10)
                .flatMap(x -> Flux.interval(Duration.ofMillis(x * 10), Duration.ofMillis(100)).take(x))
                .toStream()
                .forEach(System.out::println);*/
    }
}
