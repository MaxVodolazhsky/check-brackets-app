package ru.sber.beautifulcode.service.impl;

import org.springframework.stereotype.Component;
import ru.sber.beautifulcode.service.TextValidator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TextValidatorImpl implements TextValidator {

    private static final Map<Character, Character> storeBrackets = Map.ofEntries(
            Map.entry(')', '('),
            Map.entry('}', '{'),
            Map.entry(']', '[')
    );

    private static final Pattern SEARCH_TEXT_PATTERN = Pattern.compile("[(\\[{](.*?)[)\\]}]");


    @Override
    public boolean validate(String text) {
        return isValidBrackets(text) && isValidTextWithBrackets(text);
    }

    private boolean isValidBrackets(String text) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : text.toCharArray()) {
            if (storeBrackets.containsValue(c)) {
                stack.push(c);
            } else if (storeBrackets.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != storeBrackets.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isValidTextWithBrackets(String text) {
        final Matcher matcher = SEARCH_TEXT_PATTERN.matcher(text);
        while (matcher.find()) {
            if (matcher.group(1).isBlank()) {
                return false;
            }
        }
        return true;
    }
}
