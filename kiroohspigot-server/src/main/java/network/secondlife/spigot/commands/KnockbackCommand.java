package network.secondlife.spigot.commands;

import network.secondlife.spigot.knockback.KnockbackModule;
import network.secondlife.spigot.knockback.KnockbackProfile;
import network.secondlife.spigot.knockback.KnockbackValue;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnockbackCommand extends Command {

    public KnockbackCommand() {
        super("knockback", "Modify spigot knockback", "/knockback <view|list|use|add|delete|edit>", Arrays.asList("kb"));
        this.setPermission("gspigot.command.knockback");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "list":
                    sender.sendMessage("§eKnockback profiles: §6" + compile(KnockbackModule.INSTANCE.profiles.keySet().toArray(new String[0]), "§e, §6"));
                    break;
                case "view":
                    if (args.length == 2) {
                        KnockbackProfile profile = KnockbackModule.INSTANCE.profiles.get(args[1].toLowerCase());

                        if (profile != null) {
                            sender.sendMessage("§eViewing profile §6" + profile.title + "§e:");

                            for (KnockbackValue value : profile.values) {
                                sender.sendMessage("  §f" + value.name + ": §6" + value.value);
                            }
                        } else {
                            sender.sendMessage("§cInvalid profile: " + args[1].toLowerCase());
                        }
                    } else {
                        sender.sendMessage("§c/knockback view <profile>");
                    }
                    break;
                case "use":
                    if (args.length == 2) {
                        KnockbackProfile profile = KnockbackModule.INSTANCE.profiles.get(args[1].toLowerCase());
                        if (profile != null) {
                            if (sender instanceof Player) {
                                Player player = (Player) sender;

                                for (Player other : player.getWorld().getPlayers()) {
                                    ((CraftPlayer) other).getHandle().setKnockback(profile);
                                }
                            }

                            sender.sendMessage("§eUpdated knockback profile to §6" + profile.title);
                        } else {
                            sender.sendMessage("§cInvalid profile: " + args[1].toLowerCase());
                        }
                    } else {
                        sender.sendMessage("§c/knockback use <profile>");
                    }
                    break;
                case "add":
                    if (args.length == 2) {
                        if (!KnockbackModule.INSTANCE.profiles.containsKey(args[1].toLowerCase())) {
                            KnockbackModule.INSTANCE.profiles.put(args[1].toLowerCase(), new KnockbackProfile(args[1].toLowerCase()));
                            sender.sendMessage("§eKNockback profile created §6" + args[1].toLowerCase());
                        } else {
                            sender.sendMessage("§cKnockback profile already exists.");
                        }
                    } else {
                        sender.sendMessage("§c/knockback add <profile>");
                    }
                    break;
                case "remove":
                case "delete":
                    if (args.length == 2) {
                        if (!args[1].equalsIgnoreCase("default")) {
                            if (KnockbackModule.INSTANCE.profiles.containsKey(args[1].toLowerCase())) {
                                new File("config/knockback" + File.separator + args[1].toLowerCase() + ".yml").delete();
                                KnockbackModule.INSTANCE.profiles.remove(args[1].toLowerCase());
                                sender.sendMessage("§eKNockback profile deleted §6" + args[1].toLowerCase());
                            } else {
                                sender.sendMessage("§cKnockback profile doesn't exist.");
                            }
                        } else {
                            sender.sendMessage("§cCannot delete the default profile.");
                        }
                    } else {
                        sender.sendMessage("§c/knockback delete <profile>");
                    }
                    break;
                case "edit":
                    if (args.length >= 2) {
                        KnockbackProfile profile = KnockbackModule.INSTANCE.profiles.get(args[1].toLowerCase());

                        if (profile != null) {
                            List<String> fields = new ArrayList<>();
                            for (KnockbackValue value : profile.values) {
                                fields.add(value.id);
                            }

                            if (args.length >= 3) {
                                if (args.length == 4) {
                                    for (KnockbackValue value : profile.values) {
                                        if (value.id.equalsIgnoreCase(args[2])) {
                                            if (value.type == Integer.class) {
                                                try {
                                                    value.value = Integer.parseInt(args[3]);
                                                    sender.sendMessage("§eSet profile §6" + profile.title + " §efield §6" + value.name + " §eto §6" + value.value);
                                                    profile.save();
                                                } catch (NumberFormatException e) {
                                                    sender.sendMessage("§cInvalid number");
                                                }
                                            } else if (value.type == Double.class) {
                                                try {
                                                    value.value = Double.parseDouble(args[3]);
                                                    sender.sendMessage("§eSet profile §6" + profile.title + " §efield §6" + value.name + " §eto §6" + value.value);
                                                    profile.save();
                                                } catch (NumberFormatException e) {
                                                    sender.sendMessage("§cInvalid number");
                                                }
                                            } else if (value.type == Boolean.class) {
                                                try {
                                                    value.value = Boolean.parseBoolean(args[3]);
                                                    sender.sendMessage("§eSet profile §6" + profile.title + " §efield §6" + value.name + " §eto §6" + value.value);
                                                    profile.save();
                                                } catch (NumberFormatException e) {
                                                    sender.sendMessage("§cInvalid number");
                                                }
                                            }
                                            return true;
                                        }
                                    }

                                    sender.sendMessage("§eAvailable fields: §6" + compile(fields.toArray(new String[0])));
                                } else {
                                    sender.sendMessage("§c/knockback edit <profile> <field> <value>");
                                }
                            } else {
                                sender.sendMessage("§eAvailable fields: §6" + compile(fields.toArray(new String[0])));
                            }
                        } else {
                            sender.sendMessage("§cInvalid profile: " + args[1].toLowerCase());
                        }
                    } else {
                        sender.sendMessage("§c/knockback edit <profile> <field> <value>");
                    }
                    break;
                default:
                    sender.sendMessage("§c" + usageMessage);
                    break;
            }
        } else {
            sender.sendMessage("§c" + usageMessage);
        }

        return true;
    }

    public String compile(Object[] objs) {
        return compile(objs, " ");
    }

    public String compile(Object[] objs, String seperator) {
        StringBuilder builder = new StringBuilder();
        for (Object s : objs) {
            if (builder.length() != 0) builder.append(seperator);
            builder.append(s.toString());
        }
        return builder.toString();
    }
}
