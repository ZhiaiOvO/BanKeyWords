package top.zamc.banKeyWords.commandexecutor;

import com.google.common.base.Charsets;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import top.zamc.banKeyWords.BanKeyWords;
import top.zamc.banKeyWords.api.CheckKeyWords;
import top.zamc.banKeyWords.listener.MessageListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.io.Resources.getResource;

public class AddKeyWordsCommandExecutor implements CommandExecutor, CheckKeyWords {

    public BanKeyWords plugin;
    public AddKeyWordsCommandExecutor() {
    }
    public AddKeyWordsCommandExecutor(BanKeyWords plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String strCMD = command.getName();
        if (strCMD.equalsIgnoreCase("addkeywords") && sender.hasPermission("bankeywords.addkeywords")){
            ArrayList<String> argList = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if (checkMessage(args[i])){
                    sender.sendMessage(ChatColor.YELLOW + "该字词：" + ChatColor.RED + args[i] + ChatColor.YELLOW + "已被添加!");
                    sender.sendMessage(ChatColor.RED + "无法重复添加!");
                }else {
                    argList.add(args[i]);
                }
            }
            ArrayList<String> newList = new ArrayList<>();
            ArrayList<String> oldList = plugin.getKeyWords();
            newList.addAll(oldList);
            newList.addAll(argList);
            plugin.setKeyWordList(newList);
            plugin.getWordsFileConfig().set("words",newList);
            System.out.println(plugin.getWordsFileConfig());
            try {
                plugin.getWordsFileConfig().save(plugin.getWordsFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }else if (strCMD.equalsIgnoreCase("bkwreload" ) && sender.hasPermission("bankeywords.reload")){
            File wordsFile = new File(plugin.getDataFolder(),"keywords.yml");
            FileConfiguration newConfig = YamlConfiguration.loadConfiguration(wordsFile);
            plugin.setWordsFileConfig(newConfig);
            plugin.setWordsFile(wordsFile);
            sender.sendMessage(ChatColor.YELLOW + "已成功重新加载插件");
            return true;
        }
        return false;
    }
    @Override
    public boolean checkMessage(String wordInCommand){
        ArrayList<String> oldList = plugin.getKeyWords();
        for (String word : oldList) {
            if (word.equals(wordInCommand))
                return true;
        }
        return false;
    }
}