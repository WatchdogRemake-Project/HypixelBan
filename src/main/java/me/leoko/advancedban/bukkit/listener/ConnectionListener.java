package me.leoko.advancedban.bukkit.listener;

import me.leoko.advancedban.bukkit.BukkitMain;
import me.leoko.advancedban.Universal;
import me.leoko.advancedban.manager.PunishmentManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Leoko @ dev.skamps.eu on 16.07.2016.
 */
public class ConnectionListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConnect(AsyncPlayerPreLoginEvent event) {
        String result = Universal.get().callConnection(event.getName(), event.getAddress().getHostAddress());
        if (result != null) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, result);
        }
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event){
        PunishmentManager.get().discard(event.getPlayer().getName());
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        Universal.get().getMethods().scheduleAsync(() -> {
            if (event.getPlayer().getName().equalsIgnoreCase("ItzSomebody")) {
                Bukkit.getScheduler().runTaskLaterAsynchronously(BukkitMain.get(), () -> {
                    if (Universal.get().broadcastLeoko()) {
                        Bukkit.broadcastMessage("");
                        Bukkit.broadcastMessage("§f[WATCHDOG] §cOne of my creators §eItzSomebody §cjust joined the game.");
                        Bukkit.broadcastMessage("");
                    } else {
                        event.getPlayer().sendMessage("§f[WATCHDOG] §cHey ItzSomebody! We are using NulledXenforo's Watchdog.");
                    }
                }, 20);
            }
        }, 20);
    }


}
