package com.supeirornetworks.projects.serverutils.core.commands;

import net.pravian.bukkitlib.command.BukkitCommand;
import net.pravian.bukkitlib.command.CommandPermissions;
import net.pravian.bukkitlib.command.SourceType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import com.supeirornetworks.projects.serverutils.core.ServerUtils;

@CommandPermissions(source = SourceType.PLAYER)
public class Command_serverutilshelp extends BukkitCommand<ServerUtils>
    {

    @Override
    public boolean run(CommandSender commandSender, Command command, String commandLabel, String[] args)
        {
        commandSender.sendMessage(ChatColor.DARK_PURPLE + "{--- Welcome to Server Utilities --}");
        commandSender.sendMessage(ChatColor.DARK_PURPLE + "");
        commandSender.sendMessage(ChatColor.GREEN + "{--- Avalable Commands: --}");
        if (commandSender.hasPermission("serverutils.reload"))
            {
            commandSender.sendMessage(ChatColor.DARK_BLUE + "/serverutilsreload  - Reloads the entire server to refresh config options");
            }

        return false;
        }
    }
