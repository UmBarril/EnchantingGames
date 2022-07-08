package me.enchantingtableplugin.enchantingtable;

import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ETPlayer {
  private Player player;
  private boolean hasEnchantingTable = false;
  private int lives = 5;

  public boolean hasEnchantingTable() {
    return hasEnchantingTable;
  }

  public void changeHasEnchantingTable() {
    if (!hasEnchantingTable) {
      Bukkit.getServer().sendMessage(Component.text("O jogador " + player.getName() + "perdeu a mesa!"), MessageType.CHAT);
    } else {
      Bukkit.getServer().sendMessage(Component.text("O jogador " + player.getName() + "pegou a mesa!"), MessageType.CHAT);
    }
    hasEnchantingTable = !hasEnchantingTable;
  }

  public int getLives() { return lives; }

  public void loseOneLife() { lives--; }
}
