package ru.sber.beautifulcode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.beautifulcode.api.CheckBracketsApi;
import ru.sber.beautifulcode.model.CheckingResult;
import ru.sber.beautifulcode.model.Text;
import ru.sber.beautifulcode.service.TextHandlingService;

@RestController
@RequestMapping("/api")
public class CheckBracketsController implements CheckBracketsApi {

    private final TextHandlingService textHandlingService;

    public CheckBracketsController(TextHandlingService textHandlingService) {
        this.textHandlingService = textHandlingService;
    }

    @Override
    public ResponseEntity<CheckingResult> checkBrackets(Text text) {
        return ResponseEntity.ok(textHandlingService.checkText(text));
    }
}
