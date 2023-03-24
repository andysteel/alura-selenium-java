package br.com.alura.leilao;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class PageObject {

    protected final WebDriver browser;

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        if(browser == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-debugging-port=9222");
            this.browser = new ChromeDriver(options);
        } else {
            this.browser = browser;
        }

        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS)
                .pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void fechar() {
        this.browser.quit();
    }

    public void tirarFoto() {
        String agora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss"));
        File srcFile = ((TakesScreenshot) this.browser).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(Files.newInputStream(srcFile.toPath()), new  File("src/test/resources/images/"+ UUID.randomUUID().toString() +"-"+ agora +".png").toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void tirarFotoElementoEspecifico(@Nullable String identificador, @Nullable String cssSelector) {
        String agora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH:mm:ss"));

        try {

            WebElement element = null;
            if(identificador != null) {
                element = this.browser.findElement(By.id(identificador));
            } else if(cssSelector != null) {
                element = this.browser.findElement(By.cssSelector(cssSelector));
            }

            if(element != null) {
                File srcFile = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
                Files.copy(Files.newInputStream(srcFile.toPath()), new  File("src/test/resources/images/"+ UUID.randomUUID().toString() +"-"+ agora +".png").toPath());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
