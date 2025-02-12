package med.vall.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.vall.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {

}
