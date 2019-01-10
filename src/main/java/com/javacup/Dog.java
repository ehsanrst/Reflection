package com.javacup;

public class Dog extends Animal {
    public int age;
    public static String color;

    public Dog() {
    }

    public Dog(int age) {
        this.age = age;
    }

    public static void nest() {
        System.out.println("live in yards.");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void setColor(String color) {
        Dog.color = color;
    }
}
