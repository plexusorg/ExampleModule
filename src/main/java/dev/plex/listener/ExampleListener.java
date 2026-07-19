package dev.plex.listener;

import dev.plex.ExampleModule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class ExampleListener extends PlexListener
{
    private final ExampleModule module;

    public ExampleListener(ExampleModule module)
    {
        this.module = module;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        player.sendMessage(module.api().messages().miniMessage(
                "<gold>ExampleModule is awake!</gold> "
                        + "<click:run_command:'/examplemodule sparkle'>"
                        + "<hover:show_text:'<gray>Click for a tiny celebration</gray>'>"
                        + "<aqua>[Try a sparkle]</aqua>"
                        + "</hover></click>"));
    }
}
