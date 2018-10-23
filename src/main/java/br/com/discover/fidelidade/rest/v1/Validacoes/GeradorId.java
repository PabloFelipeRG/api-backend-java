package br.com.discover.fidelidade.rest.v1.Validacoes;

public class GeradorId {
	
	private static final Integer LIMIT = 100000000;
	private static Integer last = 0;

	public static Integer getID() {
	  // 9 digits.
	  Integer id = (int) (System.currentTimeMillis() % LIMIT);
	  if ( id <= last ) {
	    id = (last + 1) % LIMIT;
	  }
	  return last = id;
	}
}
