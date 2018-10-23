package br.com.discover.fidelidade.dao;

import br.com.discover.fidelidade.model.Usuario;

public interface UsuarioDAO {

	Usuario recuperaUsuario(Integer idUsuario);
	
	void saveUsuario(Usuario usuario);
	
	void updateUsuario(Usuario usuario);
	
	void deleteUsuario(Integer idUsuario);
}
