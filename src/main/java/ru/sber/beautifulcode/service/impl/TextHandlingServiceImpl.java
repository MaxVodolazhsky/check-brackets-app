package ru.sber.beautifulcode.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.beautifulcode.model.CheckingResult;
import ru.sber.beautifulcode.model.Text;
import ru.sber.beautifulcode.service.TextHandlingService;
import ru.sber.beautifulcode.service.TextValidator;

@Service
public class TextHandlingServiceImpl implements TextHandlingService {
    private final TextValidator textValidator;

    public TextHandlingServiceImpl(TextValidator textValidator) {
        this.textValidator = textValidator;
    }

    @Override
    public CheckingResult checkText(Text text) {
        String rawText = text.getText();
        return new CheckingResult()
                .isCorrect(textValidator.validate(rawText));
    }
}
