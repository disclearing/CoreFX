package dev.cougar.core.config.impl;

import dev.cougar.core.config.ConfigConversion;
import dev.cougar.core.config.ConfigConversion;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigConversion5 implements ConfigConversion {

    @Override
    public void convert(File file, FileConfiguration fileConfiguration) {
        fileConfiguration.set("CONFIG_VERSION", 5);
        fileConfiguration.set("GLOBAL_WHITELIST.KICK_MAINTENANCE", "&cThe server is currently in maintenance.\\nCheck our discord for more announcements!");
        fileConfiguration.set("GLOBAL_WHITELIST.KICK_CLOSED_TESTING", "&cYou are not whitelisted. To gain early access, you can\\npurchase an eligible rank &7(&6Gold+&7) &con our store.\\n&fhttps://store.sergi.us");

        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
