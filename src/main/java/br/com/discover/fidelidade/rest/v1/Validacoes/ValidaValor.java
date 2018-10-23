package br.com.discover.fidelidade.rest.v1.Validacoes;

import java.math.BigDecimal;

public class ValidaValor {
	
	public static boolean isValor(BigDecimal valor) {
		String valorString = valor.toString();
		
		if (valorString.matches("[0-9]+([.][0-9]{2})?")) {
			return (true);
		} else {
			return (false);
		}
	}
}
