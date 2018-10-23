package br.com.discover.fidelidade.model;

public class Premio implements BaseModel {
	private static final long serialVersionUID = -2023727806132667645L;
	
	private Integer idPremio;
	private String descricao;
	private Double preco;
	private Integer quantidadeDisponivel;
	
	
	public Integer getIdPremio() {
		return idPremio;
	}
	public void setIdPremio(Integer idPremio) {
		this.idPremio = idPremio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}
	public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
