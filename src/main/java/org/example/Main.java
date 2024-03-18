package org.example;

import java.lang.reflect.Field;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Test TestClassInstance = new Test();
        Generator.Generate(TestClassInstance);
        Field[] TestClassFields = TestClassInstance.getClass().getDeclaredFields();
        for (Field TestClassField : TestClassFields) {
            TestClassField.setAccessible(true);
            try {
                System.out.println(TestClassField.get(TestClassInstance));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}