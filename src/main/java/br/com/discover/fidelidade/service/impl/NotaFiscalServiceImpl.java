package br.com.discover.fidelidade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discover.fidelidade.dao.NotaFiscalDAO;
import br.com.discover.fidelidade.model.NotaFiscal;
import br.com.discover.fidelidade.rest.v1.resquest.NotaFiscalRequest;
import br.com.discover.fidelidade.service.NotaFiscalService;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

	@Autowired
	NotaFiscalDAO notaFiscalDAO;
	
	@Override
	public NotaFiscal recuperaNotaFiscal(Integer idNotaFiscal) {
		return notaFiscalDAO.recuperaNotaFiscal(idNotaFiscal);
	}
	
	@Override
	public void saveNotaFiscal(NotaFiscalRequest notaFiscal) {
		NotaFiscal invoice = new NotaFiscal();
		invoice.setIdNotaFiscal(notaFiscal.getIdNotaFiscal());
		invoice.setIdUsuario(notaFiscal.getIdUsuario());
		invoice.setNomeEmpresa(notaFiscal.getNomeEmpresa());
		invoice.setCnpj(notaFiscal.getCnpj());
		invoice.setDataEmissao(notaFiscal.getDataEmissao());
		invoice.setValor(notaFiscal.getValor());
		notaFiscalDAO.saveNotaFiscal(invoice);
	}
	
	@Override
	public void updateNotaFiscal(NotaFiscalRequest notaFiscal) {
		NotaFiscal invoice = new NotaFiscal();
		invoice.setIdNotaFiscal(notaFiscal.getIdNotaFiscal());
		invoice.setIdUsuario(notaFiscal.getIdUsuario());
		invoice.setNomeEmpresa(notaFiscal.getNomeEmpresa());
		invoice.setCnpj(notaFiscal.getCnpj());
		invoice.setDataEmissao(notaFiscal.getDataEmissao());
		invoice.setValor(notaFiscal.getValor());
		notaFiscalDAO.updateNotaFiscal(invoice);
	}
	
	@Override
	public void deleteNotaFiscal(Integer idNotaFiscal) {
		notaFiscalDAO.deleteNotaFiscal(idNotaFiscal);
	}
	
	@Override
	public List<NotaFiscal> recuperaNotasFiscais(Integer idUsuario) {
		return notaFiscalDAO.recuperaNotasFiscais(idUsuario);
	}
}
