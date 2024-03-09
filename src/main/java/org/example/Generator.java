package org.example;
import java.lang.reflect.Field;
import java.lang.management.ManagementFactory;

public class Generator {
    private static long StartTime;
    private static long m = (long)Math.pow(2, 31);
    private static long a = 214013;
    private static long c = 2531011;
    public Generator()
    {
        StartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    }
    public static <T> void Generate(T some_obj){
        Field[] ClassFields = some_obj.getClass().getDeclaredFields();
        for (Field classField : ClassFields) {
            classField.setAccessible(true);
            switch (classField.getType().toString()) {
                case "class java.lang.String" -> {
                    int len = (int) (GenerateLong() % 50);
                    char[] str = new char[len];
                    for (int i = 0; i < len; i++) {
                        str[i] = (char) ('a' + (GenerateLong() % 26));
                    }
                    try {
                        classField.set(some_obj, new String(str));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "int" -> {

                    try {
                        classField.set(some_obj, (int) GenerateLong() % Integer.MAX_VALUE);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "long" -> {
                    try {
                        classField.set(some_obj, GenerateLong());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static long GenerateLong()
    {
        long buf = (a * StartTime + c) % m;
        StartTime = buf;
        return buf;
    }
}
