package network.secondlife.spigot.commands;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.VanillaCommand;
import org.bukkit.craftbukkit.CraftWorld;
import org.spigotmc.SpigotWorldConfig;

import java.util.Arrays;

public class EntityTrackingRangeCommand extends VanillaCommand {

    public EntityTrackingRangeCommand() {
        super("entitytrackingrange");

        this.setAliases(Arrays.asList("etr", "tr", "tracking"));
        this.setDescription("Change entity tracking range in blocks.");
        this.setUsage("Usage: /entitytrackingrange <player|animal|monster|misc|other|all> <blocks> [world]");
        this.setPermission("bukkit.command.entitytrackingrange");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }

        if(args.length == 2) {
            if(!NumberUtils.isNumber(args[1])) {
                sender.sendMessage(ChatColor.RED + "Invalid value!");
                return true;
            }

            int blocks = Math.abs(Integer.parseInt(args[1]));
            String set = "";
            
            for(World world : Bukkit.getWorlds()) {
                SpigotWorldConfig swc = ((CraftWorld) world).getHandle().spigotConfig;
                
                if(args[0].equalsIgnoreCase("all")) {
                    set = "all";
                    swc.playerTrackingRange = blocks;
                    swc.animalTrackingRange = blocks;
                    swc.monsterTrackingRange = blocks;
                    swc.miscTrackingRange = blocks;
                    swc.otherTrackingRange = blocks;
                } else if(args[0].equalsIgnoreCase("player")) {
                    set = "player";
                    swc.playerTrackingRange = blocks;
                } else if(args[0].equalsIgnoreCase("animal")) {
                    set = "animal";
                    swc.animalTrackingRange = blocks;
                } else if(args[0].equalsIgnoreCase("monster")) {
                    set = "monster";
                    swc.monsterTrackingRange = blocks;
                } else if(args[0].equalsIgnoreCase("misc")) {
                    set = "misc";
                    swc.miscTrackingRange = blocks;
                } else if(args[0].equalsIgnoreCase("other")) {
                    set = "other";
                    swc.otherTrackingRange = blocks;
                } else {
                    sender.sendMessage(ChatColor.RED + this.usageMessage);
                }
            }
            
            if(!set.equals("")) {
                sender.sendMessage(ChatColor.AQUA + "You have set " + ChatColor.DARK_AQUA + set + ChatColor.AQUA + " tracking range to " + ChatColor.DARK_AQUA + blocks + ChatColor.AQUA + " for all worlds.");
            }
            
            return true;
        } else {
            if(args.length != 3) {
                sender.sendMessage("");
                sender.sendMessage(ChatColor.AQUA + "Current entity tracking values:");
                sender.sendMessage("");
              
                for (World world : Bukkit.getWorlds()) {
                    SpigotWorldConfig swc = ((CraftWorld) world).getHandle().spigotConfig;
                    sender.sendMessage(ChatColor.DARK_AQUA + world.getName());
                    sender.sendMessage(ChatColor.AQUA + " Player: " + ChatColor.DARK_AQUA + swc.playerTrackingRange);
                    sender.sendMessage(ChatColor.AQUA + " Animal: " + ChatColor.DARK_AQUA + swc.animalTrackingRange);
                    sender.sendMessage(ChatColor.AQUA + " Monster: " + ChatColor.DARK_AQUA + swc.monsterTrackingRange);
                    sender.sendMessage(ChatColor.AQUA + " Misc: " + ChatColor.DARK_AQUA + swc.miscTrackingRange);
                    sender.sendMessage(ChatColor.AQUA + " Other: " + ChatColor.DARK_AQUA + swc.otherTrackingRange);
                }
                
                sender.sendMessage("");
                sender.sendMessage(ChatColor.RED + this.usageMessage);
                return true;
            }
            
            World world = Bukkit.getWorld(args[2]);
            if(world == null) {
                sender.sendMessage(ChatColor.RED + "World not found!");
                return true;
            }
            
            if(!NumberUtils.isNumber(args[1])) {
                sender.sendMessage(ChatColor.RED + "Invalid value!");
                return true;
            }
            
            int blocks = Math.abs(Integer.parseInt(args[1]));
            SpigotWorldConfig swc = ((CraftWorld) world).getHandle().spigotConfig;
            String set = "";
           
            if(args[0].equalsIgnoreCase("all")) {
                set = "all";
                swc.playerTrackingRange = blocks;
                swc.animalTrackingRange = blocks;
                swc.monsterTrackingRange = blocks;
                swc.miscTrackingRange = blocks;
                swc.otherTrackingRange = blocks;
            } else if(args[0].equalsIgnoreCase("player")) {
                set = "player";
                swc.playerTrackingRange = blocks;
            } else if(args[0].equalsIgnoreCase("animal")) {
                set = "animal";
                swc.animalTrackingRange = blocks;
            } else if(args[0].equalsIgnoreCase("monster")) {
                set = "monster";
                swc.monsterTrackingRange = blocks;
            } else if(args[0].equalsIgnoreCase("misc")) {
                set = "misc";
                swc.miscTrackingRange = blocks;
            } else if(args[0].equalsIgnoreCase("other")) {
                set = "other";
                swc.otherTrackingRange = blocks;
            } else {
                sender.sendMessage(ChatColor.RED + this.usageMessage);
            }

            if(!set.equals("")) {
                sender.sendMessage(ChatColor.AQUA + "You have set " + ChatColor.DARK_AQUA + set + ChatColor.AQUA +" tracking range to " + ChatColor.DARK_AQUA + blocks + ChatColor.AQUA + " for " + ChatColor.DARK_AQUA + world.getName() + ChatColor.AQUA + " world.");
            }

            return true;
        }
    }
}