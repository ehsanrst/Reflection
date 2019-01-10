package com;

import com.javacup.Cat;
import com.javacup.Dog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public class Reflection {
    public static void main(String[] args) throws Exception {

        /***Class Object***/
        //Access to Class Object
        //use .class
        Class dogClass = Dog.class;

        //use Class.forName
        Class catClass = Class.forName("com.javacup.Cat");

        //use getClass method
        Object obj = new Dog();
        Class dogClass1 = obj.getClass();

        /***java.lang.Class tools***/
        //1*Field   .get(obj) / .set(obj,value)
        Dog dog1 = new Dog();
        dog1.age = 12;
        Class dogClass2 = Class.forName("com.javacup.Dog");
        Field[] fields = dogClass2.getFields();
        for (Field field : fields) {
            if (field.getName().equals("age")) {
                Object value = field.get(dog1);
                int a = (int) value;
                System.out.println(a);
                field.set(dog1, a * 2);
                System.out.println(dog1.age);
            }
        }
        //static Field
        Dog.class.getField("color").set(null, "Brown");
        System.out.println(Dog.color);

        //2*Method   .getMethod("name",parameters) / .invoke(obj,value)
        Dog dog2 = new Dog();
        Class dogClass3 = dog2.getClass();
        Method setter = dogClass3.getMethod("setAge", int.class);
        setter.invoke(dog2, new Integer(30));
        Method getter = dogClass3.getMethod("getAge");
        int age = (int) getter.invoke(dog2);
        System.out.println(age);
        //static method
        Dog.class.getMethod("nest").invoke(null); //don't need object

        Parameter[] params = setter.getParameters();
        System.out.println(params[0].getType().getSimpleName());

        //3*Constructor   .getConstructor(parameters) / .newInstance(value)
        Class dogClass4 = Dog.class;
        //1
        Constructor constructor = dogClass4.getConstructor(int.class);
        Object obj1 = constructor.newInstance(new Integer(40));
        Dog dog3 = (Dog) obj1;
        System.out.println(dog3.age);
        //2
        Dog dog4 = (Dog) dogClass4.newInstance(); //just call empty Constructor

        //4*Annotation   .getAnnotations()
        Annotation[] annotations = Cat.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType());
        }
        annotations = Cat.class.getMethod("food").getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType().getSimpleName());
        }

        /***Generic**/
        ArrayList<String> strList = new ArrayList<>();
        addInteger(strList);
        //in com.Reflection we never know about String (the Type Parameter) because it will be Erased
        for (Object o : strList) { //so here we must write Object (not String)
            System.out.println(o);
        }

        /***Access**/
        //we can access to private fields and methods of class with reflection
        Cat cat1 = new Cat();
        Method method = cat1.getClass().getDeclaredMethod("privateMethod");
        method.setAccessible(true);//we can make it accessible in just this object of Method on cat1
        method.invoke(cat1);
    }

    //Generic
    public static void addInteger(ArrayList<String> list) throws Exception {//String just check in compile time
        Method m = list.getClass().getMethod("add", Object.class);//in runtime Generic Type Parameter will Erase
        m.invoke(list, new Integer(2));//so we can new Object with Integer and so on
    }

    //Generic as above
    public static void addObject(ArrayList<String> list) throws Exception {
        Method m = list.getClass().getMethod("add", Object.class);
        m.invoke(list, new Object());
    }
}
