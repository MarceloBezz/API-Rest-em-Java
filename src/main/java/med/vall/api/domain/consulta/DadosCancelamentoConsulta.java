package med.vall.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
    @NotNull
    Long idConsulta,

    @NotNull
    MotivoCancelamento motivo
) {

}
