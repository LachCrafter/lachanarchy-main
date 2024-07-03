package eu.felicianware.lachanarchymain.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Antispam implements Listener {

    private final HashMap<UUID, Long> lastMessageTime = new HashMap<>();
    private final HashMap<UUID, String> lastMessageContent = new HashMap<>();
    private final long MESSAGE_DELAY = TimeUnit.SECONDS.toMillis(3); // 3 seconds delay
    private final long MESSAGE_REPEAT_DELAY = TimeUnit.MINUTES.toMillis(1); // 1 minute delay

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        // Get the message as a string
        String message = PlainTextComponentSerializer.plainText().serialize(event.message()).trim().toLowerCase();

        long currentTime = System.currentTimeMillis();

        // Check message delay
        if (lastMessageTime.containsKey(playerUUID)) {
            long lastTime = lastMessageTime.get(playerUUID);
            if (currentTime - lastTime < MESSAGE_DELAY) {
                player.sendMessage("§cPlease wait before sending another message!");
                event.setCancelled(true);
                return;
            }
        }

        // Check for repeated message
        if (lastMessageContent.containsKey(playerUUID)) {
            String lastMessage = lastMessageContent.get(playerUUID);
            if (message.equals(lastMessage) && currentTime - lastMessageTime.get(playerUUID) < MESSAGE_REPEAT_DELAY) {
                player.sendMessage("§cPlease wait a minute before sending the same message again!");
                event.setCancelled(true);
                return;
            }
        }

        // Update last message time and content
        lastMessageTime.put(playerUUID, currentTime);
        lastMessageContent.put(playerUUID, message);
    }
}
