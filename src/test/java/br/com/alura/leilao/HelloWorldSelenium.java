package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class HelloWorldSelenium {

    @Test
    void hello() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");

        WebDriver browser = new ChromeDriver(options);
        browser.navigate().to("http://localhost:8080/leiloes");
        browser.quit();

    }
}
