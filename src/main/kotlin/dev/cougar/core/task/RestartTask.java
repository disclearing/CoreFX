package dev.cougar.core.task;

import mx.fxmxgragfx.api.async.Async;
import dev.cougar.core.Core;
import dev.cougar.core.util.TimeUtil;
import dev.cougar.core.util.string.CC;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class RestartTask extends BukkitRunnable {

    private int seconds;
    private boolean whitelist;

    public RestartTask(int seconds, boolean whitelist) {
        this.seconds = seconds;
        this.whitelist = whitelist;
    }

    @Override
    public synchronized void cancel() throws IllegalStateException {
        super.cancel();
        Bukkit.getServer().setWhitelist(whitelist);
    }

    @Override
    public void run() {
        if (this.seconds <= -5) {
            Bukkit.broadcastMessage(CC.translate("&aThe server will restart now"));

            cancel();
            Bukkit.getServer().setWhitelist(whitelist);
            Bukkit.shutdown();
            return;
        }

        if (this.seconds == 0) {
            Bukkit.getServer().setWhitelist(true);
            new Async(this::kickPlayers);
        }

        this.broadcast();

        this.seconds--;
    }

    private void broadcast() {
        if (this.seconds <= 0) return;

        boolean shouldBroadcast = this.seconds <= 10 || this.seconds == 15 || this.seconds == 30 || this.seconds == 45 || this.seconds % 60 == 0;

        if (!shouldBroadcast) return;

        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(CC.translate("&r &e&lSERVER RESTARTING"));
        Bukkit.broadcastMessage(CC.translate("&r &f" + TimeUtil.formatIntoDetailedString(seconds) + " remaining"));
        Bukkit.broadcastMessage("");
    }

    private void kickPlayers() {
        String kickReason = CC.translate("&r\n&r &e&lSERVER RESTARTING\n&r &fYou are being sent to lobby\n&r &r");

        Core.getOnlinePlayers().forEach(player -> {
            player.kickPlayer(kickReason);
        });
    }

}
