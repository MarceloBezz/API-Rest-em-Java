package med.vall.api.domain.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vall.api.domain.ValidacaoException;
import med.vall.api.domain.consulta.DadosAgendamentoConsulta;
import med.vall.api.domain.paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente()); 
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Paciente não está ativo");
        }
    }
}
