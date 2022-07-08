package me.enchantingtableplugin.enchantingtable;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class EnchantingTable extends JavaPlugin implements Listener {
  World world;
  int Counter = 0;

  @Override
  public void onEnable() {

    getServer().getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void Join(PlayerJoinEvent e) {

    world = e.getPlayer().getWorld();

  }

  @EventHandler
  public void pickUpEvent(EntityPickupItemEvent e) {

    if (e.getEntity() instanceof Player && e.getItem().getItemStack().getType() == Material.ENCHANTING_TABLE){
      System.out.println("passou o teste de pegar a mesa de encantamento");
      Player player = (Player) e.getEntity();


      world.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1000f, 0f);
      new BukkitRunnable() {
        @Override
        public void run() {
          world.spawnParticle(Particle.GLOW_SQUID_INK, player.getLocation(), 2500, 0.1, 250 ,0.1);
          Counter++;
          if(Counter >= 50) {
            this.cancel();
          }
        }
      }.runTaskTimer(this, 0, 2);
      Counter = 0;

    }

  }

  @EventHandler
  public void placeBlock(BlockPlaceEvent e) {
    Player player = e.getPlayer();
    Block block = e.getBlockPlaced();

    if (block.getType() == Material.ENCHANTING_TABLE) {
      world.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1000f, 0f);
      new BukkitRunnable() {
        @Override
        public void run() {
          world.spawnParticle(Particle.GLOW_SQUID_INK, block.getLocation(), 2500, 0.1, 250 ,0.1);
          Counter++;
          if(Counter >= 50) {
            this.cancel();
          }
        }
      }.runTaskTimer(this, 0, 2);
      Counter = 0;
    }
  }

  @EventHandler
  public void inventoryInteract(InventoryMoveItemEvent e) {

    if (e.getItem().getType() == Material.ENCHANTING_TABLE && !(e.getDestination().getType() == InventoryType.PLAYER)) {
      System.out.println("passou o teste de mover inventarios");

      e.setCancelled(true);

    }

  }

}
