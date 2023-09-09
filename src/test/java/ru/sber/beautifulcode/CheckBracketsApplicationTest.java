package ru.sber.beautifulcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Тестирование CheckBracketsApplication")
class CheckBracketsApplicationTest {

    @Test
    @DisplayName("Успешная загрузка контекста")
    void main() {
        Assertions.assertTrue(true);
    }
}