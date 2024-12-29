package com.mspazienti.dto.paziente.relation;

import com.mspazienti.dto.paziente.CartellaClinicaDto;
import com.mspazienti.dto.paziente.InfermiereDto;
import com.mspazienti.dto.paziente.MedicinaleDto;
import lombok.Data;

@Data
public class MedicinaleSottoministrazioneDto {
    private Integer idRelazione;
    private InfermiereDto infermiere;
    private MedicinaleDto medicinale;
    private CartellaClinicaDto cartellaClinica;
}
