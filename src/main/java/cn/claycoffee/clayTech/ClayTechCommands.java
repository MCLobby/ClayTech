package cn.claycoffee.clayTech;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import cn.claycoffee.clayTech.utils.Lang;

public class ClayTechCommands implements TabExecutor {
    String @NotNull [] subCommands = {"checkupdate"};

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (args.length > 1)
            return new ArrayList<>();
        if (args.length == 0)
            return Arrays.asList(subCommands);
        return Arrays.stream(subCommands).filter(s -> s.startsWith(args[0])).collect(Collectors.toList());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String @NotNull [] args) {
        if (command.getName().equalsIgnoreCase("claytech")) {
            if (args.length >= 1) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("checkupdate")) {
                        if (sender.hasPermission("claytech.checkupdate")) {
                            sender.sendMessage(Lang.readGeneralText("check_update_deprecated"));
                            return true;
                        } else {
                            sender.sendMessage(Lang.readGeneralText("no_permission"));
                        }
                    } else {
                        subCommandNotFound(sender);
                        return true;
                    }
                } else {
                    subCommandNotFound(sender);
                    return true;
                }
            } else {
                // 基础命令
                sender.sendMessage(Lang.readGeneralText("basic_command").replace("\\{version}",
                        ClayTech.getInstance().getPluginVersion()));
                return true;
            }
        }
        return true;
    }

    private void subCommandNotFound(@NotNull CommandSender sender) {
        sender.sendMessage(Lang.readGeneralText("command_not_found"));
    }

}
