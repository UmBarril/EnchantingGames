package me.enchantingtableplugin.enchantingtable;

import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class EnchantingTable extends JavaPlugin implements Listener {
  World world;
  int Counter = 0;

  @Override
  public void onEnable() {
    Component test = Component.text("atualizou");
    world.sendMessage(test);
    getServer().getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void Join(PlayerJoinEvent e) {

    world = e.getPlayer().getWorld();

  }

  @EventHandler
  public void pickUpEvent(EntityPickupItemEvent e) {

    if (e.getEntity() instanceof Player player && e.getItem().getItemStack().getType() == Material.ENCHANTING_TABLE){
      System.out.println("passou o teste de pegar a mesa de encantamento");
      world.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1000f, 0f);
      spawnParticle(player.getLocation());

    }

  }

  @EventHandler
  public void placeBlock(BlockPlaceEvent e) {
    Player player = e.getPlayer();
    Block block = e.getBlockPlaced();

    if (block.getType() == Material.ENCHANTING_TABLE) {
      world.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1000f, 0f);

      spawnParticle(block.getLocation());
    }
  }

  @EventHandler
  public void inventoryInteract(InventoryMoveItemEvent e) {

    if (e.getItem().getType() == Material.ENCHANTING_TABLE && !(e.getDestination().getType() == InventoryType.PLAYER)) {
      System.out.println("passou o teste de mover inventarios");

      e.setCancelled(true);

    }

  }

  public void spawnParticle(Location loc) {

    new BukkitRunnable() {
      public final Location location = loc;
      @Override
      public void run() {
        world.spawnParticle(Particle.GLOW_SQUID_INK,location,2500,0.1,2500,0.1);
        Counter++;
        if (Counter >= 75) {
          this.cancel();
        }

      }
    }.runTaskTimer(this, 0, 0);
    Counter = 0;
  }

}
