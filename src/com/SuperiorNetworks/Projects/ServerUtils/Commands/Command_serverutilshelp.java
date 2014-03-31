package com.SuperiorNetworks.Projects.ServerUtils.Commands;

import net.pravian.bukkitlib.command.BukkitCommand;
import net.pravian.bukkitlib.command.CommandPermissions;
import net.pravian.bukkitlib.command.SourceType;
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

        player.sendMessage("Sorry, We have not yet implimented this command. Please contact a Superior-Networks deceloper!");
        return false;
    }
}
