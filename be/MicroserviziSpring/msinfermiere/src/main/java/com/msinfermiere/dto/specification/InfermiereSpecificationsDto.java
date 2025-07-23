package com.msinfermiere.dto.specification;

import com.msinfermiere.entity.Infermiere;
import org.springframework.data.jpa.domain.Specification;

public class InfermiereSpecificationsDto {
    public static Specification<Infermiere> hasIdInfermiere(Integer idInfermiere) {
        return (root, query, criteriaBuilder) ->
                idInfermiere == null ? null : criteriaBuilder.equal(root.get("idInfermiere"), idInfermiere);
    }

    public static Specification<Infermiere> hasNomeInfermiere(String nomeInfermiere) {
        return (root, query, criteriaBuilder) ->
                nomeInfermiere == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("nomeInfermiere")), "%" + nomeInfermiere.toLowerCase() + "%");
    }

    public static Specification<Infermiere> hasCognomeInfermiere(String cognomeInfermiere) {
        return (root, query, criteriaBuilder) ->
                cognomeInfermiere == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("cognomeInfermiere")), "%" + cognomeInfermiere.toLowerCase() + "%");
    }

    public static Specification<Infermiere> hasIdReparto(Integer idReparto) {
        return (root, query, criteriaBuilder) ->
                idReparto == null ? null : criteriaBuilder.equal(root.get("idReparto"), idReparto);
    }

    public static Specification<Infermiere> hasTurmaInfermiere(String turmaInfermiere) {
        return (root, query, criteriaBuilder) ->
                turmaInfermiere == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("turmaInfermiere")), "%" + turmaInfermiere.toLowerCase() + "%");
    }
}
