package br.com.discover.fidelidade.model;

import java.math.BigDecimal;

public class Credito implements BaseModel {
	private static final long serialVersionUID = -7477229434756202991L;

	private Integer idCredito;
	private BigDecimal valor;
	private String data;
	private Integer idUsuario;
	private Integer idNotaFiscal;
	
	
	public Integer getIdCredito() {
		return idCredito;
	}
	public void setIdCredito(Integer idCredito) {
		this.idCredito = idCredito;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdNotaFiscal() {
		return idNotaFiscal;
	}
	public void setIdNotaFiscal(Integer idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
