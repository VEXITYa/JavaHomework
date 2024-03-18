package org.example;

import java.lang.reflect.Field;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Test testClassInstance = new Test();
        Generator.generate(testClassInstance);
        Field[] TestClassFields = testClassInstance.getClass().getDeclaredFields();
        for (Field TestClassField : TestClassFields) {
            TestClassField.setAccessible(true);
            try {
                System.out.println(TestClassField.get(testClassInstance));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}