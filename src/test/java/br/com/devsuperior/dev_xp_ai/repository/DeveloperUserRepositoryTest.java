package br.com.devsuperior.dev_xp_ai.repository;

import br.com.devsuperior.dev_xp_ai.entity.DeveloperUserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@DisplayName("DeveloperUserRepository")
class DeveloperUserRepositoryTest {

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

    private DeveloperUserEntity salvarUsuario(String email, String nickname) {
        return userRepository.save(new DeveloperUserEntity(null, "Dev Teste", email, nickname, "SP"));
    }

    @Nested
    @DisplayName("existsByEmailIgnoreCase")
    class ExistsByEmail {

        @Test
        @DisplayName("Deve retornar true quando email já existe")
        void deveRetornarTrueQuandoEmailExiste() {
            salvarUsuario("dev@example.com", "dev_nick");
            assertThat(userRepository.existsByEmailIgnoreCase("dev@example.com"), is(true));
        }

        @Test
        @DisplayName("Deve ser case-insensitive ao verificar email")
        void deveSercaseInsensitiveAoVerificarEmail() {
            salvarUsuario("dev@example.com", "dev_nick");
            assertThat(userRepository.existsByEmailIgnoreCase("DEV@EXAMPLE.COM"), is(true));
        }

        @Test
        @DisplayName("Deve retornar false quando email não existe")
        void deveRetornarFalseQuandoEmailNaoExiste() {
            assertThat(userRepository.existsByEmailIgnoreCase("naoexiste@example.com"), is(false));
        }
    }

    @Nested
    @DisplayName("existsByNicknameIgnoreCase")
    class ExistsByNickname {

        @Test
        @DisplayName("Deve retornar true quando nickname já existe")
        void deveRetornarTrueQuandoNicknameExiste() {
            salvarUsuario("dev@example.com", "dev_nick");
            assertThat(userRepository.existsByNicknameIgnoreCase("dev_nick"), is(true));
        }

        @Test
        @DisplayName("Deve ser case-insensitive ao verificar nickname")
        void deveSerCaseInsensitiveAoVerificarNickname() {
            salvarUsuario("dev@example.com", "dev_nick");
            assertThat(userRepository.existsByNicknameIgnoreCase("DEV_NICK"), is(true));
        }

        @Test
        @DisplayName("Deve retornar false quando nickname não existe")
        void deveRetornarFalseQuandoNicknameNaoExiste() {
            assertThat(userRepository.existsByNicknameIgnoreCase("nickquenaoexiste"), is(false));
        }
    }

    @Nested
    @DisplayName("save e findById")
    class SaveEFindById {

        @Test
        @DisplayName("Deve persistir e recuperar usuário por id")
        void devePersistirERecuperarUsuarioPorId() {
            DeveloperUserEntity saved = salvarUsuario("maria@example.com", "maria_dev");

            assertThat(saved.getId(), notNullValue());
            Optional<DeveloperUserEntity> found = userRepository.findById(saved.getId());
            assertThat(found.isPresent(), is(true));
            assertThat(found.get().getEmail(), is("maria@example.com"));
            assertThat(found.get().getNickname(), is("maria_dev"));
            assertThat(found.get().getUf(), is("SP"));
        }

        @Test
        @DisplayName("Deve retornar Optional vazio quando id não existe")
        void deveRetornarVazioQuandoIdNaoExiste() {
            Optional<DeveloperUserEntity> found = userRepository.findById(99999L);
            assertThat(found.isPresent(), is(false));
        }
    }

    @Nested
    @DisplayName("delete em cascata")
    class DeleteCascata {

        @Test
        @DisplayName("Deve apagar experiência associada ao deletar usuário")
        void deveApagarExperienciaAoDeletarUsuario() {
            DeveloperUserEntity user = salvarUsuario("cascade@example.com", "cascade_dev");
            experienceRepository.save(
                    new br.com.devsuperior.dev_xp_ai.entity.DeveloperExperienceEntity(
                            null, user.getId(), 3, "Java", true, "Spring Boot"));

            userRepository.deleteById(user.getId());

            assertThat(experienceRepository.findByUserId(user.getId()).isPresent(), is(false));
        }
    }
}

