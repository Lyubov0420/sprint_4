package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage2 {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By orderDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); //Локатор поля даты заказ
    private final By exactDate = By.xpath(".//div[contains(@class, 'react-datepicker__day--013')]");
    private final By rentPeriod = By.className("Dropdown-placeholder");
    private final By rentPeriodOption = By.xpath(".//div[@class='Dropdown-option']"); //поле срок аренды
    private final By blackColor = By.id("black"); //цвет самоката черный
    private final By greyColor = By.xpath("//input[@id='grey']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']"); //коммент доставке
    private final By confirmButton = By.xpath(".//button[text()='Заказать']");
    private final By yesButton = By.xpath(".//button[text()='Да']"); //кнопка Да
    private final By successModal = By.className("Order_ModalHeader");

    public OrderPage2(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectOrderDate() {
        driver.findElement(orderDate).click();
        driver.findElement(exactDate).click();
    }

    public void selectRentPeriod() {
        driver.findElement(rentPeriod).click();
        driver.findElements(rentPeriodOption).get(0).click();
    }

    public void selectBlackColor() {
        driver.findElement(blackColor).click();
    }

    public void selectGreyColor() {
        driver.findElement(greyColor).click();
    }
    public void addComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void confirmOrder() {
        driver.findElement(confirmButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click();
    }

    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModal)).isDisplayed();
    }
}