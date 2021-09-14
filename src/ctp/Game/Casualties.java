package me.nosaj9.ctp.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.nosaj9.ctp.Main;
import net.md_5.bungee.api.ChatColor;

public class Casualties implements Listener{
	public int team1 = 0;
	public int team2 = 0;
	private Main Main;

	public Casualties(Main Plugin) {
		Main = Plugin;
		Bukkit.getPluginManager().registerEvents(this, Main);
	}

	@EventHandler
	public void on(PlayerDeathEvent e) {
		Player p = (Player)e.getEntity();
		
		if(Main.Teams.attackers == null || Main.Teams.defenders == null) return;
		
		if(Main.Teams.attackers.contains(p))
			team1++;
		else if(Main.Teams.defenders.contains(p))
			team2++;
	}

	public void display() {
		if(Main.Teams.attack == null || Main.Teams.defend == null) return;
		Bukkit.broadcastMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
		Bukkit.broadcastMessage(Main.Teams.attack.getColor() + "" + ChatColor.BOLD + "CASUALTIES:");
		Bukkit.broadcastMessage(ChatColor.RED + Main.Teams.attack.getName() + " - " + team1);
		Bukkit.broadcastMessage(Main.Teams.defend.getColor() + Main.Teams.defend.getName() + " - " + team2);
		Bukkit.broadcastMessage(ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
	}
}
