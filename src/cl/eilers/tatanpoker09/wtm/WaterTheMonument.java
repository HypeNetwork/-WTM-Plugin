/*TODO 
 * ADD PLAYERS TO OBSERVERS AFTER EACH MATCH
 * DEBUG A BIT
 * TELL LANDON TO FUCKING BUILD
 * END GAME DETECTION
 * REGIONS
 * LOAD OBJECTIVES
 * MONUMENT CHARGE
 * KEEP TELLING LANDON TO FUCKING BUILD
 */


package cl.eilers.tatanpoker09.wtm;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cl.eilers.tatanpoker09.wtm.commands.CycleCommand;
import cl.eilers.tatanpoker09.wtm.commands.JoinCommand;
import cl.eilers.tatanpoker09.wtm.commands.MapsCommand;
import cl.eilers.tatanpoker09.wtm.commands.SetNextCommand;
import cl.eilers.tatanpoker09.wtm.listeners.ChatListener;
import cl.eilers.tatanpoker09.wtm.listeners.PlayerListener;
import cl.eilers.tatanpoker09.wtm.scoreboard.ScoreboardMain;
import cl.eilers.tatanpoker09.wtm.scoreboard.Team;

public final class WaterTheMonument extends JavaPlugin{
	private static final File mapsDirectory = new File("maps/");
	public static File getMapsDirectory() {
		return mapsDirectory;
	}
	public void onEnable(){
		ScoreboardMain.registerTeam(new Team("Observers", 999, ChatColor.AQUA));
		Maps.loadMaps();
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new ChatListener(), this);
		getCommand("maps").setExecutor(new MapsCommand());
		getCommand("setnext").setExecutor(new SetNextCommand());
		getCommand("cycle").setExecutor(new CycleCommand());
		getCommand("join").setExecutor(new JoinCommand());
		
	}
	public void onDisable(){
		
	}
}