package network.secondlife.spigot;

import com.google.common.base.Throwables;
import net.minecraft.server.MinecraftServer;
import network.secondlife.spigot.commands.EntityTrackingRangeCommand;
import network.secondlife.spigot.commands.KnockbackCommand;
import network.secondlife.spigot.commands.MobAiCommand;
import network.secondlife.spigot.commands.SetViewDistanceCommand;
import network.secondlife.spigot.knockback.KnockbackModule;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.spigotmc.SpigotConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class SLSpigotConfig {

    private static final File CONFIG_FILE = new File( "config/server", "settings.yml");
    static YamlConfiguration config;
    public static final Random RANDOM = new Random();

    static {
        init();
    }

    public static void init() {

        config = new YamlConfiguration();

        try {
            config.load ( CONFIG_FILE );
        } catch ( IOException ignored) {
        } catch ( InvalidConfigurationException ex ) {
            Bukkit.getLogger().log( Level.SEVERE, "Could not load settings.yml, please correct your syntax errors", ex );
            throw Throwables.propagate( ex );
        }

        config.options().copyDefaults( true );

        strength_1_nerf();
        strength_2_nerf();
        options();

        try {
            config.save(CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new KnockbackModule();
    }

    public static void save() {
        try {
            config.set("settings.disable_mob_ai", disableMobAi);
            config.save(CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerCommands() {
        MinecraftServer.getServer().server.getCommandMap().register("knockback", "gspigot", new KnockbackCommand());
        MinecraftServer.getServer().server.getCommandMap().register("mobai", "gspigot", new MobAiCommand());
        MinecraftServer.getServer().server.getCommandMap().register("entitytrackingrange", "gspigot", new EntityTrackingRangeCommand());
        MinecraftServer.getServer().server.getCommandMap().register("setviewdistance", "gspigot", new SetViewDistanceCommand("setviewdistance"));
    }

    private static void set(String path, Object val) {
        config.set( path, val );
    }

    private static boolean getBoolean(String path, boolean def) {
        config.addDefault( path, def );
        return config.getBoolean( path, config.getBoolean( path ) );
    }

    private static double getDouble(String path, double def) {
        config.addDefault( path, def );
        return config.getDouble( path, config.getDouble( path ) );
    }

    private static float getFloat(String path, float def) {
        // TODO: Figure out why getFloat() always returns the default value.
        return (float) getDouble(path, (double) def);
    }

    private static int getInt(String path, int def) {
        config.addDefault( path, def );
        return config.getInt( path, config.getInt( path ) );
    }

    private static <T> List getList(String path, T def) {
        config.addDefault( path, def );
        return (List<T>) config.getList( path, config.getList( path ) );
    }


    public static String getString(String path, String def) {
        config.addDefault( path, def );
        return config.getString( path, config.getString( path ) );
    }

    public static double strength_1_nerf;
    public static void strength_1_nerf() {
        double value = getDouble("strength_1_nerf", 1.0D);

        strength_1_nerf = value < 0 || value > 1 ? 1.0D : value;
    }

    public static double strength_2_nerf;
    public static void strength_2_nerf() {
        double value = getDouble("strength_2_nerf", 1.0D);

        strength_2_nerf = value < 0 || value > 1 ? 1.0D : value;
    }

    public static boolean disableMobAi;
    public static boolean disableCropTrampling;
    public static boolean disableBlockBurn;
    public static boolean disableBlockSpread;
    public static boolean disableLeavesDecay;
    public static boolean disableSlimesInFlatWorld;
    public static boolean disableEntityCollisions;
    public static boolean disableWeatherTicking;
    public static boolean disableSleepCheck;
    public static boolean disableVillageTicking;
    public static boolean disableTracking;
    public static boolean disableBlockTicking;
    public static boolean onlyCustomTab;
    public static boolean logRemainingAsyncThreadsDuringShutdown;
    public static boolean disableSaving;
    public static boolean playerListPackets;
    public static boolean updatePingOnTablist;
    public static boolean disablePlayerFileSaving;
    public static boolean instantRespawn;
    public static boolean mobsEnabled;
    public static boolean cacheChunkMaps;
    public static boolean updateMapItemsInPlayerInventory;
    public static boolean autoSaveClearRegionFileCache;
    public static boolean autoSaveFireWorldSaveEvent;
    public static boolean usePlayerMoveEvent;
    public static boolean lagSpikeLoggerEnabled;
    public static boolean reduceArmorDamage;
    public static boolean fireLeftClickBlock;
    public static boolean fireLeftClickAir;
    public static boolean infiniteWaterSources;
    public static boolean tabCompleteNames;
    public static boolean useAutoSave;
    public static long lagSpikeLoggerTickLimitNanos;

    public static boolean uhc, practice;

    public static int autoSaveChunksPerTick;
    public static int playersPerChunkIOThread;
    public static int brewingMultiplier;
    public static int smeltingMultiplier;

    public static double appleRate;
    public static double luckyLeavesRate = -1;

    public static double getRandomValue() {
        return ThreadLocalRandom.current().nextDouble() * 100;
    }

    public static void options() {
        brewingMultiplier = getInt("settings.brewingMultiplier", 1);
        smeltingMultiplier = getInt("settings.smeltingMultiplier", 1);
        disableMobAi = getBoolean("settings.disable_mob_ai", false);
        disableCropTrampling = getBoolean("settings.disable_crop_trampling", true);
        disableBlockBurn = getBoolean("settings.disable_block_burn", true);
        disableBlockSpread = getBoolean("settings.disable_block_spread", true);
        disableLeavesDecay = getBoolean("settings.disable_leaves_decay", true);
        disableSlimesInFlatWorld = getBoolean("settings.disable_slimes_in_flat_world", false);
        disableEntityCollisions = getBoolean("settings.disable_entity_collisions", true);
        disableWeatherTicking = getBoolean("settings.disable_ticking_weather", true);
        disableSleepCheck = getBoolean("settings.disable_ticking_sleep_check", true);
        disableVillageTicking = getBoolean("settings.disable_ticking_villages", true);
        disableTracking = getBoolean("settings.disable_entity_tracker", false);
        disableBlockTicking = getBoolean("settings.disable_block_tick", false);
        onlyCustomTab = getBoolean("settings.only_custom_tab", false);
        logRemainingAsyncThreadsDuringShutdown = getBoolean("settings.log_remaining_async_threads_during_shutdown", true);
        disableSaving = getBoolean("settings.disable_saving", false);
        playerListPackets = !onlyCustomTab && !getBoolean("settings.player_list_packets", false);
        updatePingOnTablist = !onlyCustomTab && !getBoolean("settings.ping_update_packets", false);
        disablePlayerFileSaving = getBoolean("settings.disable_player_file_saving", false);
        if (disablePlayerFileSaving) {
            SpigotConfig.disableStatSaving = true;
        }
        instantRespawn = getBoolean("settings.instantRespawn", false);
        mobsEnabled = getBoolean("settings.mobs_enabled", true);
        updateMapItemsInPlayerInventory = getBoolean("settings.update_map_items_in_player_inventory", false);
        autoSaveClearRegionFileCache = getBoolean("settings.auto_save_clear_region_file_cache", false);
        autoSaveFireWorldSaveEvent = getBoolean("settings.auto_save_fire_world_save_event", false);
        usePlayerMoveEvent = getBoolean("settings.use_player_move_event", false);
        cacheChunkMaps = getBoolean("settings.cacheChunkMaps", false);

        lagSpikeLoggerEnabled = getBoolean("settings.lag_spike_logger", true);
        lagSpikeLoggerTickLimitNanos = getInt("settings.lag_spike_logger", 100) * 1000000L;

        autoSaveChunksPerTick = getInt("settings.auto_save_chunks_per_tick", 200);
        playersPerChunkIOThread = getInt("settings.players_on_thread", 150);
        reduceArmorDamage = getBoolean("settings.reduce-armor-damage", false);
        fireLeftClickBlock = getBoolean("settings.fireLeftClickBlock", false);
        fireLeftClickAir = getBoolean("settings.fireLeftClickAir", false);
        infiniteWaterSources = getBoolean("settings.infiniteWaterSources", true);
        tabCompleteNames = getBoolean("settings.tabCompleteNames", true);
        useAutoSave = getBoolean("settings.useAutoSave", true);
    }
}
