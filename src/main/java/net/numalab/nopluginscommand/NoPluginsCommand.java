package net.numalab.nopluginscommand;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.stream.Stream;

public final class NoPluginsCommand extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onCommandUse(PlayerCommandPreprocessEvent event) {
        String text = event.getMessage().toLowerCase();
        String command = text.split(" ")[0];
        boolean denied = Stream.of("?",
                        "help",
                        "pl",
                        "about",
                        "version",
                        "ver",
                        "plugins",
                        "bukkit:?",
                        "bukkit:help",
                        "bukkit:pl",
                        "bukkit:about",
                        "bukkit:version",
                        "bukkit:ver",
                        "bukkit:plugins",
                        "minecraft:help",
                        "minecraft:pl",
                        "minecraft:plugins",
                        "minecraft:about",
                        "minecraft:version",
                        "minecraft:ver")
                .map(s -> "/" + s)
                .anyMatch(command::equalsIgnoreCase);
        if (denied)
            event.setCancelled(true);
    }
}
