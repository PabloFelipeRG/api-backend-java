package br.com.discover.fidelidade.rest.v1.resquest;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class NotaFiscalRequest implements Serializable {
	private static final long serialVersionUID = 5682320523900095732L;
	
	@ApiModelProperty(value = "idNotaFiscal", dataType = "number", example = "1", notes = "Id do nota fiscal")
	private Integer idNotaFiscal;
	
	@ApiModelProperty(value = "idUsuario", dataType = "number", example = "1", notes = "Id do usuário")
	private Integer idUsuario;
	
	@ApiModelProperty(value = "nomeEmpresa", dataType = "string", example = "Mc Donald's", notes = "Nome da empresa que emitiu a nota fiscal")
	private String nomeEmpresa;
	
	@ApiModelProperty(value = "cnpj", dataType = "string", example = "85638998000107", notes = "CNPJ da empresa que emitiu a nota fiscal")
	private String cnpj;
	
	@ApiModelProperty(value = "dataEmissao", dataType = "string", example = "2018-10-10 13:27:29", notes = "Data e hora da emissão da nota fiscal")
	private String dataEmissao;
	
	@ApiModelProperty(value = "valor", dataType = "number", example = "20.00", notes = "Valor emitido na nota fiscal")
	private BigDecimal valor;
	
	
	public Integer getIdNotaFiscal() {
		return idNotaFiscal;
	}
	public void setIdNotaFiscal(Integer idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
