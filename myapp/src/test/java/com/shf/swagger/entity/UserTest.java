package com.shf.swagger.entity;


public class UserTest {
    public static void main(String[] args) {
        User user = new User();
        user.setId(10);

        String name = user.getName();

        User user1 = new User(10, "", 20);

        User user2 = User.builder().id(1).name("itcast").age(20).build();
        System.out.println(user2);
    }
}