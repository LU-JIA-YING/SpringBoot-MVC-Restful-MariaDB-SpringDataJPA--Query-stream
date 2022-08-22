# SpringBoot-MVC-Restful-MariaDB-SpringDataJPA-Query-stream

#### H2-Database：http://localhost:8080/h2-console
* spring.datasource.url=jdbc:mariadb://localhost:3306/user
* spring.datasource.driverClassName=org.mariadb.jdbc.Driver
* spring.datasource.username=root
* spring.datasource.password=sa

#### spring-restcontroller-DemoUser:

* 取得所有使用者 http://localhost:8080/api/v1/user  
* 根據ID取得使用者 http://localhost:8080/api/v1/user/:id  
* 新增使用者 http://localhost:8080/api/v1/user  
* 修改使用者 http://localhost:8080/api/v1/user/:id  
* 刪除使用者 http://localhost:8080/api/v1/user/:id

* 查詢大於等於某個age的資料 http://localhost:8080/api/v1/user/getAge?age=21
* 查詢某個age的資料進行比大小 http://localhost:8080/api/v1/user/getAge2?age=37&ageFilter=1
* 根據age排序 ->倒敘 http://localhost:8080/api/v1/user/ageSort?age=-1&ageFilter=&sorted=desc 
             ->順序 http://localhost:8080/api/v1/user/ageSort?age=-1&ageFilter=&sorted=asc

======================查詢的資料無法使用 JPA 命名規則，使用原生 SQL 語法====================================

* 根據name和age查詢資料 http://localhost:8080/practice/user?name=Maggie&age=23
* 用SQL新增一個 user資料 http://localhost:8080/practice/user
* 用SQL更改某個 user資料 http://localhost:8080/practice/user/:id
* 根據name和age刪除資料 http://localhost:8080/practice/user?name=Kelly&age=35

=================使用 findAll() 將 user 資料從 DB 撈出後，使用 stream 進行操作以及過濾========================

* 取得一個 list 只有 name，且不重複並排序的資料  EX:[Bill, Brian, KZ]  http://localhost:8080/stream/user/getAllName
* 取得一個 map，其 key 為 ID；value 為 name  EX:1 : “Bill” http://localhost:8080/stream/user/getMapAndIdName
* 取得第一筆 name = KZ 的資料  http://localhost:8080/stream/user/getFirstEqualName
* 將資料先依據 age 排序，再依據 ID 排序  http://localhost:8080/stream/user/getOrderByAgeAndId
* 取得一個 string 為所有資料的 name, age|name, age  EX:Bill, 13|KZ, 23  http://localhost:8080/stream/user/getAllByStringNameAndAge
