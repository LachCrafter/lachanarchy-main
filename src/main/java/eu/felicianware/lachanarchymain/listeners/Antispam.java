package eu.felicianware.lachanarchymain.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Antispam implements Listener {

    private final Plugin plugin;

    public Antispam(Plugin plugin) {
        this.plugin = plugin;
    }

    private final HashMap<UUID, Long> lastMessageTime = new HashMap<>();
    private final HashMap<UUID, String> lastMessageContent = new HashMap<>();
    private final long MESSAGE_DELAY = TimeUnit.SECONDS.toMillis(3);
    private final long MESSAGE_REPEAT_DELAY = TimeUnit.MINUTES.toMillis(1);

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        String message = PlainTextComponentSerializer.plainText().serialize(event.message()).trim().toLowerCase();

        long currentTime = System.currentTimeMillis();

        if (lastMessageTime.containsKey(playerUUID)) {
            long lastTime = lastMessageTime.get(playerUUID);
            if (currentTime - lastTime < MESSAGE_DELAY) {
                event.setCancelled(true);
                return;
            }
        }

        // Check for repeated message
        if (lastMessageContent.containsKey(playerUUID)) {
            String lastMessage = lastMessageContent.get(playerUUID);
            if (message.equals(lastMessage) && currentTime - lastMessageTime.get(playerUUID) < MESSAGE_REPEAT_DELAY) {
                event.setCancelled(true);
                return;
            }
        }

        lastMessageTime.put(playerUUID, currentTime);
        lastMessageContent.put(playerUUID, message);
    }
}
