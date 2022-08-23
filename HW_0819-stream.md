# 呂佳穎_08/19 HW-4討論

###### tags: `Homework`

>- [查看作業題目](https://hackmd.io/@kazzy/S1K5Lu5R9)
>- [作業繳交連結](https://docs.google.com/spreadsheets/d/1eF2svTzyUDSU5yD7Jo5kaLVfoZgovYAjqck08a9IDwM/edit#gid=1339301770)

## 目錄
[TOC]

# > 使用 findAll() 將 user 資料從 DB 撈出後，使用 stream 進行操作以及過濾

## 1. 取得一個 list 只有 name，且不重複並排序的資料
* [Bill, Brian, KZ]

```java=
public List<String> getOnlyNameAndNotRepeatAndSort() {
    List<User> response = userRepository.findAll();

    //“User::getName” / “User -> User.getName()”
    //distinct()列表去重複
    //sorted()排序
        List<String> newResponse = response.stream()
            .map(User::getName)
            .distinct()
            .sorted()
            .collect(Collectors.toList());

    return newResponse;
}
```


## 2. 取得一個 map，其 key 為 ID；value 為 name
* 1 : “Bill”
    2 : “Brian”

```java=
public Map<Integer, String> getMapAndIdName() {
    List<User> response = userRepository.findAll();

    Map<Integer, String> newResponse = response.stream()
            .collect(Collectors.toMap(u -> u.getId(), u -> u.getName()));

    return newResponse;
}
```


## 3. 取得第一筆 name = KZ 的資料

```java=
public Optional<User> getFirstEqualName() {
    List<User> response = userRepository.findAll();

    Optional<User> newResponse = response.stream()
            .filter(u -> u.getName().equals("KZ")) //"KZ".equals(u.getName())比較好(因為知道的要放前面)
            .findFirst();

    return newResponse;
}
```

## 4. 將資料先依據 age 排序，再依據 ID 排序

```java=
public List<User> getOrderByAgeAndId() {
    List<User> response = userRepository.findAll();

    List<User> newResponse = response.stream()
            .sorted(Comparator.comparing(User::getAge)
                    .thenComparing(User::getId))
            .collect(Collectors.toList());

    return newResponse;
}
```

## 5. 取得一個 string 為所有資料的 name, age|name, age
* Bill, 13|KZ, 23

```java=
public String getAllByStringNameAndAge() {
    List<User> response = userRepository.findAll();

    String newResponse = response.stream()
            .map(u -> u.getName() + "," + u.getAge())
            .collect(Collectors.joining("|"));

    return newResponse;
}
```

---
* [Spring Boot - MariaDB、Spring Data JPA、@Query、stream](https://hackmd.io/@IDdlPCCwQoeX-9DvmEbLyw/rylcdypRc)
