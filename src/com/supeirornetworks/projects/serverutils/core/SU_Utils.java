package com.supeirornetworks.projects.serverutils.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SU_Utils
    {

    // Thanks to the TotalFreedomMod Developers for this little bit!
    public static void bcastMsg(String message, ChatColor color)
        {
        for (Player player : Bukkit.getOnlinePlayers())
            {
            player.sendMessage((color == null ? "" : color) + message);
            }
        }

    // Thanks to the TotalFreedomMod Developers for this little bit!
    public static void bcastMsg(String message)
        {
        SU_Utils.bcastMsg(message, null);
        }

    // Thanks to the TotalFreedomMod Developers for this bit of cocde!
    public static void adminAction(String adminName, String action, boolean isRed)
        {
        SU_Utils.bcastMsg(adminName + " - " + action, (isRed ? ChatColor.RED : ChatColor.AQUA));
        }

    }
