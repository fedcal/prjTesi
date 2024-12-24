package com.msinfermiere.entity.operazione;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "referto_operazione")
@Entity
public class RefertoOperazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_referto")
    private Integer idReferto;

    @Column(name = "tipologia")
    private String tipologia;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data_referto")
    private String dataReferto;
}
