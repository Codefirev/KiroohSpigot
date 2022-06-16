package network.secondlife.spigot.commands;

import network.secondlife.spigot.SLSpigotConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.VanillaCommand;

import java.util.Collections;

public class MobAiCommand extends VanillaCommand {

    public MobAiCommand() {
        super("mobai");

        this.setAliases(Collections.singletonList("intelligence"));
        this.setDescription("Change mobai.");
        this.setPermission("bukkit.command.mobai");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if(!this.testPermission(sender)) {
            return true;
        }
        
        SLSpigotConfig.disableMobAi = !SLSpigotConfig.disableMobAi;
        sender.sendMessage(ChatColor.AQUA + "You have " + (SLSpigotConfig.disableMobAi ? "disabled" : "enabled") + " mobai.");
        SLSpigotConfig.save();
        return true;
    }
}
