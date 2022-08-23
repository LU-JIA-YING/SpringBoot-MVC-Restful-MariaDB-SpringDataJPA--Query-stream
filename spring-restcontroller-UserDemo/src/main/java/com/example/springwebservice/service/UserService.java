package com.example.springwebservice.service;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //查詢所有人員
    public List<User> getUserList() {

        List<User> response = userRepository.findAll();

        return response;
    }

    //用id查詢人員
    public User getUserById(int id) {
        User response = userRepository.findById(id);
        return response;
    }

    public String createUser(CreateUserRequest request) {

        //新增一個 user資料
        User user = new User();

        //新增資料:user裡的資料是從 request來的
        user.setId(request.getId());
        user.setName(request.getName());
        user.setAge(request.getAge());

        //儲存進DB
        userRepository.save(user);

        //回傳 OK告訴 Controller完成儲存
        return "OK";
    }


    //更改某個 user資料
    public String updateUser(int id, UpdateUserRequest request) {

        //確認 DB中有沒有這筆資料
        User user = userRepository.findById(id);

        //如果修改資料找不到
        //null == user (確定的值要放前面)
        if (null == user) {
            return "FAIL";
        }

        //將要更改的值塞進去
        user.setName(request.getName());
        user.setAge(request.getAge());

        //儲存進DB
        userRepository.save(user);

        //回傳 OK告訴 Controller完成儲存
        return "OK";
    }

    //刪除某個user
    public String deleteUser(int id) {

        User user = userRepository.findById(id);

        if (null == user) {
            return "FAIL";
        }

        Long count = userRepository.deleteById(id);

        return "OK";
    }

//======================================================================

    //查詢大於等於某個age的資料
    public List<User> getAgeGreaterThanEquals(int age) {

        List<User> response = new ArrayList<>();

        //查詢大於等於某個age的資料
        if (0 < age) {
            response = userRepository.findByAgeGreaterThanEqual(age);
        } else {
            //查詢所有人員
            response = userRepository.findAll();
        }

        return response;
    }

    //查詢某個age的資料進行比大小
    public List<User> getAgeSort(int age, String ageFilter) {

        List<User> response = new ArrayList<>();

        if (-1 == age) {
            return response = userRepository.findAll();
        }

        if ("0".equals(ageFilter)) {

            // 查詢所有 age 的資料

        } else if ("1".equals(ageFilter)) {

            // 查詢大於等於某個 age 的資料
            response = userRepository.findByAgeGreaterThanEqual(age);

        } else if ("2".equals(ageFilter)) {

            // 查詢小於某個 age 的資料

        } else if ("3".equals(ageFilter)) {

            // 查詢小於等於某個 age 的資料

        } else if ("4".equals(ageFilter)) {

            // 查詢大於等於某個 age 的資料
        }

        return response;
    }

    //根據age排序
    public List<User> getAllAndAgeSort(int age, String ageFilter, String sorted) {
        List<User> response = new ArrayList<>();
        if (0 >= age) {

            switch (sorted) {

                case "asc":
                    response = userRepository.findByOrderByAgeAsc();
                    break;
                case "desc":
                    response = userRepository.findByOrderByAgeDesc();
                    break;
                default:
                    response = userRepository.findAll();
                    break;
            }
        }
        return response;
    }

//======================查詢的資料無法使用 JPA 命名規則，使用原生 SQL 語法====================================

    //根據name和age查詢資料
    public List<User> getUserByNameAndAge(String name, int age) {
        List<User> response = userRepository.findByNameAndAge(name, age);
        return response;
    }

    //用SQL新增一個 user資料
    public String createUserBySql(CreateUserRequest request) {

        userRepository.createUserBySql(request.getId(), request.getName(), request.getAge());

        return "OK";
    }

    //用SQL更改某個 user資料
    public String updateUserBySql(int id, UpdateUserRequest request) {

        int count = userRepository.updateUserBySql(id, request.getName(), request.getAge());
        if (0 < count) {
            return "OK";
        } else {
            return "FAIT";
        }
    }

    //根據name和age刪除資料
    public String deleteUserByNameAndAge(String name, int age) {

        int count = userRepository.deleteUserByNameAndAge(name, age);
        if (0 < count) {
            return "OK";
        } else {
            return "FAIL";
        }
    }

//=================使用 findAll() 將 user 資料從 DB 撈出後，使用 stream 進行操作以及過濾========================

    //取得一個 list 只有 name，且不重複並排序的資料  EX:[Bill, Brian, KZ]
    public List<String> getOnlyNameAndNotRepeatAndSort() {
        List<User> response = userRepository.findAll();

        //“User::getName”/“User -> User.getName()”
        //distinct()列表去重複
        //sorted()排序
        List<String> newResponse = response.stream()
                .map(User::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return newResponse;
    }

    //取得一個 map，其 key 為 ID；value 為 name  EX:1 : “Bill”
    public Map<Integer, String> getMapAndIdName() {
        List<User> response = userRepository.findAll();

        Map<Integer, String> newResponse = response.stream()
                .collect(Collectors.toMap(u -> u.getId(), u -> u.getName()));

        return newResponse;
    }

    //取得第一筆 name = KZ 的資料
    public Optional<User> getFirstEqualName() {
        List<User> response = userRepository.findAll();

        Optional<User> newResponse = response.stream()
                .filter(u -> u.getName().equals("KZ"))//"KZ".equals(u.getName())
                .findFirst();

        return newResponse;
    }

    public User getFirstUserByName(String name) {
        List<User> response = this.userRepository.findAll();

        List<User> newResponse = response.stream()
                .filter(x -> name.equals(x.getName()))
                .collect(Collectors.toList());

        Optional<User> first = newResponse.stream().findFirst();

        if (first.isPresent()) {
            User user = first.get();
            return user;
        } else {
            return null;
        }
    }

    //將資料先依據 age 排序，再依據 ID 排序
    public List<User> getOrderByAgeAndId() {
        List<User> response = userRepository.findAll();

        List<User> newResponse = response.stream()
                .sorted(Comparator.comparing(User::getAge)
                        .thenComparing(User::getId))
                .collect(Collectors.toList());

        return newResponse;
    }

    //取得一個 string 為所有資料的 name, age|name, age  EX:Bill, 13|KZ, 23
    public String getAllByStringNameAndAge() {
        List<User> response = userRepository.findAll();

        String newResponse = response.stream()
                .map(u -> u.getName() + "," + u.getAge())
                .collect(Collectors.joining("|"));

        return newResponse;
    }
} // Class end
