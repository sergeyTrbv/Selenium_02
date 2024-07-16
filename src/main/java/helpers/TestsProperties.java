package helpers;

import org.aeonbits.owner.Config;

/**
 * Интерфейс `TestsProperties` предназначен для загрузки конфигурационных свойств из нескольких
 * источников и предоставления доступа к ним.
 * <p>
 * Этот интерфейс расширяет интерфейс `Config`, который является частью API MicroProfile Config,
 * что позволяет получать конфигурационные свойства.
 *
 * @author SergeyTrbv
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"})
public interface TestsProperties extends Config {

    /**
     * Возвращает стратегию загрузки страницы.
     *
     * @return Стратегия загрузки страницы в виде строки.
     */
    @Config.Key("page.load.strategy")
    String pageLoadStrategy();

    /**
     * Возвращает таймаут неявного ожидания в миллисекундах.
     *
     * @return Таймаут неявного ожидания.
     */
    @Config.Key("implicit.wait.timeout")
    long implicitWaitTimeout();

    /**
     * Возвращает таймаут загрузки страницы в миллисекундах.
     *
     * @return Таймаут загрузки страницы.
     */
    @Config.Key("page.load.timeout")
    long pageLoadTimeout();

    /**
     * Возвращает таймаут выполнения скрипта в миллисекундах.
     *
     * @return Таймаут выполнения скрипта.
     */
    @Config.Key("script.timeout")
    long scriptTimeout();

    /**
     * Возвращает таймаут загрузки страницы для возможностей (capabilities) в миллисекундах.
     *
     * @return Таймаут загрузки страницы для возможностей.
     */
    @Config.Key("page.load.timeout.capabilities")
    int pageLoadTimeoutCapabilities();
}