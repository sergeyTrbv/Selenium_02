package helpers;

import io.qameta.allure.Step;


/**
 * Класс {@code Assertions} предоставляет методы для выполнения проверок в тестах.
 * Эти методы обертывают стандартные утверждения из JUnit, добавляя дополнительные возможности,
 * такие как логирование шагов теста.
 *
 * @author sergeyTrbv
 */
public class Assertions {

    /**
     * Проверяет переданное условие и выводит сообщение при ошибке.
     *
     * @param condition условие для проверки
     * @param message   сообщение при ошибке
     */
    @Step("Проверяем что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }

    /**
     * Проверяет переданное условие (должно быть ложным) и выводит сообщение при ошибке.
     *
     * @param condition условие для проверки
     * @param message   сообщение при ошибке
     */
    @Step("Проверяем что нет ошибки: {message}")
    public static void assertFalse(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertFalse(condition, message);
    }

    /**
     * Проверяет, что текущий URL соответствует ожидаемому и выводит сообщение при ошибке.
     *
     * @param expectedUrl ожидаемый URL
     * @param currentUrl  текущий URL
     * @param message     сообщение при ошибке
     */
    @Step("Проверяем что URL соответствует ожидаемому: {expectedUrl}")
    public static void assertEquals(String expectedUrl, String currentUrl, String message) {
        org.junit.jupiter.api.Assertions.assertEquals(expectedUrl, currentUrl, message);
    }
}
