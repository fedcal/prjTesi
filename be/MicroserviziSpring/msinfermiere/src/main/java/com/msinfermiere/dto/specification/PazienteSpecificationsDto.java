package com.msinfermiere.dto.specification;

import com.msinfermiere.entity.Paziente;
import org.springframework.data.jpa.domain.Specification;

public class PazienteSpecificationsDto {
    public static Specification<Paziente> hasIdPaziente(Integer idPaziente) {
        return (root, query, criteriaBuilder) ->
                idPaziente == null ? null : criteriaBuilder.equal(root.get("idPaziente"), idPaziente);
    }

    public static Specification<Paziente> hasNomePaziente(String nomePaziente) {
        return (root, query, criteriaBuilder) ->
                nomePaziente == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("nomePaziente")), "%" + nomePaziente.toLowerCase() + "%");
    }

    public static Specification<Paziente> hasCognomePaziente(String cognomePaziente) {
        return (root, query, criteriaBuilder) ->
                cognomePaziente == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("cognomePaziente")), "%" + cognomePaziente.toLowerCase() + "%");
    }

    public static Specification<Paziente> hasIdReparto(Integer idReparto) {
        return (root, query, criteriaBuilder) ->
                idReparto == null ? null : criteriaBuilder.equal(root.get("idReparto"), idReparto);
    }

    public static Specification<Paziente> hasDataNascita(String dataNascita) {
        return (root, query, criteriaBuilder) ->
                dataNascita == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("dataNascita")), "%" + dataNascita.toLowerCase() + "%");
    }

    public static Specification<Paziente> hasLuogoNascita(String luogoNascita) {
        return (root, query, criteriaBuilder) ->
                luogoNascita == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("luogoNascita")), "%" + luogoNascita.toLowerCase() + "%");
    }

    public static Specification<Paziente> hasProvinciaNascita(String provinciaNascita) {
        return (root, query, criteriaBuilder) ->
                provinciaNascita == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("provinciaNascita")), "%" + provinciaNascita.toLowerCase() + "%");
    }

    public static Specification<Paziente> hasContattoRiferimento(Integer contattoRiferimento) {
        return (root, query, criteriaBuilder) ->
                contattoRiferimento == null ? null : criteriaBuilder.equal(root.get("idPaziente"), contattoRiferimento);
    }
}
