package me.enchantingtableplugin.enchantingtable;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Beam extends BukkitRunnable {
  private Beam(World world, Location position, int limit) {
    this.world = world;
    this.position = position;
    this.limit = limit;
  }
  final private Location position;
  final private World world;
  final private int limit;
  private int counter = 0;

  @Override
  public void run() {
      if(counter >= limit) cancel();
      world.spawnParticle(Particle.GLOW_SQUID_INK, position, 2500, 0.1, 100, 0.1);
      counter++;
  }
  public static void spawnBeam(World world, Location position, int timeInTicks) {
    Beam beam = new Beam(world, position, timeInTicks);
    beam.runTaskTimer(JavaPlugin.getPlugin(EnchantingTable.class), 0, 0);
  }
}
