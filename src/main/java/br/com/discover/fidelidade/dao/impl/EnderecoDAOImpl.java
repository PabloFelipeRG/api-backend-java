package br.com.discover.fidelidade.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.discover.fidelidade.dao.BaseDAO;
import br.com.discover.fidelidade.dao.EnderecoDAO;
import br.com.discover.fidelidade.model.Endereco;

@Repository
public class EnderecoDAOImpl extends BaseDAO implements EnderecoDAO {

	@Override
	public Endereco recuperaEndereco(Integer idEndereco) {

	 String sql = "    SELECT ID_ENDERECO       as idEndereco, "
	 		    + "           ID_USUARIO        as idUsuario,"
				+ "           LOGRADOURO        as logradouro, "
				+ "           NUMERO            as numero, "
				+ "           CEP               as cep, "
				+ "           BAIRRO            as bairro,"
				+ "           ESTADO            as estado, "
				+ "           CIDADE            as cidade, "
				+ "           COMPLEMENTO       as complemento "
				+ "           FROM ENDERECO"
				+ "       WHERE ID_ENDERECO = :idEndereco";

		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idEndereco", idEndereco);
		return this.getOne(sql, params, Endereco.class);
	}

	@Override
	public void saveEndereco(Endereco endereco) {
		String sql = "INSERT INTO ENDERECO (ID_ENDERECO, ID_USUARIO, LOGRADOURO, NUMERO, CEP, BAIRRO, ESTADO, CIDADE, COMPLEMENTO) "
				+ "VALUES (:idEndereco, :idUsuario, :logradouro, :numero, :cep, :bairro, :estado, :cidade, :complemento);";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idUsuario", endereco.getIdUsuario());
		params.addValue("idEndereco", endereco.getIdEndereco());
		params.addValue("logradouro", endereco.getLogradouro());
		params.addValue("numero", endereco.getNumero());
		params.addValue("cep", endereco.getCep());
		params.addValue("bairro", endereco.getBairro());
		params.addValue("estado", endereco.getEstado());
		params.addValue("cidade", endereco.getCidade());
		params.addValue("complemento", endereco.getComplemento());
		this.save(sql, params);
	}

	@Override
	public void updateEndereco(Endereco endereco) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder builder = new StringBuilder("UPDATE ENDERECO SET ");

		if (endereco.getIdEndereco() == null) {
			return;
		} else {
			params.addValue("idEndereco", endereco.getIdEndereco());
		}

		if (endereco.getLogradouro() != null && !endereco.getLogradouro().isEmpty()) {
			builder.append("LOGRADOURO = :logradouro, ");
			params.addValue("logradouro", endereco.getLogradouro());
		}
		if (endereco.getNumero() != null && !endereco.getNumero().isEmpty()) {
			builder.append("NUMERO = :numero, ");
			params.addValue("numero", endereco.getNumero());
		}
		if (endereco.getCep() != null && !endereco.getCep().isEmpty()) {
			builder.append("CEP = :cep, ");
			params.addValue("cep", endereco.getCep());
		}
		if (endereco.getBairro() != null && !endereco.getBairro().isEmpty()) {
			builder.append("BAIRRO = :bairro, ");
			params.addValue("bairro", endereco.getBairro());
		}
		if (endereco.getEstado() != null && !endereco.getEstado().isEmpty()) {
			builder.append("ESTADO = :estado, ");
			params.addValue("estado", endereco.getEstado());
		}
		if (endereco.getCidade() != null && !endereco.getCidade().isEmpty()) {
			builder.append("CIDADE = :cidade, ");
			params.addValue("cidade", endereco.getCidade());
		}
		if (endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()) {
			builder.append("COMPLEMENTO = :complemento, ");
			params.addValue("complemento", endereco.getComplemento());
		}
		
		String condicao = "WHERE ID_ENDERECO = :idEndereco;";
		builder.append(condicao);
		Integer indice = builder.length() - (condicao.length() + 2);
		builder.deleteCharAt(indice);
		this.save(builder.toString(), params);
	}

	@Override
	public void deleteEndereco(Integer idEndereco) {
		String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = :idEndereco;";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idEndereco", idEndereco);
		this.save(sql, params);
	}
	
	@Override
	public List<Endereco> recuperaEnderecos(Integer idUsuario) {
		
		String sql = "    SELECT ID_ENDERECO       as idEndereco, "
	 		       + "           ID_USUARIO        as idUsuario,"
				   + "           LOGRADOURO        as logradouro, "
				   + "           NUMERO            as numero, "
				   + "           CEP               as cep, "
				   + "           BAIRRO            as bairro,"
				   + "           ESTADO            as estado, "
				   + "           CIDADE            as cidade, "
			  	   + "           COMPLEMENTO       as complemento "
			   	   + "           FROM ENDERECO"
				   + "       WHERE ID_USUARIO = :idUsuario";
		
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idUsuario", idUsuario);
		return this.getMany(sql, params, Endereco.class);
	}
}
