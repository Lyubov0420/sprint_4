package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage1 {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameField = By.xpath(".//input[@placeholder='* Имя']"); // Локатор поля имя
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']"); // поля фамилия
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']"); // поля адрес
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']"); // поля станции метро
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); // поля номер телефона
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    private final By metroStation = By.xpath(".//div[text()='Бульвар Рокоссовского']");

    public OrderPage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillPersonalData(String name, String surname, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void selectMetroStation() {
        driver.findElement(metroField).click();
        wait.until(ExpectedConditions.elementToBeClickable(metroStation)).click();
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }
}