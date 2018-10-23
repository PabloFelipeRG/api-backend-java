package br.com.discover.fidelidade.model;

import java.math.BigDecimal;

public class Debito implements BaseModel {
	private static final long serialVersionUID = -8141532755123766394L;
	
	private Integer idDebito;
	private BigDecimal valor;
	private String data;
	private Integer idUsuario;
	private Integer idPremio;
	
	
	public Integer getIdDebito() {
		return idDebito;
	}
	public void setIdDebito(Integer idDebito) {
		this.idDebito = idDebito;
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
	public Integer getIdPremio() {
		return idPremio;
	}
	public void setIdPremio(Integer idPremio) {
		this.idPremio = idPremio;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
