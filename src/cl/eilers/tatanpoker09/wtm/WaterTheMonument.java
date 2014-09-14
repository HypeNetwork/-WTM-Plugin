/*TODO   
 * DEBUG A BIT
 * TELL LANDON TO FUCKING BUILD
 * END GAME DETECTION
 * REGIONS
 * LOAD OBJECTIVES
 * MONUMENT CHARGE
 * KEEP TELLING LANDON TO FUCKING BUILD
 * BUGS:
 * PLAYERDISPLAY NAMES DON'T CHANGE IN /JOIN
 * LOAD STARTKIT
 */


package cl.eilers.tatanpoker09.wtm;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cl.eilers.tatanpoker09.wtm.commands.CycleCommand;
import cl.eilers.tatanpoker09.wtm.commands.JoinCommand;
import cl.eilers.tatanpoker09.wtm.commands.MapsCommand;
import cl.eilers.tatanpoker09.wtm.commands.SetNextCommand;
import cl.eilers.tatanpoker09.wtm.commands.StartCommand;
import cl.eilers.tatanpoker09.wtm.listeners.BlockListener;
import cl.eilers.tatanpoker09.wtm.listeners.ChatListener;
import cl.eilers.tatanpoker09.wtm.listeners.DamageListener;
import cl.eilers.tatanpoker09.wtm.listeners.PlayerListener;

public final class WaterTheMonument extends JavaPlugin{
	private static final File mapsDirectory = new File("maps/");
	private static World currentWorld;
	public static File getMapsDirectory() {
		return mapsDirectory;
	}
	
	@SuppressWarnings("deprecation")
	public void onEnable(){
		for(Player player : Bukkit.getOnlinePlayers()){
			for(Player otherPlayer : Bukkit.getOnlinePlayers()){
				player.showPlayer(otherPlayer);
			}
		}
		Maps.loadMaps();
		new Match(Maps.getMapsList().get(0), 1);
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new ChatListener(), this);
		pm.registerEvents(new DamageListener(), this);
		pm.registerEvents(new BlockListener(), this);
		getCommand("maps").setExecutor(new MapsCommand());
		getCommand("setnext").setExecutor(new SetNextCommand());
		getCommand("cycle").setExecutor(new CycleCommand());
		getCommand("join").setExecutor(new JoinCommand());
		getCommand("start").setExecutor(new StartCommand());
		
	}
	public void onDisable(){
		
	}
	
	public static World getCurrentWorld() {
		return currentWorld;
	}
	public static void setCurrentWorld(World currentWorld) {
		WaterTheMonument.currentWorld = currentWorld;
	}
}