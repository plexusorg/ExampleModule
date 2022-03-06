package dev.plex;

import dev.plex.module.PlexModule;

public class ExampleModule extends PlexModule
{
    @Override
    public void enable() {
        getLogger().info("Test");
    }

    @Override
    public void disable() {

    }
}
