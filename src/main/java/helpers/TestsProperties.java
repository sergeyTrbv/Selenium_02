package helpers;

import org.aeonbits.owner.Config;

/**
 * Интерфейс `TestsProperties` предназначен для загрузки конфигурационных свойств из нескольких
 * источников и предоставления доступа к ним.
 * <p>
 * Этот интерфейс расширяет интерфейс `Config`, который является частью API MicroProfile Config,
 * что позволяет получать конфигурационные свойства.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "system:env",
        "file:src/main/resources/tests.properties"})
public interface TestsProperties extends Config {
    @Config.Key("google.url")
    String googleUrl();

    @Config.Key("citilink.url")
    String citilinkUrl();
}