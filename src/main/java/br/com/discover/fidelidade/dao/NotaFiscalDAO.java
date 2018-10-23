package br.com.discover.fidelidade.dao;

import java.util.List;

import br.com.discover.fidelidade.model.NotaFiscal;

public interface NotaFiscalDAO {
	
	NotaFiscal recuperaNotaFiscal(Integer idNotaFiscal);
	
	void saveNotaFiscal(NotaFiscal notaFiscal);
	
	void updateNotaFiscal(NotaFiscal notaFiscal);
	
	void deleteNotaFiscal(Integer notaFiscal);
	
	List<NotaFiscal> recuperaNotasFiscais(Integer idUsuario);
}
