package org.example;
import java.lang.reflect.Field;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;

public class Generator {
    private static long StartTime;
    private static final long m = (long)Math.pow(2, 31);
    private static final long a = 214013;
    private static final long c = 2531011;
    public Generator()
    {
        StartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    }
    public static <T> void Generate(T targetClassInstance){
        Field[] targetClassFields = targetClassInstance.getClass().getDeclaredFields();
        for (Field classField : targetClassFields) {
            classField.setAccessible(true);
            if(classField.getType().equals(String.class)) {
                    int len = (int) (GenerateLong() % 50);
                    char[] str = new char[len];
                    for (int i = 0; i < len; i++) {
                        str[i] = (char) ('a' + (GenerateLong() % 26));
                    }
                    try {
                        classField.set(targetClassInstance, new String(str));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
            }
            else if(classField.getType().equals(int.class)) {

                    try {
                        classField.set(targetClassInstance, (int) GenerateLong() % Integer.MAX_VALUE);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
            }
            else if(classField.getType().equals(long.class)) {
                    try {
                        classField.set(targetClassInstance, GenerateLong());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
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
