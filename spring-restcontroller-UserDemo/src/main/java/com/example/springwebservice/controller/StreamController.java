package com.example.springwebservice.controller;

import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/stream/user")
public class StreamController {

    @Autowired
    UserService userService;

//================使用 findAll() 將 user 資料從 DB 撈出後，使用 stream 進行操作以及過濾======================

    //取得一個 list 只有 name，且不重複並排序的資料
    @GetMapping("/getAllName")
    public List<String> getOnlyNameAndNotRepeatAndSort() {
        List<String> userList = this.userService.getOnlyNameAndNotRepeatAndSort();
        return userList;
    }

    //取得一個 map，其 key 為 ID；value 為 name
    @GetMapping("/getMapAndIdName")
    public Map<Integer, String> getMapAndIdName() {
        Map<Integer, String> userList = this.userService.getMapAndIdName();
        return userList;
    }

    //取得第一筆 name = KZ 的資料
    @GetMapping("/getFirstEqualName")
    public Optional<User> getFirstEqualName() {
        Optional<User> userList = this.userService.getFirstEqualName();
        return userList;
    }

    @GetMapping("/getFirstEqualName/{name}")
    public User getFirstUserByName(@PathVariable String name) {
        User userList = this.userService.getFirstUserByName(name);
        return userList;
    }

    //將資料先依據 age 排序，再依據 ID 排序
    @GetMapping("/getOrderByAgeAndId")
    public List<User> getOrderByAgeAndId() {
        List<User> userList = this.userService.getOrderByAgeAndId();
        return userList;
    }

    //取得一個 string 為所有資料的 name, age|name, age  EX:Bill, 13|KZ, 23
    @GetMapping("/getAllByStringNameAndAge")
    public String getAllByStringNameAndAge() {
        String userList = this.userService.getAllByStringNameAndAge();
        return userList;
    }
}
