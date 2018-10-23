package br.com.discover.fidelidade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discover.fidelidade.dao.EnderecoDAO;
import br.com.discover.fidelidade.model.Endereco;
import br.com.discover.fidelidade.rest.v1.resquest.EnderecoRequest;
import br.com.discover.fidelidade.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	EnderecoDAO enderecoDAO;
	
	@Override
	public Endereco recuperaEndereco(Integer idEndereco) {
		return enderecoDAO.recuperaEndereco(idEndereco);
	}
	
	@Override
	public void saveEndereco(EnderecoRequest endereco) {
		Endereco address = new Endereco();
		address.setIdEndereco(endereco.getIdEndereco());
		address.setIdUsuario(endereco.getIdUsuario());
		address.setLogradouro(endereco.getLogradouro());
		address.setNumero(endereco.getNumero());
		address.setCep(endereco.getCep());
		address.setBairro(endereco.getBairro());
		address.setEstado(endereco.getEstado());
		address.setCidade(endereco.getCidade());
		address.setComplemento(endereco.getComplemento());
		enderecoDAO.saveEndereco(address);
	}
	
	@Override
	public void updateEndereco(EnderecoRequest endereco) {
		Endereco address = new Endereco();
		address.setIdEndereco(endereco.getIdEndereco());
		address.setIdUsuario(endereco.getIdUsuario());
		address.setLogradouro(endereco.getLogradouro());
		address.setNumero(endereco.getNumero());
		address.setCep(endereco.getCep());
		address.setBairro(endereco.getBairro());
		address.setEstado(endereco.getEstado());
		address.setCidade(endereco.getCidade());
		address.setComplemento(endereco.getComplemento());
		enderecoDAO.updateEndereco(address);
	}
	
	@Override
	public void deleteEndereco(Integer idEndereco) {
		enderecoDAO.deleteEndereco(idEndereco);
	}
	
	@Override
	public List<Endereco> recuperaEnderecos(Integer idUsuario) {
		return enderecoDAO.recuperaEnderecos(idUsuario);
	}
}









