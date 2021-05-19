package com.zoomdk.tradingsystem.Commands;

import com.zoomdk.tradingsystem.Main;
import com.zoomdk.tradingsystem.files.DataConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
                ConfigurationSection test = DataConfig.get().getConfigurationSection(String.valueOf(player.getUniqueId()));
                if (test != null) {
                int q10 = test.getInt("q10");
                int q9 = test.getInt("q9");
                if (q10 == 1) {
                    Bukkit.broadcastMessage("Du har gennemført det Q10");
                }
                if (q9 == 1) {
                    Bukkit.broadcastMessage("Du har gennemført det Q9");

                }

                }
                else {
                    ConfigurationSection playersection = DataConfig.get().createSection(String.valueOf(player.getUniqueId()));
                    playersection.set("q10", 1);
                    DataConfig.save();
                }
        }

        return false;
    }
}
