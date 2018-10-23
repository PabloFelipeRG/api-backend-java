package br.com.discover.fidelidade.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.discover.fidelidade.dao.BaseDAO;
import br.com.discover.fidelidade.dao.UsuarioDAO;
import br.com.discover.fidelidade.model.Usuario;

@Repository
public class UsuarioDAOImpl extends BaseDAO implements UsuarioDAO {

	@Override
	public Usuario recuperaUsuario(Integer idUsuario) {
	 String sql = "    SELECT ID_USUARIO        as idUsuario, "
	 		    + "           NOME              as nome,"
				+ "           CPF               as cpf, "
				+ "           DATA_NASC         as dataNascimento,"
				+ "           EMAIL             as email, "
				+ "           TELEFONE          as telefone,"
				+ "           LOGIN             as login, "
				+ "           SALDO             as saldo"
				+ "           FROM USUARIO "
				+ "      WHERE ID_USUARIO = :idUsuario";

		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idUsuario", idUsuario);
		return this.getOne(sql, params, Usuario.class);
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		String sql = "INSERT INTO USUARIO (ID_USUARIO, NOME, CPF, DATA_NASC, EMAIL, TELEFONE, LOGIN) "
				+ "			VALUES (:idUsuario, :nome, :cpf, :dataNascimento, :email, :telefone, :login);";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("nome", usuario.getNome());
		params.addValue("idUsuario", usuario.getIdUsuario());
		params.addValue("cpf", usuario.getCpf());
		params.addValue("dataNascimento", usuario.getDataNascimento());
		params.addValue("email", usuario.getEmail());
		params.addValue("telefone", usuario.getTelefone());
		params.addValue("login", usuario.getLogin());
		this.save(sql, params);
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder builder = new StringBuilder("UPDATE USUARIO SET ");

		if (usuario.getIdUsuario() == null) {
			return;
		} else {
			params.addValue("idUsuario", usuario.getIdUsuario());
		}

		if (usuario.getNome() != null && !usuario.getNome().isEmpty()) {
			builder.append("NOME = :nome, ");
			params.addValue("nome", usuario.getNome());
		}
		if (usuario.getCpf() != null && !usuario.getCpf().isEmpty()) {
			builder.append("CPF = :cpf, ");
			params.addValue("cpf", usuario.getCpf());
		}
		if (usuario.getDataNascimento() != null && !usuario.getDataNascimento().isEmpty()) {
			builder.append("DATA_NASC = :dataNascimento, ");
			params.addValue("dataNascimento", usuario.getDataNascimento());
		}
		if (usuario.getEmail() != null && usuario.getEmail().isEmpty()) {
			builder.append("EMAIL = :email, ");
			params.addValue("email", usuario.getEmail());
		}
		if (usuario.getTelefone() != null && !usuario.getTelefone().isEmpty()) {
			builder.append("TELEFONE = :telefone, ");
			params.addValue("telefone", usuario.getTelefone());
		}
		if (usuario.getLogin() != null && !usuario.getLogin().isEmpty()) {
			builder.append("LOGIN = :login, ");
			params.addValue("login", usuario.getLogin());
		}
		
		String condicao = "WHERE ID_USUARIO = :idUsuario;";
		builder.append(condicao);
		Integer indice = builder.length() - (condicao.length() + 2);
		builder.deleteCharAt(indice);
		this.save(builder.toString(), params);
	}

	@Override
	public void deleteUsuario(Integer idUsuario) {
		String sql = "DELETE FROM USUARIO WHERE ID_USUARIO = :idUsuario;";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idUsuario", idUsuario);
		this.save(sql, params);
	}
}
