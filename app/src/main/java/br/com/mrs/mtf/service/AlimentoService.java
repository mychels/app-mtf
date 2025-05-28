package br.com.mrs.mtf.service;

import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.model.Alimento;

public class AlimentoService {
    private AlimentoDAO alimentoDAO;

    public AlimentoService(AlimentoDAO alimentoDAO) {
        this.alimentoDAO = alimentoDAO;
    }

    public boolean cadastrarAlimento(Alimento alimento) {
        if (alimentoDAO.buscarSeAlimentoJaExiste(alimento.getNomeAlimento())) {
            return false;
        }
        alimentoDAO.cadastrarAlimento(alimento);
        return true;
    }
}
