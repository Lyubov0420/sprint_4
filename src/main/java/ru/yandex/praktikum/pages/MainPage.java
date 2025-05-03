package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru"; // Константа URL


    private static final String FAQ_QUESTION_PATTERN = "accordion__heading-%d"; // Паттерны локаторов
    private static final String FAQ_ANSWER_PATTERN = "accordion__panel-%d"; // Паттерны локаторов

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieButton = By.id("rcc-confirm-button"); // Локатор баннера куки
    private final By faqSection = By.xpath("//div[@data-accordion-component='Accordion']"); // Локатор блока вопросов
    private final By orderButtonTop = By.className("Button_Button__ra12g"); // Локатор верхней кнопки Заказать
    private final By orderButtonBottom = By.xpath("(//button[text()='Заказать'])[2]"); // Локатор нижней кнопки Заказать

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(BASE_URL); // Исправила на использование константы
        try {
            driver.findElement(cookieButton).click(); // Закрываем куки
        } catch (Exception e) {
            System.out.println("Cookie banner not found");
        }
    }

    public void clickOrderButton(boolean isTop) {
        By button = isTop ? orderButtonTop : orderButtonBottom;
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void scrollToFAQ() {
        // Добавляем проверку, что страница полностью загрузилась
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));

        // Прокручиваем к разделу FAQ
        WebElement element = driver.findElement(faqSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getFAQAnswer(int questionIndex) {
        By question = By.id(String.format(FAQ_QUESTION_PATTERN, questionIndex));
        By answer = By.id(String.format(FAQ_ANSWER_PATTERN, questionIndex));

        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answer)).getText();
    }
}