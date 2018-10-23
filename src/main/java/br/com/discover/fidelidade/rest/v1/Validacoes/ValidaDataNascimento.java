package br.com.discover.fidelidade.rest.v1.Validacoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidaDataNascimento {
	
	public static boolean isDataNascimento(String dataString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			Date dataParseada = sdf.parse(dataString);
			return (true);
		} catch (ParseException e) {
			return (false);
		}
	}
}

