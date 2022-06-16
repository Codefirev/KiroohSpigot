package net.minecraft.server;

public class PacketPlayOutAttachEntity extends Packet {

    public int a;
    public int b;
    public int c;

    public PacketPlayOutAttachEntity() {
    }

    public PacketPlayOutAttachEntity(int var1, Entity var2, Entity var3) {
        this.a = var1;
        this.b = var2.getId();
        this.c = var3 != null ? var3.getId() : -1;
    }

    public void a(PacketDataSerializer packetdataserializer) {
        this.b = packetdataserializer.readInt();
        this.c = packetdataserializer.readInt();
        this.a = packetdataserializer.readUnsignedByte();
    }

    public void b(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeInt(this.b);
        packetdataserializer.writeInt(this.c);
        packetdataserializer.writeByte(this.a);
    }

    @Override
    public void handle(PacketListener packetlistener) {
        this.a((PacketPlayOutListener) packetlistener);
    }

    public void a(PacketPlayOutListener packetlistener) {
        packetlistener.a(this);
    }
}