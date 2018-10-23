package br.com.discover.fidelidade.rest.v1.response;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.discover.fidelidade.model.NotaFiscal;
import br.com.discover.fidelidade.rest.v1.resquest.NotaFiscalRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class NotaFiscalResponse implements Serializable {
	private static final long serialVersionUID = -7177683176953745905L;
	
	@ApiModelProperty(value = "idNotaFiscal", dataType = "number", example = "1", notes = "Id da nota fiscal")
	private Integer idNotaFiscal;
	
	@ApiModelProperty(value = "nomeEmpresa", dataType = "string", example = "Mc Donald's", notes = "Nome da empresa que emitiu a nota fiscal")
	private String nomeEmpresa;
	
	@ApiModelProperty(value = "cnpj", dataType = "string", example = "85638998000107", notes = "CNPJ da empresa que emitiu a nota fiscal")
	private String cnpj;
	
	@ApiModelProperty(value = "dataEmissao", dataType = "string", example = "2018-10-10 13:27:29", notes = "Data e hora da emissão da nota fiscal")
	private String dataEmissao;
	
	@ApiModelProperty(value = "valor", dataType = "number", example = "20.00", notes = "Valor emitido na nota fiscal")
	private BigDecimal valor;
	
	public NotaFiscalResponse(NotaFiscal notaFiscal) {
		
		if (notaFiscal == null) {
			return;
		}
		this.idNotaFiscal = notaFiscal.getIdNotaFiscal();
		this.nomeEmpresa = notaFiscal.getNomeEmpresa();
		this.cnpj = notaFiscal.getCnpj();
		this.dataEmissao = notaFiscal.getDataEmissao();
		this.valor = notaFiscal.getValor();
	}
	
	public NotaFiscalResponse(NotaFiscalRequest notaFiscal) {
		
		if (notaFiscal == null) {
			return;
		}
		this.idNotaFiscal = notaFiscal.getIdNotaFiscal();
		this.nomeEmpresa = notaFiscal.getNomeEmpresa();
		this.cnpj = notaFiscal.getCnpj();
		this.dataEmissao = notaFiscal.getDataEmissao();
		this.valor = notaFiscal.getValor();
	}
	
	public Integer getIdNotaFiscal() {
		return idNotaFiscal;
	}
	public void setIdNotaFiscal(Integer idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
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
