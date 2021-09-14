package me.nosaj9.ctp.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nosaj9.ctp.Main;

public class Leave implements CommandExecutor{
	private Main Main;

	public Leave(Main Plugin) {
		Main = Plugin;
		Main.getCommand("leave").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to execute this command!");
			return true;
		}

		Player p = (Player)sender;

		if(args.length > 0)
			return false;
		
		if(Main.Teams.attackers.contains(p)) {
			Main.Teams.attackers.remove(p);
			Main.Teams.attack.removeEntry(p.getName());
			Main.Teams.updateTeams();
			return true;
		}
		if(Main.Teams.defenders.contains(p)) {
			Main.Teams.defenders.remove(p);
			Main.Teams.defend.removeEntry(p.getName());
			Main.Teams.updateTeams();
			return true;
		}
		
		return false;
	}
}
