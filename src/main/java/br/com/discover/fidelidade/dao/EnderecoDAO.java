package br.com.discover.fidelidade.dao;

import java.util.List;

import br.com.discover.fidelidade.model.Endereco;

public interface EnderecoDAO {
	
	Endereco recuperaEndereco(Integer idEndereco);
	
	void saveEndereco(Endereco endereco);
	
	void updateEndereco(Endereco endereco);
	
	void deleteEndereco(Integer idEndereco);
	
	List<Endereco> recuperaEnderecos(Integer idUsuario);
}
