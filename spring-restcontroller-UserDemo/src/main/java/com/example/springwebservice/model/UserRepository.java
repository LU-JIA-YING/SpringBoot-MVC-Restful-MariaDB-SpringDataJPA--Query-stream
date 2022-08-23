package com.example.springwebservice.model;

import com.example.springwebservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    Long deleteById(int id);

    List<User> findByAgeGreaterThanEqual(int age);

    List<User> findByOrderByAgeDesc();

    List<User> findByOrderByAgeAsc();

//======================查詢的資料無法使用 JPA 命名規則，使用原生 SQL 語法====================================

    @Query(value = "select * from member where name=?1 and age=?2", nativeQuery = true)
    List<User> findByNameAndAge(String name, int id);

    @Query(value = "insert into member values(?1,?2,?3)", nativeQuery = true)
    void createUserBySql(int id, String name, int age);


    @Modifying
    @Transactional
    @Query(value = "update member set age=?3,name=?2 where id=?1", nativeQuery = true)
    int updateUserBySql(int id, String name,int age);

    @Modifying
    @Transactional
    @Query(value = "delete from member where name=?1 and age=?2", nativeQuery = true)
    int deleteUserByNameAndAge(String name,int age);
}
