package br.com.discover.fidelidade.rest.v1.Validacoes;

public class ValidaCEP {
	
	public static boolean isCep(String cep) {
		return cep.matches("^[0-9]{8}$");
	} 
}
