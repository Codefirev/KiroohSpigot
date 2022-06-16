package network.secondlife.spigot.interceptors;

import net.minecraft.server.PacketPlayInFlying;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MoveInterceptor {

    public void onUpdateLocation(Player player, Location from, Location to, PacketPlayInFlying packet) { }

    public void onUpdateRotation(Player player, Location from, Location to, PacketPlayInFlying packet) { }
}
