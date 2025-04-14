package top.zamc.banKeyWords.commandexecutor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import top.zamc.banKeyWords.BanKeyWords;

import java.util.ArrayList;
import java.util.StringJoiner;

public class KeyWordListCommandExecutor implements CommandExecutor {

    public BanKeyWords plugin;

    public KeyWordListCommandExecutor(BanKeyWords plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("keywordlist")&&!sender.hasPermission("bankeywords.keywordlist")){
            sender.sendMessage(ChatColor.RED + "你没有权限执行此命令!");
            return true;
        }
        String strCMD = command.getName();
        if (strCMD.equalsIgnoreCase("keywordlist")&&sender.hasPermission("bankeywords.keywordlist")){
            ArrayList<String> keyWordList = plugin.getKeyWords();
            sender.sendMessage(ChatColor.DARK_GREEN + "当前服务器已添加的关键字有：");
            StringJoiner sj = new StringJoiner(",", ChatColor.AQUA+"[", ChatColor.AQUA+"]");
            for (String s : keyWordList) {
                sj.add(ChatColor.YELLOW +s);
            }
            sender.sendMessage(sj.toString());
            return true;
        }
        return true;
    }
}