package br.com.bene20.localizacao.domain.repository.specs;

import br.com.bene20.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public abstract class CidadeSpecs {
  
  public static Specification<Cidade> nomeEqual(String value){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("nome"), value);
  }
  
  public static Specification<Cidade> IdEqual(Long value){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get("id"), value);
  }
  
  public static Specification<Cidade> nomeiLike(String value){
    System.out.println("like usado: %"+value+"%".toUpperCase());
    return (root, query, cb) -> 
            cb.like(cb.upper(root.get("nome")),
                    ("%"+value+"%").toUpperCase());
  }
  
  public static Specification<Cidade> propertyEquals(String property, Object value){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.equal(root.get(property), value);
  }
  
  public static Specification<Cidade> habitantesGreaterThan(Long value){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.greaterThan(root.get("habitantes"), value);
  }
  
  public static Specification<Cidade> habitantesBetween(Long minValue, Long maxValue){
    return (root, query, criteriaBuilder) -> 
            criteriaBuilder.between(root.get("habitantes"), minValue, maxValue);
  }
  
}
