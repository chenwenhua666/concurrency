package com.plm.concurrency.mongodb.controller;

import com.plm.concurrency.mongodb.entity.User;
import com.plm.concurrency.mongodb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : cwh
 * 2020/4/14 0014
 * description ：
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, User user) {
        userService.updateUser(id, user);
    }

    /**
     * 根据用户 id查找
     * 存在返回，不存在返回 null
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id).orElse(null);
    }

    @GetMapping("/condition")
    public Page<User> getUserByCondition(int size, int page, User user) {
        return userService.getUserByCondition(size, page, user);
    }
}
