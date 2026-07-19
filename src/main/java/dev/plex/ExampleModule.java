package dev.plex;

import dev.plex.command.ExampleCommand;
import dev.plex.listener.ExampleListener;
import dev.plex.module.PlexModule;

public class ExampleModule extends PlexModule
{
    @Override
    public void enable()
    {
        registerCommand(new ExampleCommand());
        registerListener(new ExampleListener(this));
        api().logging().info("{0} enabled with Plex API compatibility {1}",
                getPlexModuleFile().getName(),
                api().compatibility().version());
    }

    @Override
    public void disable()
    {
        // Unregistering listeners / commands is handled by Plex
    }
}
