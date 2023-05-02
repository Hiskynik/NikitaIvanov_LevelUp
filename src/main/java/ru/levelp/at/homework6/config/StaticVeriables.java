package ru.levelp.at.homework6.config;


import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources(
        {"classpath:properties"}
)
public interface StaticVeriables extends Config {
    @Key("access.token")
    String getAccessToken();

    @Key("gender")
    String getGender();

    @Key("status")
    String getStatus();

}
