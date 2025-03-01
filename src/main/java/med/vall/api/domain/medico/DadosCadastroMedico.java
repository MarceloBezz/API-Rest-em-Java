package med.vall.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vall.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank 
        String nome,

        @NotBlank 
        @Email 
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        DadosEndereco endereco,
        
        @NotBlank
        String telefone
        ) {

    public DadosCadastroMedico(String nome, String email, String telefone, String crm, Especialidade especialidade,
            DadosEndereco dadosEndereco) {
        this(nome, email, crm, especialidade, dadosEndereco, telefone);
    }
}
