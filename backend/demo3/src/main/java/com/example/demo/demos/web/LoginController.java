package com.example.demo.demos.web;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // 添加 CORS 配置
public class LoginController {

    // 模拟用户存储 (内存存储)
    private Map<String, User> users = new HashMap<>();

    // 用户注册
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (users.containsKey(user.getUsername())) {
            return "用户名已存在";
        }

        users.put(user.getUsername(), user);
        return "注册成功: " + user.getUsername();
    }

    // 用户登录
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        User user = users.get(loginRequest.getUsername());

        if (user == null) {
            return "用户不存在";
        }

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return "密码错误";
        }

        return "登录成功: " + user.getUsername();
    }

    // 获取用户信息
    @GetMapping("/user/{username}")
    public User getUserInfo(@PathVariable String username) {
        return users.get(username);
    }
}