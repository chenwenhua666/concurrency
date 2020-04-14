package com.plm.concurrency.mongodb.service.impl;

import com.plm.concurrency.mongodb.dao.UserDao;
import com.plm.concurrency.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author : cwh
 * 2020/4/14 0014
 * description ：
 */
@Service
public class UserService {

    @Autowired
    private MongoTemplate template;

    @Autowired
    private UserDao userDao;

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public Optional<User> getUser(String id) {
        return this.userDao.findById(id);
    }

    /**
     * 新增和修改都是 save方法，
     * id 存在为修改，id 不存在为新增
     */
    public User createUser(User user) {
        user.setId(null);
        return userDao.save(user);
    }

    public void deleteUser(String id) {
        this.userDao.findById(id)
                .ifPresent(user -> this.userDao.delete(user));
    }

    public void updateUser(String id, User user) {
        this.userDao.findById(id)
                .ifPresent(
                        u -> {
                            u.setName(user.getName());
                            u.setAge(user.getAge());
                            u.setDescription(user.getDescription());
                            this.userDao.save(u);
                        }
                );
    }

    public Page<User> getUserByCondition(int size, int page, User user) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(user.getName())) {
            criteria.and("name").is(user.getName());
        }
        if (!StringUtils.isEmpty(user.getDescription())) {
            criteria.and("description").regex(user.getDescription());
        }

        query.addCriteria(criteria);

        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(page, size, sort);

        List<User> users = template.find(query.with(pageable), User.class);
        return PageableExecutionUtils.getPage(users, pageable, () -> template.count(query, User.class));
    }
}
