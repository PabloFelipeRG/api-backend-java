package br.com.discover.fidelidade.service;

import java.util.List;

import br.com.discover.fidelidade.model.Endereco;
import br.com.discover.fidelidade.rest.v1.resquest.EnderecoRequest;

public interface EnderecoService {
	
	Endereco recuperaEndereco(Integer idEndereco);
	
	void saveEndereco(EnderecoRequest endereco);
	
	void updateEndereco(EnderecoRequest endereco);
	
	void deleteEndereco(Integer idEndereco);
	
	List<Endereco> recuperaEnderecos(Integer idUsuario);
}
