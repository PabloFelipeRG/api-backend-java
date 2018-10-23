package br.com.discover.fidelidade.rest.v1.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorCadastro implements Serializable {
	private static final long serialVersionUID = 1400181030342875155L;
	
	private List<ErrorCadastroResponse> exceptionList;

	@JsonCreator
	public ErrorCadastro (@JsonProperty("exceptionList") List<ErrorCadastroResponse> newExceptionList) {

		this.exceptionList = newExceptionList;
	};
	
	public ErrorCadastro() {};
	
	public List<ErrorCadastroResponse> getExceptionList() {
		return exceptionList;
	}

	public void setExceptionList(List<ErrorCadastroResponse> exceptionList) {
		this.exceptionList = exceptionList;
	}
}
