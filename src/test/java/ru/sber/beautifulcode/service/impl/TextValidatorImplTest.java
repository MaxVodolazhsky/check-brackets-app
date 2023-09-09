package ru.sber.beautifulcode.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.sber.beautifulcode.service.TextValidator;

import java.util.stream.Stream;

class TextValidatorImplTest {

    private TextValidator textValidator;

    private static Stream<Arguments> provideTexts() {
        return Stream.of(
                Arguments.of("()", false),
                Arguments.of("{sdad)", false),
                Arguments.of("(asd)", true),
                Arguments.of("(1)", true),
                Arguments.of("(        )", false)
        );
    }

    @BeforeEach
    void setUp() {
        textValidator = new TextValidatorImpl();
    }

    @ParameterizedTest
    @MethodSource("provideTexts")
    void validate(String input, boolean expected) {
        Assertions.assertEquals(expected, textValidator.validate(input));
    }
}