package com.superiornetworks.serverutils2;

import com.superiornetworks.serverutils.core.commands.Command_serverutilsmysql;
import java.util.logging.Logger;
import net.pravian.bukkitlib.BukkitLib;
import net.pravian.bukkitlib.command.BukkitCommandHandler;
import net.pravian.bukkitlib.config.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerUtils extends JavaPlugin
    {

    public static final Logger logger = Bukkit.getLogger();
    public static BukkitCommandHandler handler;
    public static YamlConfig config;
    public static ServerUtils plugin;

    @Override
    public void onDisable()
        {
        logger.info("Server Utilities Disabled");
        }

    @Override
    public void onEnable()
        {
        BukkitLib.init(this);
        }

    @Override
    public void onLoad()
        {

        plugin = this;
        handler = new BukkitCommandHandler(plugin);
        handler.setCommandLocation(Command_serverutilsmysql.class.getPackage());
        config = new YamlConfig(plugin, "config.yml", true);
        config.load();
        PluginDescriptionFile pdfFile = getDescription();
        PluginManager pm = getServer().getPluginManager();
        logger.info("Server Utilities Enabled!");
        config.options().copyDefaults(true);
        saveConfig();

        }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
        {
        return handler.handleCommand(sender, cmd, commandLabel, args);
        }

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
        ServerUtils.bcastMsg(message, null);
        }

    // Thanks to the TotalFreedomMod Developers for this bit of cocde!
    public static void adminAction(String adminName, String action, boolean isRed)
        {
        ServerUtils.bcastMsg(adminName + " - " + action, (isRed ? ChatColor.RED : ChatColor.AQUA));
        }

    }
