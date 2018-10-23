package br.com.discover.fidelidade.rest.v1.Validacoes;

public class ValidaTelefone {
	
	public static boolean isTelefone(String telefone) {
		return telefone.matches("^(10)|([1-9][1-9])[2-9][0-9]{3}[0-9][0-9]{4}$") ||
	            telefone.matches("^[0-9][0-9][2-9][0-9]{3}[0-9]{4}$");
	}
}
