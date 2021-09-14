package me.nosaj9.ctp.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import net.md_5.bungee.api.ChatColor;
import me.nosaj9.ctp.Main;

public class GameScoreboard {
	private Objective capture;
	private Main Main;
	
	public GameScoreboard(Main Plugin) {
		Main = Plugin;
		capture = Main.Teams.board.registerNewObjective("ControlPoint", "dummy");
		capture.setDisplaySlot(DisplaySlot.SIDEBAR);
		capture.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Control Point");
		
		for(Player player : Bukkit.getOnlinePlayers())
			player.setScoreboard(Main.Teams.board);
	}	
	
	public void updateScore(int n) {		
	    Score score = capture.getScore(ChatColor.GREEN + "OBJECTIVE:"); //Get a fake offline player
	    score.setScore(n);
		
		for(Player player : Bukkit.getOnlinePlayers())
			player.setScoreboard(Main.Teams.board);
	}
}
