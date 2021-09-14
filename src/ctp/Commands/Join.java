package me.nosaj9.ctp.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.nosaj9.ctp.Main;

public class Join implements CommandExecutor, Listener {
	private Main Main;

	public Join(Main Plugin) {
		Main = Plugin;
		Main.getCommand("join").setExecutor(this);
		Bukkit.getPluginManager().registerEvents(this, Main);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must a be player to execute this command!");
			return true;
		}

		Player p = (Player)sender;
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

		if(args.length > 1)
			return false;

		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("attack")) {
				Main.Teams.checkTeam(p);
				Main.Teams.attack.addEntry(p.getName());
				Main.Teams.attackers.add(p);
				p.teleport(Main.Teams.attackspawn);
				Location sp = Main.Teams.attackspawn;
				Bukkit.dispatchCommand(console, "spawnpoint " + p.getName() + " " + sp.getX() + " " + sp.getY() + " " + sp.getZ());
				p.sendMessage("Joined " +  Main.Teams.attack.getColor() + Main.Teams.attack.getName());
				Main.Teams.updateTeams();
				return true;
			}
			else if(args[0].equalsIgnoreCase("defense")) {
				Main.Teams.checkTeam(p);
				Main.Teams.defend.addEntry(p.getName());
				Main.Teams.defenders.add(p);
				p.teleport(Main.Teams.defensespawn);
				Location sp = Main.Teams.defensespawn;
				Bukkit.dispatchCommand(console, "spawnpoint " + p.getName() + " " + sp.getX() + " " + sp.getY() + " " + sp.getZ());
				p.sendMessage("Joined " + Main.Teams.defend.getColor() + Main.Teams.defend.getName());
				Main.Teams.updateTeams();
				return true;
			}
		}

		return false;
	}
	
	@EventHandler
	public void on(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(Main.Teams.attack == null || Main.Teams.defend == null) return;
		
		if(Main.Teams.attackers.contains(p)) {
			Main.Teams.attack.removeEntry(p.getName());
			Main.Teams.attackers.remove(p);
			Main.Teams.updateTeams();
		}
		if(Main.Teams.defenders.contains(p)) {		
			Main.Teams.defend.removeEntry(p.getName());
			Main.Teams.defenders.remove(p);
			Main.Teams.updateTeams();
		}
	}
}
