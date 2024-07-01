package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {

    public static Stream<Arguments> providerCheckingLaptop(){
        return Stream.of(
                Arguments.of("citilink", "Ситилинк - интернет-магазин техники, электроники ...",
                        "Ноутбуки" ,"https://www.citilink.ru/catalog/noutbuki/?ref=mainmenu")
        );
    }
}
