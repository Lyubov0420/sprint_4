package ru.yandex.praktikum.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.ScooterOrderForm;
import ru.yandex.praktikum.pages.ScooterRentalDetailsForm;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("testData")
    public void testOrder(
            boolean useTopButton,  // true - верхняя кнопка, false - нижняя
            String name,
            String lastName,
            String address,
            String phone,
            String color,
            String comment
    ) {
        MainPage mainPage = new MainPage(driver);
        ScooterOrderForm orderPage1 = new ScooterOrderForm(driver);
        ScooterRentalDetailsForm orderPage2 = new ScooterRentalDetailsForm(driver);

        mainPage.open();
        mainPage.clickOrderButton(useTopButton); // Использ. нижнюю кнопку Заказать

        orderPage1.fillPersonalData(name, lastName, address, phone);
        orderPage1.selectMetroStation();
        orderPage1.clickNextButton();

        orderPage2.selectOrderDate();
        orderPage2.selectRentPeriod();
        if ("black".equals(color)) {
            orderPage2.selectBlackColor();
        } else {
            orderPage2.selectGreyColor();
        }
        orderPage2.addComment(comment);
        orderPage2.confirmOrder();

        assertTrue(orderPage2.isOrderConfirmed());
    }

    static Stream<Arguments> testData() {  // Проверка обеих кнопок
        return Stream.of(

                Arguments.of(true, "Дмитрий", "Донской", "Самара", "79991234567", "black", "Тестовый заказ 1"),
                Arguments.of(false, "Дмитрий", "Донской", "Самара", "79991234567", "black", "Тестовый заказ 1"),
                Arguments.of(true, "Анна", "Смирнова", "Москва", "79998765432", "grey", "Тестовый заказ 2"),
                Arguments.of(false, "Анна", "Смирнова", "Москва", "79998765432", "grey", "Тестовый заказ 2")
        );
    }
}