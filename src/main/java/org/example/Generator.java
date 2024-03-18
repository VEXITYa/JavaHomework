package org.example;
import java.lang.reflect.Field;
import java.lang.management.ManagementFactory;

public class Generator {
    private static long startTime;
    private static final long m = (long)Math.pow(2, 31);
    private static final long a = 214013;
    private static final long c = 2531011;
    public Generator()
    {
        startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
    }
    public static <T> void generate(T targetClassInstance){
        Field[] classFields = targetClassInstance.getClass().getDeclaredFields();
        for (Field targetClassField : classFields) {
            targetClassField.setAccessible(true);
            try
            {
                if (targetClassField.getType().equals(String.class)){
                    int len = (int) (generateLong() % 50);
                    char[] resString = new char[len];
                    for (int i = 0; i < len; i++) {
                        resString[i] = (char) ('a' + (generateLong() % 26));
                    }
                    targetClassField.set(targetClassInstance, new String(resString));
                }
                else if (targetClassField.getType().equals(char.class)){
                    targetClassField.set(targetClassInstance, (char) ('a' + (generateLong() % 26)));
                }
                else if (targetClassField.getType().equals(int.class)){
                    targetClassField.set(targetClassInstance, (int) (generateLong() % Integer.MAX_VALUE));
                }
                else if (targetClassField.getType().equals(long.class)){
                    targetClassField.set(targetClassInstance, generateLong());
                }
                else if (targetClassField.getType().equals(short.class)){
                    targetClassField.set(targetClassInstance, (short) (generateLong() % Short.MAX_VALUE));
                }
                else if (targetClassField.getType().equals(byte.class)){
                    targetClassField.set(targetClassInstance, (byte) (generateLong() % Byte.MAX_VALUE));
                }
                else if (targetClassField.getType().equals(boolean.class)){
                    if(generateLong() % 2 != 1)
                    {
                        targetClassField.set(targetClassInstance, false);
                    }
                    else
                    {
                        targetClassField.set(targetClassInstance, true);
                    }
                }
                else if (targetClassField.getType().equals(double.class)){
                    short point = (short)(generateLong() % 18);
                    targetClassField.set(targetClassInstance, (double) (generateLong() * Math.pow(0.1, point)));
                }
                else if (targetClassField.getType().equals(float.class)){
                    short point = (short)(generateLong() % 9);
                    targetClassField.set(targetClassInstance, (float) (generateLong() * Math.pow(0.1, point)));
                }
            }
            catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static long generateLong()
    {
        long buf = (a * startTime + c) % m;
        startTime = buf;
        return buf;
    }
}
