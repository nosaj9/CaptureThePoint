package me.nosaj9.ctp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nosaj9.ctp.Main;

public class Spawn implements CommandExecutor{
	private Main Main;
	
	public Spawn(Main Plugin) {
		Main = Plugin;
		Main.getCommand("spawn").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to execute this command!");
		}
		
		Player p = (Player)sender;
		
		if(args.length > 1 || args.length < 1)
			return false;
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("attack")) {
				Main.Teams.attackspawn = p.getLocation();
				p.sendMessage("Spawn set for " + args[0]);
				return true;
			}
			else if(args[0].equalsIgnoreCase("defense")) {
				Main.Teams.defensespawn = p.getLocation();
				p.sendMessage("Spawn set for " + args[0]);
				return true;
			}
		}
		
		return false;
	}
}
