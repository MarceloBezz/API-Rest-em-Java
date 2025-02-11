package med.vall.api.Medico;

import jakarta.validation.constraints.NotNull;
import med.vall.api.Endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco
) {

}
