package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * Класс {@code DataProvider} предоставляет данные для тестовых методов.
 *
 * @author sergeyTrbv
 */
public class DataProvider {

    /**
     * Метод {@code providerCheckingLaptop} для поставки данных о ноутбуках в тесты.
     * Возвращает поток аргументов, содержащий информацию о компании-поставщике, описании,
     * категории и URL страницы ноутбуков.
     *
     * @return поток аргументов с информацией о ноутбуке
     */
    public static Stream<Arguments> providerCheckingLaptop() {
        return Stream.of(
                Arguments.of("citilink", "Ситилинк - интернет-магазин техники, электроники ...",
                        "Ноутбуки", "https://www.citilink.ru/catalog/noutbuki/?ref=mainmenu","20000","120000")
        );
    }
}
