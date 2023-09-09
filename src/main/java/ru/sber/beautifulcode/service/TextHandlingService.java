package ru.sber.beautifulcode.service;

import ru.sber.beautifulcode.model.CheckingResult;
import ru.sber.beautifulcode.model.Text;

/**
 * Сервис для проверки текста
 */
public interface TextHandlingService {

    /**
     * Метод для проверки текста
     *
     * @param text Текст
     * @return Результат проверки текста
     */
    CheckingResult checkText(Text text);
}
