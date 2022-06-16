package network.secondlife.spigot.interceptors;

import net.minecraft.server.Packet;
import net.minecraft.server.PlayerConnection;

public class PacketInterceptor {

    public void onPacketSend(PlayerConnection connection, Packet packet) { }

    public void onPacketReceive(PlayerConnection connection, Packet packet) { }
}
