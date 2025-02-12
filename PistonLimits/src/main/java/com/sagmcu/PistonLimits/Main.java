package com.sagmcu.PistonLimits;

import org.bukkit.plugin.java.JavaPlugin;

import com.sagmcu.PistonLimits.commands.ReloadCommand;
import com.sagmcu.PistonLimits.mechanisms.PistonLimiter;
import com.sagmcu.PistonLimits.utils.ChatColorTranslator;

public class Main extends JavaPlugin {
    private PistonLimiter pistonLimiter;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        pistonLimiter = new PistonLimiter(this);
        getServer().getPluginManager().registerEvents(pistonLimiter, this);
        getCommand("pistonlimits").setExecutor(new ReloadCommand(this));
        String enabledMessage = ChatColorTranslator.translate("&aPistonLimits &7has been &aenabled&7! &6:D");
        this.getServer().getConsoleSender().sendMessage(enabledMessage);
    }

    @Override
    public void onDisable() {
        String disabledMessage = ChatColorTranslator.translate("&cPistonLimits &7has been &cdisabled&7! :(");
        this.getServer().getConsoleSender().sendMessage(disabledMessage);
    }

    public PistonLimiter getPistonLimiter() {
        return pistonLimiter;
    }
}