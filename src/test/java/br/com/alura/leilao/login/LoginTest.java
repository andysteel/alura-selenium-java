package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    public static final String HTTP_LOCALHOST_8080_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");

        this.browser = new ChromeDriver(options);
        this.browser.navigate().to(HTTP_LOCALHOST_8080_LOGIN);
    }

    @AfterEach
    void afterEach() {
        this.browser.quit();
    }

    @Test
    void deveEfetuarLoginComDadosValidos() {

        this.browser.findElement(By.id("username")).sendKeys("fulano");
        this.browser.findElement(By.id("password")).sendKeys("pass");
        this.browser.findElement(By.id("login-form")).submit();

        assertNotEquals(HTTP_LOCALHOST_8080_LOGIN, this.browser.getCurrentUrl());
        assertEquals("fulano", this.browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    void naoDeveEfetuarLoginComDadosInvalidos() {

        this.browser.findElement(By.id("username")).sendKeys("invalido");
        this.browser.findElement(By.id("password")).sendKeys("pass");
        this.browser.findElement(By.id("login-form")).submit();

        By by = By.id("usuario-logado");

        assertEquals("http://localhost:8080/login?error", browser.getCurrentUrl());
        assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        assertThrows(NoSuchElementException.class, () -> this.browser.findElement(by));
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");

        assertEquals(HTTP_LOCALHOST_8080_LOGIN, this.browser.getCurrentUrl());
        assertFalse(this.browser.getPageSource().contains("Dados do Leilão"));
    }
}
