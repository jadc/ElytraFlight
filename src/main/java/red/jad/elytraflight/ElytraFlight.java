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
        if( Boolean.TRUE.equals(e.getPlayer().getWorld().getGameRuleValue(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK)) ) return;

        boolean canAlreadyFly = e.getPlayer().getGameMode().equals(GameMode.CREATIVE) || e.getPlayer().getGameMode().equals(GameMode.SPECTATOR);
        ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
        boolean hasElytra = chestplate != null && chestplate.getType().equals(Material.ELYTRA);

        e.getPlayer().setAllowFlight(canAlreadyFly || hasElytra);
    }
}