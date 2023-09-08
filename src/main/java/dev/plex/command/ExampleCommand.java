package dev.plex.command;

import dev.plex.command.annotation.CommandParameters;
import dev.plex.command.annotation.CommandPermissions;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@CommandParameters(name = "examplemodule", description = "An example command provided by Plex's example module")
@CommandPermissions(permission = "plex.module.command")
public class ExampleCommand extends PlexCommand
{
    @Override
    protected Component execute(@NotNull CommandSender commandSender, @Nullable Player player, @NotNull String[] strings)
    {
        return Component.text("Example module command");
    }
}
