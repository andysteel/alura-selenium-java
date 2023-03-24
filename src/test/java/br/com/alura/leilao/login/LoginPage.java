package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeilaoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {

    private static final String HTTP_LOGIN = "http://localhost:8080/login";
    private static final String HTTP_LOGIN_ERROR = "http://localhost:8080/login?error";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(HTTP_LOGIN);
    }


    public void preencherFormularioDeLogin(String username, String password) {
        this.browser.findElement(By.id("username")).sendKeys(username);
        this.browser.findElement(By.id("password")).sendKeys(password);
        this.tirarFoto();
    }

    public LeilaoPage efetuaLogin() {
        this.browser.findElement(By.id("login-form")).submit();
        this.tirarFotoElementoEspecifico("usuario-logado", null);
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
