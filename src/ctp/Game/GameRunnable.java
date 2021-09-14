package me.nosaj9.ctp.Game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.nosaj9.ctp.Main;
import net.md_5.bungee.api.ChatColor;

public class GameRunnable extends BukkitRunnable{
	public int progress = 0;
	private int seconds = 300;
	private int amt = 0;
	private boolean contesting = false;
	private Main Main;
	private Location point;
	
	public GameRunnable(Main Plugin) {
		Main = Plugin;
	}

	@Override
	public void run() {
		if(progress >= 100) {
			progress = 100;
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a times 0 40 0");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + ChatColor.RED + "THE GERMANS HAVE CAPTURED THE ARSENAL!" + "\"}");
			Main.Casualties.display();
			Main.GameScoreboard.updateScore(100);
			this.cancel();
			return;
		}

		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(onPoint(pl)) {
				if(Main.Teams.attackers.contains(pl))
					amt++;
				if(Main.Teams.defenders.contains(pl)) {
					contesting = true;
					break;
				}
			}
		}
		
		if(contesting && amt > 0) {
			//Bukkit.broadcastMessage(ChatColor.BOLD + "Contesting Point");
		}
		else if(!contesting && amt > 0){
			if(amt == 1) {
				progress += 5;
				captureSound(Main.Teams.attackers.get(0));
			}
			else if(amt == 2) {
				progress += 7;
				captureSound(Main.Teams.attackers.get(0));
			}
			else if(amt == 3) {
				progress += 10;
				captureSound(Main.Teams.attackers.get(0));
			}
			Main.GameScoreboard.updateScore(progress);
		}
		else if(amt == 0 && progress > 0) {
			progress -= 10;
			point = new Location(Main.Teams.attackers.get(0).getWorld(), -518.5, 4, 339.5);
			Main.Teams.attackers.get(0).getWorld().playSound(point, Sound.BLOCK_CLOTH_BREAK, 3, 1);
			Main.GameScoreboard.updateScore(progress);
		}

		if(progress < 0) {
			progress = 0;
			Main.GameScoreboard.updateScore(progress);
		}

		if(seconds < 0) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a times 0 70 0");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + ChatColor.RED + "THE SOVIET FORCES HELD THEIR GROUND!" + "\"}");
			Main.Casualties.display();
			this.cancel();
			return;
		}
		
		seconds--;
		amt = 0;
		contesting = false;
		
		for(Player pl : Bukkit.getOnlinePlayers())
			pl.setLevel(seconds);
	}

	public boolean onPoint(Player p) {
		return p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GLASS;
	}
	
	public void captureSound(Player p) {
		point = new Location(p.getWorld(), -518.5, 4, 339.5);
		p.getWorld().playSound(point, Sound.BLOCK_GLASS_PLACE, 3, 1);
	}
}
