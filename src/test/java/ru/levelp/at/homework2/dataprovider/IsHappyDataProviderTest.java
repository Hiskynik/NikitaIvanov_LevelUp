package ru.levelp.at.homework2.dataprovider;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.levelp.at.homework2.IsHappy;

public class IsHappyDataProviderTest {

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][] {
            {123456}, {1},
            {12}, {123}, {1234},
            {12345}, {1234567}};
    }

    @Test(dataProvider = "dataProvider")
    public void shouldIsHappyReturnFalseIfNegative(int number) {
        IsHappy ih = new IsHappy();
        assertFalse(ih.happy(number));
    }

    @DataProvider
    public static Object[][] dataProvider1() {
        return new Object[][] {
            {123321}, {111111},
            {222222}, {333333},
            {575755}, {333333}};
    }

    @Test(dataProvider = "dataProvider1")
    public void shouldIsHappyReturnFalseIfPositive(int number) {
        IsHappy ih = new IsHappy();
        assertTrue(ih.happy(number));
    }
}
