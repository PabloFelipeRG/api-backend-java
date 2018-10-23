package br.com.discover.fidelidade.rest.v1.resquest;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class EnderecoRequest implements Serializable {
	private static final long serialVersionUID = -7884179567219624813L;

	@ApiModelProperty(value = "idEndereco", dataType = "number", example = "1", notes = "Id do endereço")
	private Integer idEndereco;
	
	@ApiModelProperty(value = "idUsuario", dataType = "number", example = "1", notes = "Id do usuário")
	private Integer idUsuario;
	
	@ApiModelProperty(value = "logradouro", dataType = "string", example = "Rua Almeida Dias", notes = "Nome do logradouro")
	private String logradouro;
	
	@ApiModelProperty(value = "numero", dataType = "string", example = "1230", notes = "Número da residência")
	private String numero;
	
	@ApiModelProperty(value = "cep", dataType = "string", example = "02828000", notes = "Número do CEP")
	private String cep;
	
	@ApiModelProperty(value = "bairro", dataType = "string", example = "Lapa", notes = "Nome do bairro")
	private String bairro;
	
	@ApiModelProperty(value = "estado", dataType = "string", example = "SP", notes = "UF do estado")
	private String estado;
	
	@ApiModelProperty(value = "cidade", dataType = "string", example = "São Paulo", notes = "Nome da cidade")
	private String cidade;
	
	@ApiModelProperty(value = "complemento", dataType = "string", example = "Próximo a padaria", notes = "Complemento do endereço")
	private String complemento;
	
	public EnderecoRequest () {};
	
	public Integer getIdEndereco() {
		return idEndereco;
	}
	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
