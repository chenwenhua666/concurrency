package com.plm.concurrency.mongodb.dao;

import com.plm.concurrency.mongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author : cwh
 * 2020/4/14 0014
 * description ：
 */
@Repository
public interface UserDao extends MongoRepository<User, String> {

    List<User> findByAgeBetween(Integer from, Integer to);

    /**
     * 通过年龄段，用户名，描述（模糊查询）
     * 方法参数个数需要和方法名中所需要的参数个数对应上。
     * @param from        from
     * @param to          to
     * @param name        name
     * @param description description
     * @return List<User>
     */
    List<User> findByAgeBetweenAndNameEqualsAndDescriptionIsLike(Integer from, Integer to, String name, String description);

}
