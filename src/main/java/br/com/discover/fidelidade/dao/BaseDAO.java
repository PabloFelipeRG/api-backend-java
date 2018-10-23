package br.com.discover.fidelidade.dao;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import br.com.discover.fidelidade.model.BaseModel;

public abstract class BaseDAO {
	
	private static Logger LOGGER = Logger.getLogger(BaseDAO.class.getName());
	
	@Autowired
	protected DataSource dataSource;
	
	
	/**
	 * Executa a query retornando apenas um registro. Retorna null caso não encontre.
	 * 
	 * @param sql Query a ser executada
	 * @param params Parâmetros a serem substituídos na query
	 * @param clazz Classe do objeto a ser retornado
	 * @return Objeto populado com o resultado da query
	 */
	protected <T extends BaseModel> T getOne(String sql, MapSqlParameterSource params, Class<T> clazz) {
		
		try {
			return new NamedParameterJdbcTemplate(dataSource).queryForObject(sql, params, this.getRowMapper(clazz));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	/**
	 * Executa a query retornando uma lista de registros. Retorna lista vazia caso não encontre.
	 * 
	 * @param sql Query a ser executada
	 * @param params Parâmetros a serem substituídos na query
	 * @param clazz Classe do objeto a ser retornado
	 * @return Lista populada com o resultado da query
	 */
	protected <T extends BaseModel> List<T> getMany(String sql, MapSqlParameterSource params, Class<T> clazz) {
		return new NamedParameterJdbcTemplate(dataSource).query(sql, params, this.getRowMapper(clazz));
	}
	
	
	/**
	 * Cria um RowMapper para a classe informada, baseado no BeanPropertyRowMapper. Também irá inspecionar a classe e mapear
	 * todos os atributos dela que sejam filhos de BaseModel. O método é recursivo, então o mapeamento ocorrerá em quantos
	 * níveis houver. 
	 * 
	 * @param clazz Tipo do objeto a ser mapeado
	 * @return RowMapper que é uma composição de BeanPropertyRowMapper(s) desta classe e de seus atributos.
	 */
	private <T extends BaseModel> RowMapper<T> getRowMapper(Class<T> clazz) {
		
		return new RowMapper<T>() {

			@Override
			@SuppressWarnings("unchecked")
			public T mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				T bean = new BeanPropertyRowMapper<T>(clazz).mapRow(rs, rowNum);
				
				try {
					BeanInfo info = Introspector.getBeanInfo(bean.getClass());
					for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
						if (BaseModel.class.isAssignableFrom(pd.getPropertyType())) {
							Object child = getRowMapper((Class<? extends BaseModel>) pd.getPropertyType()).mapRow(rs, rowNum);
							try {
								pd.getWriteMethod().invoke(bean, child);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								LOGGER.log(Level.SEVERE, "Erro ao invocar setter do atributo", e);
							}
						}
					}
				} catch (IntrospectionException e1) {
					LOGGER.log(Level.SEVERE, "Erro ao recupear informações do bean", e1);
				}
				
				return bean;
			}
		};
	}
	
	protected <T extends BaseModel> void save(String sql, MapSqlParameterSource params) {
		new NamedParameterJdbcTemplate(dataSource).update(sql, params);
	}
}
