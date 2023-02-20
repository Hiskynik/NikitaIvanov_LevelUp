package ru.levelp.at.homework2;

public class IsHappy {

    public static boolean happy(int number) {

        if (number < 0) {
            throw new IllegalArgumentException("Номер билета не может быть отрицательным");
        }
        if ((number > 999999) || (number < 100001)) {
            return false;
        }

        int n1 = number / 100000 % 10;
        int n2 = number / 10000 % 10;
        int n3 = number / 1000 % 10;
        int n4 = number / 100 % 10;
        int n5 = number / 10 % 10;
        int n6 = number % 10;

        return (n1 + n2 + n3) == (n4 + n5 + n6);
    }

    public static void main(String[] arts) {
        System.out.println("Этот номер счастливый?:" + happy(321123));

    }
}
