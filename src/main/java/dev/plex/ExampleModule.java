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
        registerListener(new ExampleListener());
    }

    @Override
    public void disable()
    {
        // Unregistering listeners / commands is handled by Plex
    }
}
