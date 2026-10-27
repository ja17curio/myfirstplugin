package games.toost.myfirstplugin;
import org.bukkit.command.CommandSender;

public class Messages {

    public static void noPermission(CommandSender sender) {
        sender.sendMessage("Je mag dit commando niet uitvoeren");
    }
}
