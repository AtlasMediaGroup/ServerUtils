package com.SuperiorNetworks.Projects.ServerUtils.Commands;

import net.pravian.bukkitlib.command.BukkitCommand;
import net.pravian.bukkitlib.command.CommandPermissions;
import net.pravian.bukkitlib.command.SourceType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(source = SourceType.PLAYER, permission = "serverutils.reload")
public class Command_serverutilsreload extends BukkitCommand
{

    @Override
    public boolean run(CommandSender commandSender, Command command, String commandLabel, String[] args)
    {

        commandSender.sendMessage(ChatColor.DARK_RED + "Server Reloading... ");
        server.dispatchCommand(server.getConsoleSender(), "reload");

        return false;
    }
}
