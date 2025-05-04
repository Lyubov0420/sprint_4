package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterRentalDetailsForm {  // Исправила, по тем же соображениям, что и OrderPage1
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By orderDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); //Локатор поля даты заказ
    private final By exactDate = By.xpath(".//div[contains(@class, 'react-datepicker__day--013')]");
    private final By rentPeriod = By.className("Dropdown-placeholder");
    private final By rentPeriodOption = By.xpath(".//div[@class='Dropdown-option']"); //поле срок аренды
    private final By blackColor = By.id("black"); //цвет самоката черный
    private final By greyColor = By.xpath("//input[@id='grey']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']"); //коммент доставке
    private final By finalOrderButton = By.xpath("(//button[contains(@class, 'Button_Button__ra12g') and text()='Заказать'])[2]"); // Нижняя кнопка Заказать
    private final By yesButton = By.xpath(".//button[text()='Да']"); //кнопка Да
    private final By successModal = By.className("Order_ModalHeader");

    public ScooterRentalDetailsForm(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectOrderDate() {
        wait.until(ExpectedConditions.elementToBeClickable(orderDate)).click();
        wait.until(ExpectedConditions.elementToBeClickable(exactDate)).click();
    }

    public void selectRentPeriod() {
        wait.until(ExpectedConditions.elementToBeClickable(rentPeriod)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentPeriodOption)).click();
    }

    public void selectBlackColor() {
        wait.until(ExpectedConditions.elementToBeClickable(blackColor)).click();
    }

    public void selectGreyColor() {
        wait.until(ExpectedConditions.elementToBeClickable(greyColor)).click();
    }

    public void addComment(String comment) {
        wait.until(ExpectedConditions.elementToBeClickable(commentField)).sendKeys(comment);
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(finalOrderButton)).click(); // Нижняя кнопка Заказать в форме аренды
        wait.until(ExpectedConditions.elementToBeClickable(yesButton)).click(); // Кнопка Да
    }

    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModal)).isDisplayed(); // Проверка оформления заказа
    }
}