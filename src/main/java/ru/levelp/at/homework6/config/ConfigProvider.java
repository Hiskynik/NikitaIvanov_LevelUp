package ru.levelp.at.homework6.config;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigCache;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigProvider {

    public static StaticVeriables staticVariables() {
        return ConfigCache.getOrCreate(StaticVeriables.class);
    }
}
