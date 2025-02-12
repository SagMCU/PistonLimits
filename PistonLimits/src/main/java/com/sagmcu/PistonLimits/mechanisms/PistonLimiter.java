package com.sagmcu.PistonLimits.mechanisms;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;

import com.sagmcu.PistonLimits.Main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PistonLimiter implements Listener {
    private final Main plugin;
    private Set<Material> blockedMaterials = new HashSet<>();
    private boolean blockAll = false;

    public PistonLimiter(Main plugin) {
        this.plugin = plugin;
        loadBlockedMaterials();
    }

    public void reloadBlockedMaterials() {
        loadBlockedMaterials();
    }

    private void loadBlockedMaterials() {
        FileConfiguration config = plugin.getConfig();
        List<String> materialNames = config.getStringList("blocked-blocks");
        blockedMaterials.clear();
        blockAll = false;
        for (String name : materialNames) {
            if (name.equals("*")) {
                blockAll = true;
                break;
            }

            try {
                Material material = Material.valueOf(name.toUpperCase());
                blockedMaterials.add(material);
            } catch (IllegalArgumentException e) {
                plugin.getLogger().warning("Invalid material in config: " + name);
            }
        }
    }

    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent event) {
        if (blockAll || event.getBlocks().stream().anyMatch(block -> blockedMaterials.contains(block.getType()))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent event) {
        if (blockAll || event.getBlocks().stream().anyMatch(block -> blockedMaterials.contains(block.getType()))) {
            event.setCancelled(true);
        }
    }
} 