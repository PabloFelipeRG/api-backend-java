package br.com.discover.fidelidade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discover.fidelidade.dao.UsuarioDAO;
import br.com.discover.fidelidade.model.Usuario;
import br.com.discover.fidelidade.rest.v1.resquest.UsuarioRequest;
import br.com.discover.fidelidade.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public Usuario recuperaUsuario(Integer idUsuario) {
		return usuarioDAO.recuperaUsuario(idUsuario);
	}
	
	@Override
	public void saveUsuario(UsuarioRequest usuario) {
		Usuario user = new Usuario();
		user.setIdUsuario(usuario.getIdUsuario());
		user.setNome(usuario.getNome());
		user.setCpf(usuario.getCpf());
		user.setDataNascimento(usuario.getDataNascimento());
		user.setEmail(usuario.getEmail());
		user.setTelefone(usuario.getTelefone());
		user.setLogin(usuario.getLogin());
		usuarioDAO.saveUsuario(user);
	};
	
	@Override
	public void updateUsuario(UsuarioRequest usuario) {
		Usuario user = new Usuario();
		user.setIdUsuario(usuario.getIdUsuario());
		user.setNome(usuario.getNome());
		user.setCpf(usuario.getCpf());
		user.setDataNascimento(usuario.getDataNascimento());
		user.setEmail(usuario.getEmail());
		user.setTelefone(usuario.getTelefone());
		user.setLogin(usuario.getLogin());
		usuarioDAO.updateUsuario(user);
	}
	
	@Override
	public void deleteUsuario(Integer idUsuario) {
		usuarioDAO.deleteUsuario(idUsuario);
	}
}
