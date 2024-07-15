package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static helpers.Properties.testsProperties;

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
                Arguments.of("https://www.citilink.ru/",
                        "Ситилинк - интернет-магазин техники, электроники, товаров для дома и ремонта",
                        "Каталог товаров",
                        "Ноутбуки и компьютеры",
                        "Ноутбуки",
                        "20000",
                        "100000",
                        List.of("hp", "lenovo"),
                        12),
                Arguments.of("https://www.citilink.ru/",
                        "Ситилинк - интернет-магазин техники, электроники, товаров для дома и ремонта",
                        "Каталог товаров",
                        "Смартфоны и планшеты",
                        "Смартфоны",
                        "30000",
                        "100000",
                        List.of("honor"),
                        4)
        );
    }
}
