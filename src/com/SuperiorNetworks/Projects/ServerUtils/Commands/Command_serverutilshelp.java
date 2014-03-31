package com.SuperiorNetworks.Projects.ServerUtils.Commands;

import net.pravian.bukkitlib.command.BukkitCommand;
import net.pravian.bukkitlib.command.CommandPermissions;
import net.pravian.bukkitlib.command.SourceType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(source = SourceType.PLAYER)
public class Command_serverutilshelp extends BukkitCommand
{

    @Override
    public boolean run(CommandSender commandSender, Command command, String commandLabel, String[] args)
    {
        Player player = (Player) commandSender;

        player.sendMessage(ChatColor.DARK_PURPLE + "{--- Welcome to Server Utilities --}");
        player.sendMessage(ChatColor.DARK_PURPLE + "");
        player.sendMessage(ChatColor.GREEN + "{--- Avalable Commands: --}");
        if (player.hasPermission("serverutils.reload"))
        { 
            player.sendMessage(ChatColor.DARK_BLUE + "/serverutilsreload  - Reloads the entire server to refresh config options");
        }
        
        return false;
    }
}
