package br.com.alura.leilao.login;

import br.com.alura.leilao.leiloes.LeilaoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPage {

    private static final String HTTP_LOGIN = "http://localhost:8080/login";
    private static final String HTTP_LOGIN_ERROR = "http://localhost:8080/login?error";
    private WebDriver browser;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");

        this.browser = new ChromeDriver(options);
        this.browser.navigate().to(HTTP_LOGIN);
    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencherFormularioDeLogin(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeilaoPage efetuaLogin() {
        this.browser.findElement(By.id("login-form")).submit();
        return new LeilaoPage(this.browser);
    }

    public boolean isPaginaDeLogin() {
        return this.browser.getCurrentUrl().equals(HTTP_LOGIN);
    }

    public boolean isPaginaDeLoginComError() {
        return this.browser.getCurrentUrl().equals(HTTP_LOGIN_ERROR);
    }

    public boolean paginaContemMensagemDeErroDeLogin() {
        return browser.getPageSource().contains("Usuário e senha inválidos.");
    }

    public boolean paginaContemTextoDePaginaDeLeiloes() {
        return browser.getPageSource().contains("Dados do Leilão");
    }

    public String getNomeUsuarioLogado() {
        try {
            return this.browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    public void navegaParaPaginaDeLances() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }
}
