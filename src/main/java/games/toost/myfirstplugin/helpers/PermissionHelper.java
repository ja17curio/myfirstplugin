package games.toost.myfirstplugin.helpers;

import org.bukkit.entity.Player;

public class PermissionHelper {

    public static boolean checkPermission(String permissionKey, Player player) {
        return player.hasPermission(permissionKey);
    }
}
