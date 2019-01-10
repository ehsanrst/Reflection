package com.javacup;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public class Cat extends Animal {

    public int age;

    public Cat() {
    }

    public Cat(int age) {
        this.age = age;
    }

    @Deprecated
    @WebMethod
    public void food() {
        System.out.println("Meat");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void privateMethod() {
        System.out.println("Private method of Cat class");
    }
}
