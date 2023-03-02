package ru.levelp.at.homework2;

import static org.testng.Assert.assertFalse;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IsHappyTest {

    @Test
    public void shouldIsHappyReturnTrue() {
        IsHappy ih = new IsHappy();
        Assert.assertTrue(ih.happy(111111));
    }

    @Test
    public void shouldIsHappyReturnTrue1() {
        IsHappy ih = new IsHappy();
        Assert.assertTrue(ih.happy(123321));
    }

    @Test(expectedExceptions = {IllegalArgumentException.class},
          expectedExceptionsMessageRegExp = "Номер билета не может быть отрицательным!")
    public void shouldIsHappyNegativeNumber() {
        IsHappy ih = new IsHappy();
        Assert.assertFalse(ih.happy(-131321));
    }

    @Test
    public void shouldIsHappyReturnFalse() {
        IsHappy ih = new IsHappy();
        Assert.assertFalse(ih.happy(123456));
    }

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][] {
            {123456}, {1},
            {12}, {123}, {1234},
            {12345}, {1234567}};
    }

    @Test(dataProvider = "dataProvider", expectedExceptions = {IllegalArgumentException.class},
          expectedExceptionsMessageRegExp = "Номер билета должен состоять из 6 цифр!")
    public void shouldIsHappyReturnFalseIfNegative(int number) {
        IsHappy ih = new IsHappy();
        assertFalse(ih.happy(number));
    }
}
