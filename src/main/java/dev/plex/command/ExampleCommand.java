package dev.plex.command;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExampleCommand extends SimplePlexCommand
{
    public ExampleCommand()
    {
        super(command("examplemodule")
                .description("An example command provided by Plex's example module")
                .usage("/<command> [info | sparkle]")
                .aliases("example")
                .build());
    }

    @Override
    protected Component execute(@NotNull CommandSender sender, @Nullable Player player, @NotNull String[] args)
    {
        if (args.length == 0)
        {
            return help();
        }

        return switch (args[0].toLowerCase(Locale.ROOT))
        {
            case "info" -> info();
            case "sparkle" -> sparkle(player);
            default -> usage();
        };
    }

    @Override
    protected @NotNull List<String> suggestions(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args)
    {
        if (args.length == 1)
        {
            return List.of("info", "sparkle");
        }
        return List.of();
    }

    private Component help()
    {
        return mmString("""
                <gold><bold>Plex Example Module</bold></gold>
                <gray>Try <yellow>/example info</yellow> or <light_purple>/example sparkle</light_purple>.</gray>
                """);
    }

    private Component info()
    {
        int compatibility = api().compatibility().version();
        int loadedModules = api().modules().loadedModules().size();
        return mmString("<gold>Plex API compatibility:</gold> <yellow>" + compatibility
                + "</yellow> <dark_gray>•</dark_gray> <gold>Loaded modules:</gold> <yellow>"
                + loadedModules + "</yellow>");
    }

    private Component sparkle(@Nullable Player player)
    {
        if (player == null)
        {
            return mmString("<red>Only players can sparkle.</red>");
        }

        AtomicInteger bursts = new AtomicInteger();
        api().scheduler().runEntityTimer(player, task ->
        {
            int burst = bursts.incrementAndGet();
            Location origin = player.getLocation().add(0, 1, 0);
            player.getWorld().spawnParticle(Particle.END_ROD, origin, 12, 0.6, 0.7, 0.6, 0.02);
            player.playSound(origin, Sound.BLOCK_NOTE_BLOCK_CHIME, 0.7f, 1.2f + burst * 0.15f);
            if (burst >= 4)
            {
                task.cancel();
            }
        }, null, 1L, 5L);

        return mmString("<rainbow>A tiny celebration!</rainbow>");
    }
}