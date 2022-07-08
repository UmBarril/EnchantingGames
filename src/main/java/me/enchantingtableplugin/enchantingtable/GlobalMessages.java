package me.enchantingtableplugin.enchantingtable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class GlobalMessages {
  List<Player> playersThatPlayed;
  public static void showLeaderBoard() {
    Bukkit.getOnlinePlayers();
  }
}
