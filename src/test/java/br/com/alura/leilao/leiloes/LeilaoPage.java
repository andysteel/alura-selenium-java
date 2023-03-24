package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeilaoPage extends PageObject {
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

    public LeilaoPage(WebDriver browser) {
        super(browser);
    }

    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String dataAbertura) {
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValor = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        this.tirarFotoElementoEspecifico(null, "#tabela-leiloes tbody tr:last-child");

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(dataAbertura)
                && colunaValor.getText().equals(valor);
    }

}
