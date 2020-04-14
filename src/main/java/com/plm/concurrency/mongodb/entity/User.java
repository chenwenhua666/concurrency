package com.plm.concurrency.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : cwh
 * 2020/4/14 0014
 * description ：
 */
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String name;

    private Integer age;

    private String description;
}
