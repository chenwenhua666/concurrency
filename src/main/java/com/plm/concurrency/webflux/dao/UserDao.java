package com.plm.concurrency.webflux.dao;

import com.plm.concurrency.webflux.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : cwh
 * 2020/4/14 0014
 * description ：
 */
@Repository
public interface UserDao extends ReactiveMongoRepository<User, String> {


}
