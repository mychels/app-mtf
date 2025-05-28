package br.com.mrs.mtf;

import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.model.Alimento;
import br.com.mrs.mtf.service.AlimentoService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AlimentoServiceTest {

    private AlimentoDAO mockDAO;
    private AlimentoService service;

    @Before
    public void setUp() {
        mockDAO = mock(AlimentoDAO.class);
        service = new AlimentoService(mockDAO);
    }

    @Test
    public void naoDeveCadastrarAlimentoComNomeDuplicado() {
        Alimento alimento = new Alimento();
        alimento.setNomeAlimento("Arroz");

        when(mockDAO.buscarSeAlimentoJaExiste("Arroz")).thenReturn(true);

        boolean resultado = service.cadastrarAlimento(alimento);

        assertFalse(resultado);
        verify(mockDAO, never()).cadastrarAlimento(any());
    }

    @Test
    public void deveCadastrarAlimentoQuandoNomeNaoExiste() {
        Alimento alimento = new Alimento();
        alimento.setNomeAlimento("Feijão");

        when(mockDAO.buscarSeAlimentoJaExiste("Feijão")).thenReturn(false);

        boolean resultado = service.cadastrarAlimento(alimento);

        assertTrue(resultado);
        verify(mockDAO).cadastrarAlimento(alimento);
    }
}
