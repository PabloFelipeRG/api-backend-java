package br.com.discover.fidelidade.model;

import java.math.BigDecimal;

public class NotaFiscal implements BaseModel{
	private static final long serialVersionUID = 7630511816792823698L;
	
	private Integer idNotaFiscal;
	private Integer idUsuario;
	private String nomeEmpresa;
	private String cnpj;
	private String dataEmissao;
	private BigDecimal valor;
	
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
