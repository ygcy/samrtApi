package com.zhangfuqiang.zapi.entity;

import java.util.List;

/**
 * @author zhangfuqiang
 */
public class TestEntity {

    private String name;

    private String city;

    private int age;

    private List<TestEntity2> row;

    public List<TestEntity2> getRow() {
        return row;
    }

    public void setRow(List<TestEntity2> row) {
        this.row = row;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
