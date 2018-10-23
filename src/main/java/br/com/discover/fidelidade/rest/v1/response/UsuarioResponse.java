package br.com.discover.fidelidade.rest.v1.response;

import java.io.Serializable;

import br.com.discover.fidelidade.model.Usuario;
import br.com.discover.fidelidade.rest.v1.resquest.UsuarioRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UsuarioResponse implements Serializable {

	private static final long serialVersionUID = -7258869381653779677L;
	
	@ApiModelProperty(value = "idUsuario", dataType = "number", example = "1", notes = "Id do usuário")
	private Integer idUsuario;
	
	@ApiModelProperty(value = "nome", dataType = "string", example = "José da Silva", notes = "Nome do usuário")
	private String nome;
	
	@ApiModelProperty(value = "email", dataType = "string", example = "jose.silva@discover.com.br", notes = "E-mail do usuário")
	private String email;
	
	public UsuarioResponse(Usuario usuario) {
		
		if (usuario == null) {
			return;
		}
		this.idUsuario = usuario.getIdUsuario();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		
	}
	
	public UsuarioResponse(UsuarioRequest usuario) {
		if (usuario == null) {
			return;
		}
		this.idUsuario = usuario.getIdUsuario();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
