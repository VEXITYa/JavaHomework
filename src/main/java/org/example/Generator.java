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
    public static <T> void Generate(T targetClassInstance){
        Field[] ClassFields = targetClassInstance.getClass().getDeclaredFields();
        for (Field targetClassField : ClassFields) {
            targetClassField.setAccessible(true);
            if (targetClassField.getType().equals(String.class)){
                    int len = (int) (GenerateLong() % 50);
                    char[] resString = new char[len];
                    for (int i = 0; i < len; i++) {
                        resString[i] = (char) ('a' + (GenerateLong() % 26));
                    }
                    try {
                        targetClassField.set(targetClassInstance, new String(resString));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
            }
            else if (targetClassField.getType().equals(char.class)){
                try {
                    targetClassField.set(targetClassInstance, (char) ('a' + (GenerateLong() % 26)));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (targetClassField.getType().equals(int.class)){

                    try {
                        targetClassField.set(targetClassInstance, (int) (GenerateLong() % Integer.MAX_VALUE));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
            }
            else if (targetClassField.getType().equals(long.class)){
                    try {
                        targetClassField.set(targetClassInstance, GenerateLong());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
            }
            else if (targetClassField.getType().equals(short.class)){
                try {
                    targetClassField.set(targetClassInstance, (short) (GenerateLong() % Short.MAX_VALUE));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (targetClassField.getType().equals(byte.class)){
                try {
                    targetClassField.set(targetClassInstance, (byte) (GenerateLong() % Byte.MAX_VALUE));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (targetClassField.getType().equals(boolean.class)){
                try {
                    if(GenerateLong() % 2 != 1)
                    {
                        targetClassField.set(targetClassInstance, false);
                    }
                    else
                    {
                        targetClassField.set(targetClassInstance, true);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (targetClassField.getType().equals(double.class)){
                try {
                    short point = (short)(GenerateLong() % 18);
                    targetClassField.set(targetClassInstance, (double) (GenerateLong() * Math.pow(0.1, point)));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (targetClassField.getType().equals(float.class)){
                try {
                    short point = (short)(GenerateLong() % 9);
                    targetClassField.set(targetClassInstance, (float) (GenerateLong() * Math.pow(0.1, point)));
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
