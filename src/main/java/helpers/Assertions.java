package helpers;

import io.qameta.allure.Step;


/**
 * Класс {@code Assertions} предоставляет удобные методы для выполнения проверок в тестах.
 * Эти методы обертывают стандартные утверждения из JUnit, добавляя дополнительные возможности,
 * такие как логирование шагов теста.
 *
 * @author sergeyTrbv
 */
public class Assertions {

    /**
     * Проверяет, что переданное условие истинно. Если условие ложно, выбрасывается исключение с указанным сообщением.
     * Этот метод также логирует шаг теста с сообщением о проверке.
     *
     * @param condition условие, которое должно быть истинным
     * @param message   сообщение, которое будет использовано в исключении, если условие ложно
     */
    @Step("Проверяем что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }

    @Step("Проверяем что нет ошибки: {message}")
    public static void assertFalse(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertFalse(condition, message);
    }

    @Step("Проверяем что URL соответствует ожидаемому: {expectedUrl}")
    public static void assertEquals(String expectedUrl, String currentUrl, String message) {
        org.junit.jupiter.api.Assertions.assertEquals(expectedUrl, currentUrl, message);
    }
}
