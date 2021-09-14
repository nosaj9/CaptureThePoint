package me.nosaj9.ctp.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.nosaj9.ctp.Main;
import me.nosaj9.ctp.Game.GameRunnable;
import net.md_5.bungee.api.ChatColor;

public class Setup implements CommandExecutor{
	private Main Main;
	private int countdown = 3;
	public String map = "";
	
	public Setup(Main Plugin) {
		Main = Plugin;
		Main.getCommand("setup").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length > 1 || args.length < 1)
			return false;
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("arsenal")) {
				Main.Teams.setupTeams(args[0], (Player)sender);
				map = "arsenal";		
				return true;
			}
			else if(args[0].equalsIgnoreCase("omaha")) {
				Main.Teams.setupTeams(args[0], (Player)sender);
				map = "omaha";
				return true;
			}
			else if(args[0].equalsIgnoreCase("start")) {
				new BukkitRunnable() {

					@Override
					public void run() {
						if(countdown <= 0) {
							new GameRunnable(Main).runTaskTimer(Main, 20, 20);
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a times 5 60 5");
							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + ChatColor.YELLOW + "THE ASSAULT HAS BEGUN! MOVE YOUR ASS!" + "\"}");
							cancel();
							return;
						}
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a times 5 10 5");
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + ChatColor.RED + countdown + "\"}");
						countdown--;
					}
					
				}.runTaskTimer(Main, 0, 20);
				countdown = 3;
				return true;
			}
			else if(args[0].equalsIgnoreCase("s")) {
				new GameRunnable(Main).runTaskTimer(Main, 20, 20);
				
				for(Player pl : Bukkit.getOnlinePlayers()) {
					pl.setExp(0);
					pl.setExp(300);
					pl.setGameMode(GameMode.SURVIVAL);
				}
				
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a times 5 60 5");
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + ChatColor.YELLOW + "THE ASSAULT HAS BEGUN! MOVE YOUR ASS!" + "\"}");
				return true;
			}
		}
		return false;
	}
}
