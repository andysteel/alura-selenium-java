package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LeiloesTest {

    private LeilaoPage leilaoPage;
    private CadastroLeilaoPage cadastroLeilaoPage;

    @BeforeEach
    public void beforeEach() {
        LoginPage login = new LoginPage();
        login.preencherFormularioDeLogin("fulano", "pass");
        this.leilaoPage = login.efetuaLogin();
        this.cadastroLeilaoPage = this.leilaoPage.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.leilaoPage.fechar();
    }

    @Test
    void deveCadastrarNovoLeilao() {

        String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia "+ dataHoje;
        String valorInicial = "700.00";

        this.leilaoPage = this.cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataHoje);

        assertTrue(this.leilaoPage.isLeilaoCadastrado(nome, valorInicial, dataHoje));
    }

    @Test
    void deveValidarCadastroDeLeilao() {
        this.leilaoPage = this.cadastroLeilaoPage.cadastrarLeilao("", "", "");
        this.leilaoPage.tirarFoto();
        assertTrue(this.cadastroLeilaoPage.isMensagensDeValidacaoVisiveis());
    }
}
