package ru.levelp.at.homework2;

import org.testng.Assert;
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
          expectedExceptionsMessageRegExp = "Номер билета не может быть отрицательным")
    public void shouldIsHappyNegativeNumber() {
        IsHappy ih = new IsHappy();
        Assert.assertFalse(ih.happy(-131321));
    }

    @Test
    public void shouldIsHappyReturnFalse() {
        IsHappy ih = new IsHappy();
        Assert.assertFalse(ih.happy(123456));
    }

    @Test
    public void shouldIsHappyReturnFalseNot6Digits() {
        IsHappy ih = new IsHappy();
        Assert.assertFalse(IsHappy.happy(1234567));
        Assert.assertFalse(IsHappy.happy(12345));
        Assert.assertFalse(IsHappy.happy(1234));
        Assert.assertFalse(IsHappy.happy(123));
        Assert.assertFalse(IsHappy.happy(12));
        Assert.assertFalse(IsHappy.happy(1));
    }
}
