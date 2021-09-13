package me.nosaj9.ctp;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
public class Teams {
	public ArrayList<Player> attackers;
	public ArrayList<Player> defenders;
	public Team attack;
	public Team defend;
	public Location attackspawn;
	public Location defensespawn;
	public Scoreboard board;
	public ScoreboardManager manager;
	private Main Main;

	public Teams(Main Plugin) {
		Main = Plugin;
		manager = Bukkit.getScoreboardManager();
		board = manager.getNewScoreboard();
	}

	public void setupTeams(String s, Player p) {
		if(s.equalsIgnoreCase("arsenal")) {
			attack = board.registerNewTeam("Germans");
			attack.setDisplayName(ChatColor.DARK_GRAY + "Wehrmacht Heer");
			attack.setColor(ChatColor.DARK_GRAY);
			attack.setPrefix(ChatColor.DARK_GRAY + "");
			attack.setSuffix(ChatColor.WHITE + "");
			
			defend = board.registerNewTeam("Soviets");
			defend.setDisplayName(ChatColor.RED + "Red Army");
			defend.setColor(ChatColor.RED);
			defend.setPrefix(ChatColor.RED + "");
			defend.setSuffix(ChatColor.WHITE + "");
			
			attackers = new ArrayList<Player>();
			defenders = new ArrayList<Player>();
			
			p.sendMessage("arsenal is setup");
		}
		else if(s.equalsIgnoreCase("omaha")) {
			attack = board.registerNewTeam("Americans");
			attack.setDisplayName(ChatColor.DARK_GREEN + "US Infantry");
			attack.setColor(ChatColor.DARK_GREEN);
			attack.setPrefix(ChatColor.DARK_GREEN + "");
			attack.setSuffix(ChatColor.WHITE + "");
			
			defend = board.registerNewTeam("Germans");
			defend.setDisplayName(ChatColor.DARK_GRAY + "Wehrmacht Heer");
			defend.setColor(ChatColor.DARK_GRAY);
			defend.setPrefix(ChatColor.DARK_GRAY + "");
			defend.setSuffix(ChatColor.WHITE + "");
		}


		attack.setAllowFriendlyFire(false);
		attack.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
		attackers = new ArrayList<Player>();

		defend.setAllowFriendlyFire(false);
		defend.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OTHER_TEAMS);
		defenders = new ArrayList<Player>();
		for(Player player : Bukkit.getOnlinePlayers())
			player.setScoreboard(board);
	}
	
	public void updateTeams() {
		for(Player player : Bukkit.getOnlinePlayers())
			player.setScoreboard(Main.Teams.board);
	}
	
	public void checkTeam(Player p) {
		if(Main.Teams.attackers.contains(p)) {
			Main.Teams.attack.removeEntry(p.getName());
			Main.Teams.attackers.remove(p);
			p.getInventory().clear();
		}
		if(Main.Teams.defenders.contains(p)) {
			Main.Teams.defend.removeEntry(p.getName());
			Main.Teams.defenders.remove(p);
			p.getInventory().clear();
		}
	}
}
