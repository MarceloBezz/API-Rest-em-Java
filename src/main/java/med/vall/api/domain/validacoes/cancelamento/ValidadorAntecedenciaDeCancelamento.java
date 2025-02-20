package med.vall.api.domain.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.vall.api.domain.ValidacaoException;
import med.vall.api.domain.consulta.ConsultaRepository;
import med.vall.api.domain.consulta.DadosCancelamentoConsulta;

@Component
public class ValidadorAntecedenciaDeCancelamento implements ValidadorCancelamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosCancelamentoConsulta dados) {
        var agora = LocalDateTime.now();
        var consulta = repository.findById(dados.idConsulta()).get();

        var diferencaEmHoras = Duration.between(agora,consulta.getData()).toHours();

        if(diferencaEmHoras < 24) {
            throw new ValidacaoException("Cancelamento deve ser feito com no mínimo 24 horas de antecedência");
        }
    }
}
