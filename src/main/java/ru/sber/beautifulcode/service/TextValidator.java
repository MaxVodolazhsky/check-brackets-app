package ru.sber.beautifulcode.service;

/**
 * Компонент для валидации текста
 */
public interface TextValidator {

    /**
     * Метод для проверки баланса скобок ("{}", "[]", "()")
     *
     * @param text входящий параметр
     * @return результат валидации
     */
    boolean validate(String text);
}
