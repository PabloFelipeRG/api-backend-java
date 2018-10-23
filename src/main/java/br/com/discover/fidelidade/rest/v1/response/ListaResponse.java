package br.com.discover.fidelidade.rest.v1.response;

import java.util.List;

public class ListaResponse<T> {

	private List<T> list;
	
	public ListaResponse(List<T> t) {
		
		if (t == null) {
			return;
		}
		this.list = t;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> t) {
		this.list = t;
	}
}
