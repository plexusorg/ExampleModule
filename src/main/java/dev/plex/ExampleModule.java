package dev.plex;

import dev.plex.command.ExampleCommand;
import dev.plex.module.PlexModule;

public class ExampleModule extends PlexModule
{
    @Override
    public void enable()
    {
        registerCommand(new ExampleCommand());
    }

    @Override
    public void disable()
    {
    }
}
