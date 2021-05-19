package com.zoomdk.tradingsystem.Commands;

import com.zoomdk.tradingsystem.files.DataConfig;
import com.zoomdk.tradingsystem.files.NPCConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    @Deprecated
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
        Player player = (Player) sender;
            if (player.hasPermission("*")) {
                if (!(args.length == 0)) {
                    if (args[0].equalsIgnoreCase("reload")) {
                        player.sendMessage("Reloadet TradingSystem");
                        DataConfig.reload();
                        NPCConfig.reload();
                    }
                    if (args[0].equalsIgnoreCase("reset")) {
                        if (!(args.length == 1)) {
                            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(args[1].toString());
                            ConfigurationSection storm = DataConfig.get().getConfigurationSection(String.valueOf(offlinePlayer.getUniqueId()));
                            if (storm != null) {
                                DataConfig.get().set(String.valueOf(offlinePlayer.getUniqueId()), null);
                                player.sendMessage(offlinePlayer.getName() + " er blevet resat");
                            }
                            player.sendMessage(offlinePlayer.getName() + " har intet data");
                            DataConfig.save();
                        }
                        else {
                            player.sendMessage("Skriv navn");
                        }
                    }

                }
            }
            else {
                player.sendMessage("§8[§4§lS§c§lP§8] §7Ingen adgang. Brug §c/help §7for hjælp");
            }

        }
        return false;
    }
}