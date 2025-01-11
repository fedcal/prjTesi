package com.bff.dto.response.mspaziente;

import com.bff.dto.response.mspaziente.relation.*;
import lombok.Data;

import java.util.Set;

@Data
public class CartellaClinicaDto {
    private Integer idCartellaClinica;
    private String gruppoSanguigno;
    private Set<DiagnosiDto> diagnosi;
    private Set<MalattiaCartellaDto> malattiaCartella;
    private Set<VisitaMedicaCartellaDto> visitaMedicaCartella;
    private Set<OperazioneCartellaDto> operazioneCartella;
    private Set<MedicinalePrescrizioneDto> medicinalePrescrizione;
    private Set<VisitaPrescrizioneDto> visitaPrescrizione;
    private Set<VisitaSottoministrazioneMedicoDto> visitaSottoministrazioneMedico;
    private Set<VisitaSottoministrazioneInfermiereDto> visitaSottoministrazioneInfermiere;
    private Set<MedicinaleCartellaDto> medicinaleCartella;
    private Set<OperazionePrescrizioneDto> operazionePrescrizione;
    private Set<MedicinaleSottoministrazioneDto> medicinaleSottoministrazione;
}
