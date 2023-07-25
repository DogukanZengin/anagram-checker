package com.io.beyonnex.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.io.beyonnex.model.InputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AnagramCheckerServiceTest {

    private AnagramCheckerService service;

    @BeforeEach
    void setup(){
        service = new AnagramCheckerService();
    }

    @Test
    void should_return_true_when_anagram(){
        var input = new InputDto("anagram", "grmanaa");
        var result = service.isAnagram(input);

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({
            "true, restful, fluster",
            "true, cheater, teacher",
            "true, santa, satan",
            "false, table, tablo",
            "false, chair, hairf",
            "true, Deductions, Discounted",
            "true, Discriminator, Doctrinairism"
    })
    void csvInputRandomTests(boolean expected, String first, String second) {
        var input = new InputDto(first, second);
        var result = service.isAnagram(input);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void should_return_true_when_anagram_with_uppercase(){
        var input = new InputDto("aNaGram", "grManAa");
        var result = service.isAnagram(input);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void should_return_false_when_length_are_different(){
        var input = new InputDto("test", "longer_then_test");
        var result = service.isAnagram(input);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void should_return_false_when_input_is_null(){
        var input = new InputDto("test", null);
        var result = service.isAnagram(input);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void should_return_false_when_input_contains_non_letters(){
        var input = new InputDto("321t", "t123");
        var result = service.isAnagram(input);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void should_return_false_when_not_anagram(){
        var input = new InputDto("anag", "gram");
        var result = service.isAnagram(input);

        assertThat(result).isEqualTo(false);
    }
}