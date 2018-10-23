package br.com.discover.fidelidade.model;

import java.io.Serializable;

/**
 * Interface marcadora para o utilitário BaseDAO. Utilize esta interface em todos os Beans que serão
 * utilizados para popular resultados de queries executadas por classes filhas de BaseDAO, pois esta
 * permite que os atributos do Bean sejam mapeados e populados corretamente.
 */
public interface BaseModel extends Serializable {

}
