package com.zoomdk.tradingsystem;


import com.zoomdk.tradingsystem.Commands.Reload;
import com.zoomdk.tradingsystem.Commands.Test;
import com.zoomdk.tradingsystem.NPC.Q10.Storm;
import com.zoomdk.tradingsystem.files.DataConfig;
import com.zoomdk.tradingsystem.files.NPCConfig;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("traktor").setExecutor(new Test());
        getCommand("tradingsystem").setExecutor(new Reload());
        getServer().getPluginManager().registerEvents(new Storm(), this);
        NPCConfig.setup();
        NPCConfig.get().options().copyDefaults(true);
        NPCConfig.save();

        DataConfig.setup();
        DataConfig.get().options().copyDefaults(true);
        DataConfig.save();
    }

    @Override
    public void onDisable() {

    }
}