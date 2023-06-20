package br.com.bene20.localizacao.domain.repository.specs;

import br.com.bene20.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {
  
  public static Specification<Cidade> nomeEquals(String value){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("nome"), value);
  }
  
  public static Specification<Cidade> propertyEquals(String property, Object value){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get(property), value);
  }
  
  public static Specification<Cidade> habitantesGreaterThan(Integer value){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.greaterThan(root.get("habitantes"), value);
  }
  
}
