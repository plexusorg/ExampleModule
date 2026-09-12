package dev.plex.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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
    protected void configureCommand(LiteralArgumentBuilder<CommandSourceStack> command)
    {
        command.executes(context -> executeCommand(context, (sender, player) -> help()));
        command.then(word("action")
                .suggests((context, builder) -> suggestMatching(builder, List.of("info", "sparkle")))
                .executes(context -> executeCommand(context,
                        (sender, player) -> executeTyped(player, string(context, "action"))))
                .then(greedyString("ignored").executes(context -> executeCommand(context,
                        (sender, player) -> executeTyped(player, string(context, "action"))))));
    }

    private Component executeTyped(@Nullable Player player, String action)
    {
        return switch (action.toLowerCase(Locale.ROOT))
        {
            case "info" -> info();
            case "sparkle" -> sparkle(player);
            default -> usage();
        };
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
        int compatibility = api().apiCompatibilityVersion();
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
        ownTask(player.getScheduler().runAtFixedRate(taskOwner(), task ->
        {
            int burst = bursts.incrementAndGet();
            Location origin = player.getLocation().add(0, 1, 0);
            player.getWorld().spawnParticle(Particle.END_ROD, origin, 12, 0.6, 0.7, 0.6, 0.02);
            player.playSound(origin, Sound.BLOCK_NOTE_BLOCK_CHIME, 0.7f, 1.2f + burst * 0.15f);
            if (burst >= 4)
            {
                task.cancel();
            }
        }, null, 1L, 5L));

        return mmString("<rainbow>A tiny celebration!</rainbow>");
    }
}
