package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldSubmitValidForm() {
        driver.get("http://localhost:9999");


        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");


        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79231234567");


        driver.findElement(By.cssSelector("[data-test-id='agreement'] .checkbox__box")).click();


        driver.findElement(By.cssSelector("button.button")).click();


        String actualText = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        String expectedText = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";

        assertEquals(expectedText, actualText.trim());
    }
}