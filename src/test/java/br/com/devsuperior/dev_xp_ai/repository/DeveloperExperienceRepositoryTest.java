package br.com.devsuperior.dev_xp_ai.repository;

import br.com.devsuperior.dev_xp_ai.entity.DeveloperExperienceEntity;
import br.com.devsuperior.dev_xp_ai.entity.DeveloperUserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@DisplayName("DeveloperExperienceRepository")
class DeveloperExperienceRepositoryTest {

    @Autowired
    private DeveloperUserRepository userRepository;

    @Autowired
    private DeveloperExperienceRepository experienceRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void limpar() {
        jdbcTemplate.execute("DELETE FROM tb_developer_experience");
        jdbcTemplate.execute("DELETE FROM tb_developer");
    }

    private DeveloperUserEntity salvarUsuario(String email, String nickname, String uf) {
        return userRepository.save(new DeveloperUserEntity(null, "Dev Teste", email, nickname, uf));
    }

    private DeveloperExperienceEntity salvarExperiencia(Long userId, String language, int anos) {
        return experienceRepository.save(
                new DeveloperExperienceEntity(null, userId, anos, language, true, "Spring Boot"));
    }

    @Nested
    @DisplayName("findByUserId")
    class FindByUserId {

        @Test
        @DisplayName("Deve retornar experiência quando userId existe")
        void deveRetornarExperienciaQuandoUserIdExiste() {
            DeveloperUserEntity user = salvarUsuario("exp@example.com", "exp_dev", "RJ");
            salvarExperiencia(user.getId(), "Java", 4);

            Optional<DeveloperExperienceEntity> result = experienceRepository.findByUserId(user.getId());

            assertThat(result.isPresent(), is(true));
            assertThat(result.get().getUserId(), is(user.getId()));
            assertThat(result.get().getPrimaryLanguage(), is("Java"));
            assertThat(result.get().getYearsOfExperience(), is(4));
        }

        @Test
        @DisplayName("Deve retornar Optional vazio quando userId não existe")
        void deveRetornarVazioQuandoUserIdNaoExiste() {
            Optional<DeveloperExperienceEntity> result = experienceRepository.findByUserId(99999L);
            assertThat(result.isPresent(), is(false));
        }
    }

    @Nested
    @DisplayName("findAllByFilters")
    class FindAllByFilters {

        @BeforeEach
        void inserirMassaDeDados() {
            DeveloperUserEntity userSP = salvarUsuario("sp@example.com", "dev_sp", "SP");
            DeveloperUserEntity userRJ = salvarUsuario("rj@example.com", "dev_rj", "RJ");
            DeveloperUserEntity userMG = salvarUsuario("mg@example.com", "dev_mg", "MG");

            salvarExperiencia(userSP.getId(), "Java", 5);
            salvarExperiencia(userRJ.getId(), "Python", 3);
            salvarExperiencia(userMG.getId(), "Java", 7);
        }

        @Test
        @DisplayName("Deve retornar todos quando filtros forem nulos")
        void deveRetornarTodosQuandoFiltrosNulos() {
            List<DeveloperExperienceEntity> result = experienceRepository.findAllByFilters(null, null);
            assertThat(result, hasSize(3));
        }

        @Test
        @DisplayName("Deve filtrar por UF corretamente")
        void deveFiltrarPorUf() {
            List<DeveloperExperienceEntity> result = experienceRepository.findAllByFilters("SP", null);
            assertThat(result, hasSize(1));
        }

        @Test
        @DisplayName("Deve filtrar por linguagem corretamente")
        void deveFiltrarPorLinguagem() {
            List<DeveloperExperienceEntity> result = experienceRepository.findAllByFilters(null, "Java");
            assertThat(result, hasSize(2));
        }

        @Test
        @DisplayName("Deve filtrar por linguagem de forma case-insensitive")
        void deveFiltrarPorLinguagemCaseInsensitive() {
            List<DeveloperExperienceEntity> result = experienceRepository.findAllByFilters(null, "java");
            assertThat(result, hasSize(2));
        }

        @Test
        @DisplayName("Deve filtrar por UF e linguagem combinados")
        void deveFiltrarPorUfELinguagemCombinados() {
            List<DeveloperExperienceEntity> result = experienceRepository.findAllByFilters("RJ", "Python");
            assertThat(result, hasSize(1));
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando filtro não encontra resultado")
        void deveRetornarVazioQuandoSemResultado() {
            List<DeveloperExperienceEntity> result = experienceRepository.findAllByFilters(null, "COBOL");
            assertThat(result, is(empty()));
        }
    }

    @Nested
    @DisplayName("save e atualização")
    class SaveEAtualizacao {

        @Test
        @DisplayName("Deve atualizar yearsOfExperience sem alterar outros campos")
        void deveAtualizarYearsOfExperienceSemAlterar() {
            DeveloperUserEntity user = salvarUsuario("upd@example.com", "upd_dev", "PR");
            DeveloperExperienceEntity exp = salvarExperiencia(user.getId(), "Kotlin", 2);

            exp.setYearsOfExperience(10);
            DeveloperExperienceEntity updated = experienceRepository.save(exp);

            assertThat(updated.getYearsOfExperience(), is(10));
            assertThat(updated.getPrimaryLanguage(), is("Kotlin"));
            assertThat(updated.getUserId(), is(user.getId()));
        }
    }
}

