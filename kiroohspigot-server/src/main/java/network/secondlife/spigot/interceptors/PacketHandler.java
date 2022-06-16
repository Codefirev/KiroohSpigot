package network.secondlife.spigot.interceptors;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class PacketHandler {

    private static Set<PacketInterceptor> LISTENERS = new HashSet<>();

    public static void addListener(PacketInterceptor interceptor) {
        LISTENERS.add(interceptor);
    }

    public static void removeListener(PacketInterceptor interceptor) {
        LISTENERS.remove(interceptor);
    }

    public static Set<PacketInterceptor> getListeners() {
        return Sets.newHashSet(LISTENERS);
    }
}