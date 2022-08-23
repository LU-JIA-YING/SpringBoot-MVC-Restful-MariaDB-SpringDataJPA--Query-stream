package com.example.springwebservice.controller;

import com.example.springwebservice.controller.dto.reponse.StatusResponse;
import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/practice/user")
public class PracticeController {

    @Autowired
    UserService userService;

//======================查詢的資料無法使用 JPA 命名規則，使用原生 SQL 語法====================================

    //根據name和age查詢資料
    @GetMapping
    public List<User> getUserByNameAndAge(@RequestParam String name, @RequestParam int age) {
        List<User> response = userService.getUserByNameAndAge(name, age);
        return response;
    }

    //新增 user資料
    @PostMapping()
    public StatusResponse createUserBySql(@RequestBody CreateUserRequest request) {
        String response = userService.createUserBySql(request);
        return new StatusResponse(response);
    }

    //更新資料
    @PutMapping("/{id}")
    public StatusResponse updateUserBySql(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        String response = userService.updateUserBySql(id, request);
        return new StatusResponse(response);
    }

    //根據name和age刪除資料
    @DeleteMapping
    public StatusResponse deleteUserByNameAndAge(@RequestParam String name, @RequestParam int age) {
        String response = userService.deleteUserByNameAndAge(name, age);
        return new StatusResponse(response);
    }
}
