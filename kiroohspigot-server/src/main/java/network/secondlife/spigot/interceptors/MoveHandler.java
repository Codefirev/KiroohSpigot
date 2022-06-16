package network.secondlife.spigot.interceptors;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

public class MoveHandler {

    private static Set<MoveInterceptor> LISTENERS = new HashSet<>();

    public static void addListener(MoveInterceptor interceptor) {
        LISTENERS.add(interceptor);
    }

    public static void removeListener(MoveInterceptor interceptor) {
        LISTENERS.remove(interceptor);
    }

    public static Set<MoveInterceptor> getListeners() {
        return Sets.newHashSet(LISTENERS);
    }
}