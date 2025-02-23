package med.vall.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import med.vall.api.domain.consulta.DadosAgendamentoConsulta;
import med.vall.api.domain.endereco.DadosEndereco;
import med.vall.api.domain.endereco.Endereco;
import med.vall.api.domain.medico.DadosCadastroMedico;
import med.vall.api.domain.medico.DadosDetalhamentoMedico;
import med.vall.api.domain.medico.Especialidade;
import med.vall.api.domain.medico.Medico;
import med.vall.api.domain.medico.MedicoRepository;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoJSON;

    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações inválidas")
    @WithMockUser
    void testCadastrar_cenario01() throws Exception {
        var response = mockMvc.perform(post("/medicos")
                .contentType("application/json")
                .content("{}"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 201 quando informações válidas")
    @WithMockUser
    void testCadastrar_cenario02() throws Exception {
        var especialidade = Especialidade.DERMATOLOGIA;
        var endereco = new DadosEndereco(
                "00000000",
                "bairro",
                "21",
                "Brasilia",
                "DF",
                "null",
                "SP");
        var dadosMedico = new DadosCadastroMedico("Celinho",
                "emailteste@voll.med",
                "098765",
                especialidade,
                endereco,
                "11111111111");
        var dadosDetalhamento = new DadosDetalhamentoMedico(null,
                "Celinho",
                "098765",
                "11111111111",
                especialidade,
                new Endereco(endereco));

        when(repository.save(any())).thenReturn(new Medico(dadosMedico));

        var response = mockMvc.perform(post("/medicos")
                .contentType("application/json")
                .content(dadosCadastroJSON.write(dadosMedico).getJson()))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoJSON
                .write(dadosDetalhamento)
                .getJson();

        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}
