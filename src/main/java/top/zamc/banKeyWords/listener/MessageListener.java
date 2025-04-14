package top.zamc.banKeyWords.listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;
import top.zamc.banKeyWords.BanKeyWords;
import top.zamc.banKeyWords.api.CheckKeyWords;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageListener implements Listener, CheckKeyWords {
    public BanKeyWords plugin;
    private String formatedMessage;
    public MessageListener(){
    }
    public MessageListener(@NotNull BanKeyWords plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerSendMessage(AsyncPlayerChatEvent event){
        ArrayList<String> keyWordList = plugin.getKeyWords();
        String playerMessage = event.getMessage();
        if (checkMessage(playerMessage)){
            event.setMessage(formatedMessage);
        }
    }

    @Override
    public boolean checkMessage(String playerMessage){
        boolean isMessageContained = false;
        formatedMessage = new String(playerMessage);
        ArrayList<String> keyWordList = plugin.getKeyWords();
        for (String word : keyWordList) {
            Pattern pattern = Pattern.compile(word);
            Matcher matcher = pattern.matcher(playerMessage);
            while (matcher.find()){
                isMessageContained = true;
                StringBuilder sbFormatedMessage = new StringBuilder();
                sbFormatedMessage.append(formatedMessage.replace(word, "*".repeat(word.length())));
                formatedMessage = sbFormatedMessage.toString();
            }
        }
        return isMessageContained;
    }


}