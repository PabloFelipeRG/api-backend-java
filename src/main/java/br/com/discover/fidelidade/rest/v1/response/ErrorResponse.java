package br.com.discover.fidelidade.rest.v1.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ErrorResponse implements Serializable  {

	private static final long serialVersionUID = -7988256899719491976L;
	
	@ApiModelProperty(value = "mensagem", dataType = "string", example = "Ocorreu um erro interno", notes = "Mensagem de erro")
	private String mensagem;
	
	@ApiModelProperty(value = "descricao", dataType = "string", example = "java.lang.NullPointerException", notes = "Descrição do erro")
	private String descricao;
	
	public ErrorResponse(String mensagem, String descricao) {
		
		this.mensagem = mensagem;
		this.descricao = descricao;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
