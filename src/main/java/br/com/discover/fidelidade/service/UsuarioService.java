package br.com.discover.fidelidade.service;

import br.com.discover.fidelidade.model.Usuario;
import br.com.discover.fidelidade.rest.v1.resquest.UsuarioRequest;

public interface UsuarioService {

	Usuario recuperaUsuario(Integer idUsuario);
	
	void saveUsuario(UsuarioRequest usuario);
	
	void updateUsuario(UsuarioRequest usuario);
	
	void deleteUsuario(Integer idUsuario);
}
