package helpers;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс `Properties` предоставляет статический экземпляр интерфейса `TestsProperties`, который используется
 * для доступа к конфигурационным свойствам.
 *
 * @author SergeyTrbv
 */
public class Properties {

    /**
     * Статический экземпляр интерфейса `TestsProperties`, созданный с помощью `ConfigFactory`.
     */
    public static TestsProperties testsProperties = ConfigFactory.create(TestsProperties.class);
}