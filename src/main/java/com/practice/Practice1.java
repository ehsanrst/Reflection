package com.practice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Practice1 {
    public static void main(String[] args)
            throws ClassNotFoundException,
            NoSuchMethodException,
            NoSuchFieldException,
            IllegalAccessException,
            InstantiationException,
            InvocationTargetException {

        System.out.println("Class Name:");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.nextLine();
        Class clazz = Class.forName(className); //create Class Object

        System.out.println("Method Name:");
        String methodName = scanner.nextLine();
        Method method = clazz.getDeclaredMethod(methodName); //create Method Object

        Object object = clazz.newInstance(); //or with Constructor create obj of Class

        method.setAccessible(true); //for private methods
        method.invoke(object); //invoke method on obj

        Annotation[] annotations = clazz.getAnnotations(); //Annotation
        for (Annotation annotation : annotations) {
            System.out.println("Annotation: " + annotation.annotationType().getSimpleName());
        }

        System.out.println("Field Name:");
        String fieldName = scanner.nextLine();
        Field field = clazz.getDeclaredField(fieldName);

        field.set(object, 22);
        // System.out.println(field.get(object));
        System.out.println(object);

    }
}

@Deprecated
class A {
    public int field;

    public void f() {
        System.out.println("A.f() method invoked");
    }

    @Override
    public String toString() {
        return String.valueOf(field);
    }
}

class B {
    private void g() {
        System.out.println("B.g() method invoked");
    }
}
