package br.com.devsuperior.dev_xp_ai.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("TextNormalizer")
class TextNormalizerTest {

    @Nested
    @DisplayName("toTitleCase")
    class ToTitleCase {

        @Test
        @DisplayName("Deve capitalizar primeira letra de cada palavra")
        void deveCapitalizarPrimeiraLetraDeCadaPalavra() {
            assertThat(TextNormalizer.toTitleCase("maria da silva"), is("Maria Da Silva"));
        }

        @Test
        @DisplayName("Deve converter texto todo em maiúsculo para title case")
        void deveConverterMaiusculoParaTitleCase() {
            assertThat(TextNormalizer.toTitleCase("JOAO CARLOS"), is("Joao Carlos"));
        }

        @Test
        @DisplayName("Deve normalizar espaços extras entre palavras")
        void deveNormalizarEspacosExtras() {
            assertThat(TextNormalizer.toTitleCase("  maria   silva  "), is("Maria Silva"));
        }

        @Test
        @DisplayName("Deve retornar string vazia quando entrada for nula")
        void deveRetornarVazioQuandoNulo() {
            assertThat(TextNormalizer.toTitleCase(null), is(""));
        }

        @Test
        @DisplayName("Deve retornar string vazia quando entrada for em branco")
        void deveRetornarVazioQuandoEmBranco() {
            assertThat(TextNormalizer.toTitleCase("   "), is(""));
        }

        @Test
        @DisplayName("Deve processar palavra única corretamente")
        void deveProcessarPalavraUnica() {
            assertThat(TextNormalizer.toTitleCase("java"), is("Java"));
        }
    }

    @Nested
    @DisplayName("toLowerCaseTrimmed")
    class ToLowerCaseTrimmed {

        @Test
        @DisplayName("Deve converter para minúsculo e remover espaços")
        void deveConverterParaMinusculoERemoverEspacos() {
            assertThat(TextNormalizer.toLowerCaseTrimmed("  MARIA@EXAMPLE.COM  "), is("maria@example.com"));
        }

        @Test
        @DisplayName("Deve retornar string vazia quando entrada for nula")
        void deveRetornarVazioQuandoNulo() {
            assertThat(TextNormalizer.toLowerCaseTrimmed(null), is(""));
        }

        @Test
        @DisplayName("Deve manter texto já em minúsculo inalterado")
        void deveManterMinusculoInaletrado() {
            assertThat(TextNormalizer.toLowerCaseTrimmed("abc"), is("abc"));
        }
    }

    @Nested
    @DisplayName("toUpperCaseTrimmed")
    class ToUpperCaseTrimmed {

        @Test
        @DisplayName("Deve converter para maiúsculo e remover espaços")
        void deveConverterParaMaiusculoERemoverEspacos() {
            assertThat(TextNormalizer.toUpperCaseTrimmed("  sp  "), is("SP"));
        }

        @Test
        @DisplayName("Deve retornar string vazia quando entrada for nula")
        void deveRetornarVazioQuandoNulo() {
            assertThat(TextNormalizer.toUpperCaseTrimmed(null), is(""));
        }

        @Test
        @DisplayName("Deve manter texto já em maiúsculo inalterado")
        void deveManterMaiusculoInaletrado() {
            assertThat(TextNormalizer.toUpperCaseTrimmed("RJ"), is("RJ"));
        }
    }

    @Nested
    @DisplayName("serializeSkills")
    class SerializeSkills {

        @Test
        @DisplayName("Deve serializar lista de skills em CSV")
        void deveSerializarListaEmCsv() {
            assertThat(TextNormalizer.serializeSkills(List.of("Java", "Spring Boot", "Docker")),
                    is("Java,Spring Boot,Docker"));
        }

        @Test
        @DisplayName("Deve retornar string vazia quando lista for nula")
        void deveRetornarVazioQuandoNulo() {
            assertThat(TextNormalizer.serializeSkills(null), is(""));
        }

        @Test
        @DisplayName("Deve serializar lista com um único item")
        void deveSerializarListaComUmItem() {
            assertThat(TextNormalizer.serializeSkills(List.of("Kotlin")), is("Kotlin"));
        }

        @Test
        @DisplayName("Deve remover espaços externos de cada skill ao serializar")
        void deveRemoverEspacosExternosDeSkills() {
            assertThat(TextNormalizer.serializeSkills(List.of("  Java  ", " Docker ")),
                    is("Java,Docker"));
        }
    }

    @Nested
    @DisplayName("deserializeSkills")
    class DeserializeSkills {

        @Test
        @DisplayName("Deve deserializar CSV de skills em lista")
        void deveDeserializarCsvEmLista() {
            List<String> result = TextNormalizer.deserializeSkills("Java,Spring Boot,Docker");
            assertThat(result, hasSize(3));
            assertThat(result, contains("Java", "Spring Boot", "Docker"));
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando CSV for nulo")
        void deveRetornarListaVaziaQuandoNulo() {
            assertThat(TextNormalizer.deserializeSkills(null), is(empty()));
        }

        @Test
        @DisplayName("Deve retornar lista vazia quando CSV for em branco")
        void deveRetornarListaVaziaQuandoEmBranco() {
            assertThat(TextNormalizer.deserializeSkills("   "), is(empty()));
        }

        @Test
        @DisplayName("Deve remover espaços externos de cada item ao deserializar")
        void deveRemoverEspacosExternosAoDeserializar() {
            List<String> result = TextNormalizer.deserializeSkills(" Java , Docker ");
            assertThat(result, contains("Java", "Docker"));
        }

        @Test
        @DisplayName("Deve deserializar lista com um único item")
        void deveDeserializarUmUnicoItem() {
            List<String> result = TextNormalizer.deserializeSkills("Kotlin");
            assertThat(result, hasSize(1));
            assertThat(result, contains("Kotlin"));
        }
    }
}

