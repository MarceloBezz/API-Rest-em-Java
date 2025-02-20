package med.vall.api.domain.validacoes.cancelamento;

import med.vall.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    
    void validar(DadosCancelamentoConsulta dados);
}
