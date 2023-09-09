package ru.sber.beautifulcode.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sber.beautifulcode.model.Text;
import ru.sber.beautifulcode.service.TextHandlingService;
import ru.sber.beautifulcode.service.TextValidator;

@ExtendWith(MockitoExtension.class)
class TextHandlingServiceImplTest {

    private static String EXAMPLE_TEXT = "example";

    @Mock
    private TextValidator textValidator;

    private TextHandlingService textHandlingService;

    @Captor
    private ArgumentCaptor<String> captorText;

    @BeforeEach
    void setUp() {
        textHandlingService = new TextHandlingServiceImpl(textValidator);
    }

    @Test
    void checkText() {
        Text example = new Text("example");

        Assertions.assertDoesNotThrow(() -> textHandlingService.checkText(example));
        Mockito.verify(textValidator, Mockito.times(1)).validate(captorText.capture());
        Assertions.assertEquals(EXAMPLE_TEXT, captorText.getValue());
    }
}