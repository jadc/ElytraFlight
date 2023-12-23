package red.jad.elytraflight;

import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElytraFlight extends JavaPlugin implements Listener {
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerEquip(PlayerMoveEvent e){
        if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            if( Boolean.FALSE.equals(e.getPlayer().getWorld().getGameRuleValue(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK)) ){
                ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
                boolean canFly = chestplate != null && chestplate.getType().equals(Material.ELYTRA);
                e.getPlayer().setAllowFlight(canFly);
            }
        }
    }
}
