package br.com.discover.fidelidade.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.discover.fidelidade.dao.BaseDAO;
import br.com.discover.fidelidade.dao.NotaFiscalDAO;
import br.com.discover.fidelidade.model.NotaFiscal;

@Repository
public class NotaFiscalDAOImpl extends BaseDAO implements NotaFiscalDAO {

	@Override
	public NotaFiscal recuperaNotaFiscal(Integer idNotaFiscal) {
		String sql =  "    SELECT ID_NOTA_FISCAL    as idNotaFiscal, "
		 		    + "           ID_USUARIO        as idUsuario,"
					+ "           NOME_EMPRESA      as nomeEmpresa,"
					+ "           CNPJ              as cnpj,"
					+ "           DATA_EMISSAO      as dataEmissao, "
					+ "           VALOR             as valor "
					+ "           FROM NOTA_FISCAL "
					+ "      WHERE ID_NOTA_FISCAL = :idNotaFiscal";

		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idNotaFiscal", idNotaFiscal);
		return this.getOne(sql, params, NotaFiscal.class);
	}
	
	@Override
	public void saveNotaFiscal(NotaFiscal notaFiscal) {
		String sql = "INSERT INTO NOTA_FISCAL (ID_NOTA_FISCAL, ID_USUARIO, NOME_EMPRESA, CNPJ, DATA_EMISSAO, VALOR) "
				+ "			VALUES (:idNotaFiscal, :idUsuario, :nomeEmpresa, :cnpj, :dataEmissao, :valor);";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idUsuario", notaFiscal.getIdUsuario());
		params.addValue("idNotaFiscal", notaFiscal.getIdNotaFiscal());
		params.addValue("nomeEmpresa", notaFiscal.getNomeEmpresa());
		params.addValue("cnpj", notaFiscal.getCnpj());
		params.addValue("dataEmissao", notaFiscal.getDataEmissao());
		params.addValue("valor", notaFiscal.getValor());
		this.save(sql, params);
	}
	
	@Override
	public void updateNotaFiscal(NotaFiscal notaFiscal) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		StringBuilder builder = new StringBuilder("UPDATE NOTA_FISCAL SET ");

		if (notaFiscal.getIdNotaFiscal() == null) {
			return;
		} else {
			params.addValue("idNotaFiscal", notaFiscal.getIdNotaFiscal());
		}

		if (notaFiscal.getNomeEmpresa() != null && !notaFiscal.getNomeEmpresa().isEmpty()) {
			builder.append("NOME_EMPRESA = :nomeEmpresa, ");
			params.addValue("nomeEmpresa", notaFiscal.getNomeEmpresa());
		}
		if (notaFiscal.getCnpj() != null && !notaFiscal.getCnpj().isEmpty()) {
			builder.append("CNPJ = :cnpj, ");
			params.addValue("cnpj", notaFiscal.getCnpj());
		}
		if (notaFiscal.getDataEmissao() != null && !notaFiscal.getDataEmissao().isEmpty()) {
			builder.append("DATA_EMISSAO = :dataEmissao, ");
			params.addValue("dataEmissao", notaFiscal.getDataEmissao());
		}
		if (notaFiscal.getValor() != null) {
			builder.append("VALOR = :valor, ");
			params.addValue("valor", notaFiscal.getValor());
		}
		
		String condicao = "WHERE ID_NOTA_FISCAL = :idNotaFiscal;";
		builder.append(condicao);
		Integer indice = builder.length() - (condicao.length() + 2);
		builder.deleteCharAt(indice);
		this.save(builder.toString(), params);
	}
	
	@Override
	public void deleteNotaFiscal(Integer idNotaFiscal) {
		String sql = "DELETE FROM NOTA_FISCAL WHERE ID_NOTA_FISCAL = :idNotaFiscal;";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idNotaFiscal", idNotaFiscal);
		this.save(sql, params);
	}
	
	@Override
	public List<NotaFiscal> recuperaNotasFiscais(Integer idUsuario) {
		
		String sql = "    SELECT ID_NOTA_FISCAL    as idNotaFiscal, "
	 		       + "           ID_USUARIO        as idUsuario,"
				   + "           NOME_EMPRESA      as nomeEmpresa, "
				   + "           CNPJ              as cnpj, "
				   + "           DATA_EMISSAO      as dataEmissao, "
				   + "           VALOR             as valor "
			   	   + "           FROM NOTA_FISCAL"
				   + "       WHERE ID_USUARIO = :idUsuario";
		
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("idUsuario", idUsuario);
		return this.getMany(sql, params, NotaFiscal.class);
	}
}
