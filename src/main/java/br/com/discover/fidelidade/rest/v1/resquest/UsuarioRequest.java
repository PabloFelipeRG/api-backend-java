package br.com.discover.fidelidade.rest.v1.resquest;

import java.io.Serializable;

public class UsuarioRequest implements Serializable {
	private static final long serialVersionUID = -1792318778736159339L;
	
	private Integer idUsuario;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String email;
	private String telefone;
	private String login;
	
	public UsuarioRequest() {};

	public UsuarioRequest(UsuarioRequest usuario) {
		if (usuario == null) {
			return;
		}
		this.idUsuario = usuario.getIdUsuario();
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
