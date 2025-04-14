package top.zamc.banKeyWords;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import top.zamc.banKeyWords.commandexecutor.AddKeyWordsCommandExecutor;
import top.zamc.banKeyWords.commandexecutor.KeyWordListCommandExecutor;
import top.zamc.banKeyWords.listener.MessageListener;

import java.io.File;
import java.util.ArrayList;

public final class BanKeyWords extends JavaPlugin {
    private File wordsFile = new File(this.getDataFolder(),"keywords.yml");
    public void setWordsFile(File wordsFile) {
        this.wordsFile = wordsFile;
    }

    public void setWordsFileConfig(FileConfiguration wordsFileConfig) {
        this.wordsFileConfig = wordsFileConfig;
    }
    private FileConfiguration wordsFileConfig = YamlConfiguration.loadConfiguration(wordsFile);
    public File getWordsFile() {
        return wordsFile;
    }
    public FileConfiguration getWordsFileConfig() {
        return wordsFileConfig;
    }
    ArrayList<String> keyWordList = new ArrayList<>(wordsFileConfig.getStringList("words"));
    public ArrayList<String> getKeyWords() {
        return keyWordList;
    }
    public void setKeyWordList(ArrayList<String> keyWordList) {
        this.keyWordList = keyWordList;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("BanKeyWords has been enabled!");
        getLogger().info("Watch on your message!!!");
        saveResource("keywords.yml",false);
        getConfig().setDefaults(this.wordsFileConfig);
        getServer().getPluginManager().registerEvents(new MessageListener(this),this);
        getServer().getPluginCommand("addkeywords").setExecutor(new AddKeyWordsCommandExecutor(this));
        getServer().getPluginCommand("bkwreload").setExecutor(new AddKeyWordsCommandExecutor(this));
        getServer().getPluginCommand("keywordlist").setExecutor(new KeyWordListCommandExecutor(this));
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
