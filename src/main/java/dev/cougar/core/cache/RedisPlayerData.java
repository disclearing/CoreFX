package dev.cougar.core.cache;

import com.google.gson.JsonObject;
import dev.cougar.core.util.TimeUtil;
import dev.cougar.core.util.json.JsonChain;

import java.util.UUID;

public class RedisPlayerData {

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LastAction getLastAction() {
        return lastAction;
    }

    public void setLastAction(LastAction lastAction) {
        this.lastAction = lastAction;
    }

    public String getLastSeenServer() {
        return lastSeenServer;
    }

    public void setLastSeenServer(String lastSeenServer) {
        this.lastSeenServer = lastSeenServer;
    }

    public long getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(long lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    private UUID uuid;
    private String username;
    private LastAction lastAction;
    private String lastSeenServer;
    private long lastSeenAt;
    private boolean online = false;

    public RedisPlayerData(JsonObject object) {
        this.uuid = UUID.fromString(object.get("uuid").getAsString());
        this.username = object.get("name").getAsString();
        this.lastAction = LastAction.valueOf(object.get("lastAction").getAsString());
        this.lastSeenServer = object.get("lastSeenServer").getAsString();
        this.lastSeenAt = object.get("lastSeenAt").getAsLong();
        if (!object.has("online")) {
            this.online = false;
        } else {
            this.online = object.get("online").getAsBoolean();
        }
    }

    public RedisPlayerData(UUID uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }

    public JsonObject getJson() {
        return new JsonChain()
            .addProperty("uuid", uuid.toString())
            .addProperty("name", username)
            .addProperty("lastAction", lastAction.name())
            .addProperty("lastSeenServer", lastSeenServer)
            .addProperty("lastSeenAt", lastSeenAt)
            .addProperty("online", online)
            .get();
    }

    public String getTimeAgo() {
        return TimeUtil.millisToRoundedTime(System.currentTimeMillis() - lastSeenAt) + " ago";
    }

    public enum LastAction {
        LEAVING_SERVER,
        JOINING_SERVER
    }

}
