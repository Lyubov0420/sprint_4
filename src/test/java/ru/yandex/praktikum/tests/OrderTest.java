package ru.yandex.praktikum.tests;

import org.junit.jupiter.api.Test;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderPage1;
import ru.yandex.praktikum.pages.OrderPage2;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {

    @Test
    public void testOrderFirstDataSet() {
        MainPage mainPage = new MainPage(driver);
        OrderPage1 orderPage1 = new OrderPage1(driver);
        OrderPage2 orderPage2 = new OrderPage2(driver);

        mainPage.open();
        mainPage.clickOrderButton(true);

        orderPage1.fillPersonalData("Дмитрий", "Донской", "Самара", "79991234567");
        orderPage1.selectMetroStation();
        orderPage1.clickNextButton();

        orderPage2.selectOrderDate();
        orderPage2.selectRentPeriod();
        orderPage2.selectBlackColor();
        orderPage2.addComment("Тестовый заказ 1");
        orderPage2.confirmOrder();

        assertTrue(orderPage2.isOrderConfirmed());
    }

    @Test
    public void testOrderSecondDataSet() {
        MainPage mainPage = new MainPage(driver);
        OrderPage1 orderPage1 = new OrderPage1(driver);
        OrderPage2 orderPage2 = new OrderPage2(driver);

        mainPage.open();
        mainPage.clickOrderButton(false); // Используем нижнюю кнопку

        orderPage1.fillPersonalData("Анна", "Смирнова", "Москва", "79998765432");
        orderPage1.selectMetroStation();
        orderPage1.clickNextButton();

        orderPage2.selectOrderDate();
        orderPage2.selectRentPeriod();
        orderPage2.selectGreyColor();
        orderPage2.addComment("Тестовый заказ 2");
        orderPage2.confirmOrder();

        assertTrue(orderPage2.isOrderConfirmed());
    }
}