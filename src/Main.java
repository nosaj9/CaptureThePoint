package me.nosaj9.ctp;

import org.bukkit.plugin.java.JavaPlugin;

import me.nosaj9.ctp.Commands.Join;
import me.nosaj9.ctp.Commands.Leave;
import me.nosaj9.ctp.Commands.Setup;
import me.nosaj9.ctp.Commands.Spawn;
import me.nosaj9.ctp.Game.Casualties;
import me.nosaj9.ctp.Game.GameScoreboard;

public class Main extends JavaPlugin{
	public Teams Teams;
	public GameScoreboard GameScoreboard;
	public Casualties Casualties;
	public Setup Setup;
	public Join Join;
	
	@Override
	public void onEnable() {
		Join = new Join(this);
		Setup = new Setup(this);
		Teams = new Teams(this);
		GameScoreboard = new GameScoreboard(this);
		Casualties = new Casualties(this);
		
		new Leave(this);
		new Spawn(this);
		new Casualties(this);
		
		System.out.println("CTP >> Loaded Plugin");
	}
	
	@Override
	public void onDisable() {
		
	}
}
