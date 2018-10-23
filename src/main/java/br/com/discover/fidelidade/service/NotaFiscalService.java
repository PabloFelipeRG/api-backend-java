package br.com.discover.fidelidade.service;

import java.util.List;

import br.com.discover.fidelidade.model.NotaFiscal;
import br.com.discover.fidelidade.rest.v1.resquest.NotaFiscalRequest;

public interface NotaFiscalService {
	
	NotaFiscal recuperaNotaFiscal(Integer idNotaFiscal);
	
	void saveNotaFiscal(NotaFiscalRequest notaFiscal);
	
	void updateNotaFiscal(NotaFiscalRequest notaFiscal);
	
	void deleteNotaFiscal(Integer idNotaFiscal);
	
	List<NotaFiscal> recuperaNotasFiscais(Integer idUsuario);
}
