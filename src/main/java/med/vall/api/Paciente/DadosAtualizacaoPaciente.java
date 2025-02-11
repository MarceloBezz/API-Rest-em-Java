package med.vall.api.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.vall.api.Endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
    @NotNull
    Long id,
    String nome,
    String telefone,
    @Valid DadosEndereco endereco
) {

}
