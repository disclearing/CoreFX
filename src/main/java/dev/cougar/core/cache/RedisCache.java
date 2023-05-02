package dev.cougar.core.cache;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import redis.clients.jedis.Jedis;
import dev.cougar.core.Core;

import java.util.UUID;

public class RedisCache {

    private Core kore;

    public RedisCache(Core kore) {
        this.kore = kore;
    }

    /**
     * Gets the UUID of a username
     *
     * @param name the name of a player.
     * @return The {@link UUID} of the player if found in the Cache. Null otherwise.
     */
    public UUID getUuid(String name) {
        if (kore.getServer().isPrimaryThread()) {
            throw new IllegalStateException("Cannot query on main thread (Redis profile cache)");
        }

        try (Jedis jedis = kore.getJedisPool().getResource()) {
            String uuid = jedis.hget("uuid-cache:name-to-uuid", name.toLowerCase());

            if (uuid != null) {
                return UUID.fromString(uuid);
            }
        } catch (Exception e) {
            Bukkit.getLogger().info("Could not connect to redis");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Updates the username and UUID of a player in the cache
     *
     * @param name The username of the player
     * @param uuid The {@link UUID} of the player
     * @throws IllegalStateException if executed in the server primary {@link Thread}
     */
    public void updateNameAndUUID(String name, UUID uuid) {
        if (Bukkit.isPrimaryThread()) {
            throw new IllegalStateException("Cannot query redis on main thread!");
        }

        try (Jedis jedis = kore.getJedisPool().getResource()) {
            jedis.hset("uuid-cache:name-to-uuid", name.toLowerCase(), uuid.toString());
            jedis.hset("uuid-cache:uuid-to-name", uuid.toString(), name);
        }
    }

    /**
     * Gets the {@link RedisPlayerData} data of a player
     *
     * @param uuid The {@link UUID} of the player
     * @return The {@link RedisPlayerData} of the player
     * @throws IllegalStateException if executed in the server primary {@link Thread}
     */
    public RedisPlayerData getPlayerData(UUID uuid) {
        if (Bukkit.isPrimaryThread()) {
            throw new IllegalStateException("Cannot query redis on main thread!");
        }

        try (Jedis jedis = kore.getJedisPool().getResource()) {
            String data = jedis.hget("player-data", uuid.toString());

            if (data == null) {
                return null;
            }

            try {
                JsonObject dataJson = new JsonParser().parse(data).getAsJsonObject();
                return new RedisPlayerData(dataJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Updates the {@link RedisPlayerData} of a player
     *
     * @param playerData the {@link RedisPlayerData} of the player
     */
    public void updatePlayerData(RedisPlayerData playerData) {
        try (Jedis jedis = kore.getJedisPool().getResource()) {
            jedis.hset("player-data", playerData.getUuid().toString(), playerData.getJson().toString());
        }
    }

}
