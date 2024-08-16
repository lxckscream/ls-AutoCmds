package ru.lxckscream.lsautocmds;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class Ls_AutoCmds extends JavaPlugin {
    private static ArrayList<Command> commands = new ArrayList<>();
    private static boolean debugMode;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        debugMode = getConfig().getBoolean("debug");
        for (String ln : getConfig().getConfigurationSection("commands").getKeys(false)) {
            Command command = new Command(ln, (ArrayList<String>) getConfig().getStringList("commands." + ln + ".executes"), getConfig().getInt("commands." + ln + ".to-execute"));
            commands.add(command);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Command cmd : commands) cmd.check();
            }
        }.runTaskTimer(this, 20, 20);
    }

    public static boolean isDebugMode() {return debugMode;}

    public static ArrayList<Command> getCommands() {return commands;}
}
