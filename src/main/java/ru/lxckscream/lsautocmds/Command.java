package ru.lxckscream.lsautocmds;

import org.bukkit.Bukkit;

import java.util.ArrayList;

public class Command {
    private String name;
    private ArrayList<String> cmds;
    private int startToExecute;
    private int toExecute;

    public Command(String name, ArrayList<String> cmds, int toExecute) {
        this.name = name;
        this.cmds = cmds;
        this.startToExecute = toExecute;
        this.toExecute = toExecute;
    }

    public void check() {
        if (toExecute == 0) {
            if (Ls_AutoCmds.isDebugMode()) System.out.println("[" + name + "] Executing!");
            for (String ln : cmds) {
                if (Ls_AutoCmds.isDebugMode()) System.out.println("[" + name + "] Executing " + ln + "...");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ln);
            }
            if (Ls_AutoCmds.isDebugMode()) System.out.println("[" + name + "] Go back to start execute time!");
            toExecute = startToExecute;
        } else {
            if (Ls_AutoCmds.isDebugMode()) System.out.println("[" + name + "] To execute: " + toExecute + ", start execute: " + startToExecute + ", commands: " + cmds);
            toExecute--;
        }
    }
}
