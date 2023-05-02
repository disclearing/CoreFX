package dev.cougar.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import dev.cougar.core.cache.RedisCache;
import dev.cougar.core.config.ConfigValidation;
import dev.cougar.core.config.utils.BasicConfigurationFile;
import dev.cougar.core.staff.StaffModule;
import dev.cougar.core.util.duration.Duration;
import dev.cougar.core.util.duration.DurationTypeAdapter;
import dev.cougar.core.util.item.ItemBuilder;
import mx.fxmxgragfx.api.menu.MenuListener;
import mx.fxmxgragfx.api.command.CommandHandler;
import mx.fxmxgragfx.api.handler.Handler;
import mx.fxmxgragfx.api.module.Module;
import mx.fxmxgragfx.api.module.ModulesManager;
import dev.cougar.node.Node;
import mx.fxmxgragfx.api.scoreboard.ScoreboardEngine;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Core extends JavaPlugin {

    public static final Gson GSON = new Gson();
    public static final Type LIST_STRING_TYPE = new TypeToken<List<String>>() {
    }.getType();

    private static Core instance;

    private BasicConfigurationFile mainConfig;
    private CommandHandler commandHandler;
    public Node node;
    public ScoreboardEngine scoreboardEngine;

    private MongoDatabase mongoDatabase;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    private JedisPool jedisPool;
    private RedisCache redisCache;

    public BasicConfigurationFile getMainConfig() {
        return mainConfig;
    }

    @Override
    public void onEnable() {
        instance = Core.getPlugin(Core.class);
        mainConfig = new BasicConfigurationFile(this, "config");

        new ConfigValidation(mainConfig.getFile(), mainConfig.getConfiguration(), 4).check();
        new Handler(MenuListener.class);
        loadMongo();
        loadRedis();
        scoreboardEngine = new ScoreboardEngine();
        scoreboardEngine.load();
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        redisCache = new RedisCache(this);

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        /*
        honcho.registerTypeAdapter(Group.class, new GroupTypeAdapter());
        honcho.registerTypeAdapter(Profile.class, new ProfileTypeAdapter());
        honcho.registerTypeAdapter(Duration.class, new DurationTypeAdapter());
        honcho.registerTypeAdapter(ChatColor.class, new ChatColorTypeAdapter());

         */

        node = new Node("core",
            mainConfig.getString("REDIS.HOST"),
            mainConfig.getInteger("REDIS.PORT"),
            mainConfig.getBoolean("REDIS.AUTHENTICATION.ENABLED") ?
                mainConfig.getString("REDIS.AUTHENTICATION.PASSWORD") : null
        );
        ItemBuilder.registerGlow();
        ModulesManager.registerModule(StaffModule.class);
        commandHandler = new CommandHandler();
        commandHandler.load();
        commandHandler.registerParameterType(Duration.class, new DurationTypeAdapter());
        commandHandler.registerAll(this);
    }

    @Override
    public void onDisable() {
        try {
            jedisPool.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Stop Modules
        ModulesManager.getModules().forEach(Module::stop);
    }

    private void loadMongo() {
        mongoDatabase = MongoClients.create(mainConfig.getString("MONGO.CONNECTION_STRING")).getDatabase("Core");
    }

    private void loadRedis() {
        jedisPool = new JedisPool(mainConfig.getString("REDIS.HOST"), mainConfig.getInteger("REDIS.PORT"));

        if (mainConfig.getBoolean("REDIS.AUTHENTICATION.ENABLED")) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.auth(mainConfig.getString("REDIS.AUTHENTICATION.PASSWORD"));
            }
        }
    }

    public static Core getInstance() {
        return instance;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public static Collection<? extends Player> getOnlinePlayers() {
        Collection<Player> collection = new ArrayList<>();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            collection.add(player);
        }
        return collection;
    }
}
