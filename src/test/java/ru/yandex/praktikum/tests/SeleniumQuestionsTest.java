package ru.yandex.praktikum.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.yandex.praktikum.pages.MainPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumQuestionsTest extends BaseTest {

    @ParameterizedTest(name = "Проверка вопроса #{0}: {2}")
    @MethodSource("questionData")
    void testQuestionAnswer(int questionIndex, String expectedAnswer, String questionDescription) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollToFAQ();

        String actualAnswer = mainPage.getFAQAnswer(questionIndex);
        assertEquals(expectedAnswer, actualAnswer,
                String.format("Ошибка в вопросе '%s'", questionDescription));
    }

    static Stream<Arguments> questionData() {
        return Stream.of(
                Arguments.of(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "Стоимость аренды"),
                Arguments.of(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "Несколько самокатов"),
                Arguments.of(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "Расчет времени аренды"),
                Arguments.of(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "Заказ на сегодня"),
                Arguments.of(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "Продление заказа"),
                Arguments.of(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "Зарядка"),
                Arguments.of(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "Отмена заказа"),
                Arguments.of(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "Доставка за МКАД")
        );
    }
}
