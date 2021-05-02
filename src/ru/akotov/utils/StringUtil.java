package ru.akotov.utils;

public class StringUtil {
    private StringUtil() {
        throw new RuntimeException("Не удалось создать..");
    }

    public static String plurals(int n, String typeOne, String typeTwo, String typeThree) {
        if (n == 0) {
            return typeThree;
        } else {
            n = Math.abs(n) % 100;
            if (n > 10 && n < 20) {
                return typeThree;
            } else {
                n %= 10;
                if (n > 1 && n < 5) {
                    return typeTwo;
                } else {
                    return n == 1 ? typeOne : typeThree;
                }
            }
        }
    }
}

