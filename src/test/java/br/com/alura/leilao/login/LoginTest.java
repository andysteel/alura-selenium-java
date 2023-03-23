package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private LoginPage paginaDeLogin;

    @BeforeEach
    void setup() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    void afterEach() {
        this.paginaDeLogin.fechar();
    }

    @Test
    void deveEfetuarLoginComDadosValidos() {

        this.paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
        this.paginaDeLogin.efetuaLogin();

        assertFalse(this.paginaDeLogin.isPaginaDeLogin());
        assertEquals("fulano", this.paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveEfetuarLoginComDadosInvalidos() {

        this.paginaDeLogin.preencherFormularioDeLogin("invalido", "pass");
        this.paginaDeLogin.efetuaLogin();


        assertTrue(this.paginaDeLogin.isPaginaDeLoginComError());
        assertTrue(this.paginaDeLogin.paginaContemMensagemDeErroDeLogin());
        assertNull(this.paginaDeLogin.getNomeUsuarioLogado());
    }

    @Test
    void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.paginaDeLogin.navegaParaPaginaDeLances();

        assertTrue(this.paginaDeLogin.isPaginaDeLogin());
        assertFalse(this.paginaDeLogin.paginaContemTextoDePaginaDeLeiloes());
    }
}
