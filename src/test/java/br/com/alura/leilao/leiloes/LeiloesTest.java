package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LeiloesTest {

    private LeilaoPage leilaoPage;

    @AfterEach
    public void afterEach() {
        this.leilaoPage.fechar();
    }

    @Test
    void deveCadastrarNovoLeilao() {
        LoginPage login = new LoginPage();
        login.preencherFormularioDeLogin("fulano", "pass");
        this.leilaoPage = login.efetuaLogin();
        CadastroLeilaoPage cadastroLeilaoPage = this.leilaoPage.carregarFormulario();

        String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia "+ dataHoje;
        String valorInicial = "500.00";

        this.leilaoPage = cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataHoje);

        assertTrue(this.leilaoPage.isLeilaoCadastrado(nome, valorInicial, dataHoje));
    }
}
