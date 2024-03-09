package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Generator a = new Generator();
        Test b = new Test();
        Generator.Generate(b);
        System.out.println(b.getCount());
        System.out.println(b.getName());
        System.out.println(b.getPackage());
        System.out.println(b.getWeight());
    }
}