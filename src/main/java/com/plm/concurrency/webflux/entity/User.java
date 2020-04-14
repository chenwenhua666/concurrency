package com.plm.concurrency.webflux.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : cwh
 * 2020/4/14 0014
 * description ：
 */
@Document(collection = "user")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String description;
}
