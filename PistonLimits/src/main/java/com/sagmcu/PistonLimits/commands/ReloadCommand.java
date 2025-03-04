package com.sagmcu.PistonLimits.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sagmcu.PistonLimits.Main;
import com.sagmcu.PistonLimits.utils.ChatColorTranslator;

public class ReloadCommand implements CommandExecutor {
    private final Main plugin;

    public ReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String reloadSuccessMessage = plugin.getConfig().getString("messages.reload_success");
        String noPermissionMessage = plugin.getConfig().getString("messages.no_permission");

        reloadSuccessMessage = ChatColorTranslator.translate(reloadSuccessMessage);
        noPermissionMessage = ChatColorTranslator.translate(noPermissionMessage);

        if (sender instanceof Player) {
            Player player = (Player) sender;

        if (!player.hasPermission("pistonlimits.admin")) {
            player.sendMessage(noPermissionMessage);
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getPistonLimiter().reloadBlockedMaterials();
            player.sendMessage(reloadSuccessMessage);
            return true;
        }
    }
        else {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                plugin.getPistonLimiter().reloadBlockedMaterials();
                sender.sendMessage(reloadSuccessMessage);
                return true;
            }
        }

        return false;
    }
}