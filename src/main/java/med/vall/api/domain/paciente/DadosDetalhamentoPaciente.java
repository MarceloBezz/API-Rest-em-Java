package med.vall.api.domain.paciente;

import med.vall.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String cpf, String telefone, String email, Endereco endereco) {
    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail(), paciente.getEndereco());
    }
    
}
