package com.supeirornetworks.projects.serverutils.core;

import com.supeirornetworks.projects.serverutils.core.commands.Command_serverutilshelp;
import java.util.logging.Logger;
import net.pravian.bukkitlib.BukkitLib;
import net.pravian.bukkitlib.command.BukkitCommandHandler;
import net.pravian.bukkitlib.config.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
        handler.setCommandLocation(Command_serverutilshelp.class.getPackage());
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

    }
