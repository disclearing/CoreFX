package dev.cougar.core.config;

import dev.cougar.core.config.impl.*;

public enum ConfigVersion {

    VERSION_1(1, new ConfigConversion1()),
    VERSION_2(2, new ConfigConversion2()),
    VERSION_3(3, new ConfigConversion3()),
    VERSION_4(4, new ConfigConversion4()),
    VERSION_5(5, new ConfigConversion5());

    private int number;
    private ConfigConversion conversion;

    ConfigVersion(int number, ConfigConversion conversion) {
        this.number = number;
        this.conversion = conversion;
    }

    public int getNumber() {
        return number;
    }

    public ConfigConversion getConversion() {
        return conversion;
    }
}
