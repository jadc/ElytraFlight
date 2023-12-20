package red.jad.elytraflight;

import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ElytraFlight extends JavaPlugin implements Listener {
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
    }

    private boolean allowFlight(World w){
        return Boolean.FALSE.equals(w.getGameRuleValue(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK));
    }

    @EventHandler
    public void onPlayerEquip(PlayerMoveEvent e){
        ItemStack chestplate = e.getPlayer().getInventory().getChestplate();
        boolean canFly = chestplate != null
                && chestplate.getType().equals(Material.ELYTRA)
                && allowFlight(e.getPlayer().getWorld());
        e.getPlayer().setAllowFlight(canFly);
    }

    @EventHandler
    public void onGlide(EntityToggleGlideEvent e){
        if(e.getEntityType().equals(EntityType.PLAYER) && allowFlight(e.getEntity().getWorld())) e.setCancelled(true);
    }
}
