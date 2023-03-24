package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage extends PageObject {

    public CadastroLeilaoPage(WebDriver browser) {
        super(browser);
    }

    public LeilaoPage cadastrarLeilao(String nome, String valorInicial, String dataHoje) {
        this.browser.findElement(By.id("nome")).sendKeys(nome);
        this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(dataHoje);

        this.browser.findElement(By.id("button-submit")).submit();

        return new LeilaoPage(this.browser);
    }

    public boolean isMensagensDeValidacaoVisiveis() {
        String source = this.browser.getPageSource();
        return source.contains("minimo 3 caracteres")
                && source.contains("não deve estar em branco")
                && source.contains("deve ser um valor maior de 0.1")
                && source.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
