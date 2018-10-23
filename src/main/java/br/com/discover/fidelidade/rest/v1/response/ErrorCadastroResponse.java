package br.com.discover.fidelidade.rest.v1.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ErrorCadastroResponse implements Serializable {
	private static final long serialVersionUID = -812418458449525009L;
	
	@ApiModelProperty(value = "campo", dataType = "string", example = "dataNascimento", notes = "Nome do campo")
	private String campo;
	
	@ApiModelProperty(value = "mensagem", dataType = "string", example = "Data de nascimento fornecida inválida", notes = "Mensagem de erro referente ao campo")
	private String mensagem;
	
	public ErrorCadastroResponse (String campo, String mensagem) {
		
		this.campo = campo;
		this.mensagem = mensagem;
	}
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
