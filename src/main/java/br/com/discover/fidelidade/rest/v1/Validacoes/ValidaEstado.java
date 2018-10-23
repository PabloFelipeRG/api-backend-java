package br.com.discover.fidelidade.rest.v1.Validacoes;

public class ValidaEstado {
	
	static String siglasEstados[]={"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
	
	public static boolean isEstado(String estado) {
		boolean status = false;

		for (String x: siglasEstados) {
			if (estado.equals(x)) {
				status = true;
			}
		}
		return status;
	}
}
