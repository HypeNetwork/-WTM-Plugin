package cl.eilers.tatanpoker09.wtm.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.wtm.Match;
import cl.eilers.tatanpoker09.wtm.WaterTheMonument;
import cl.eilers.tatanpoker09.wtm.scoreboard.ScoreboardMain;

public class CycleCommand implements CommandExecutor {
	private static World mapToErase;
	private static File dirToErase;
	private int taskToCancel;
	private int countdown;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length>0){
			if(SetNextCommand.getNextMap()!=null){
				countdown = Integer.parseInt(args[0]);
				if(countdown>5 && countdown % 5 !=0){
				Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Cambiando de mapa a "+ChatColor.RED+SetNextCommand.getNextMap().getName()+ChatColor.GREEN+" en "+ChatColor.GOLD+countdown);	
				}
				Bukkit.getServer().getScheduler().cancelTask(taskToCancel);
				taskToCancel = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("WTM"), new Runnable(){
					public void run(){
						if(countdown!=0){
							if(countdown>5){
								if (countdown % 5 == 0) {
									Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Cambiando de mapa a "+ChatColor.RED+SetNextCommand.getNextMap().getName()+ChatColor.GREEN+" en "+ChatColor.GOLD+countdown);	
								}

							} else {
								Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Cambiando de mapa a "+ChatColor.RED+SetNextCommand.getNextMap().getName()+ChatColor.GREEN+" en "+ChatColor.GOLD+countdown);	
							}
							countdown--;
						} else {
							setMapToErase(WaterTheMonument.getCurrentWorld());
							File mapInput = Match.getMatch().getMap().getMapFolder();
							File finalFolder = mapInput.getParentFile().getParentFile();
							new File(finalFolder, mapInput.getName()).mkdir();
							setDirToErase(new File(finalFolder, mapInput.getName()));				
							ScoreboardMain.reloadScoreboard(new Match(SetNextCommand.getNextMap(), 1));
							Bukkit.getServer().getScheduler().cancelTask(taskToCancel);
						}
					} 
				}, 0L, 20L);
			} else {
				sender.sendMessage(ChatColor.RED+"[WTM] Por favor primero ocupe /setnext");
			}
		} else {
			sender.sendMessage(ChatColor.RED+"[WTM] Por favor especifique un numero.");
		}
		return false;
	}
	public static World getMapToErase() {
		return mapToErase;
	}
	public static void setMapToErase(World mapToErase) {
		CycleCommand.mapToErase = mapToErase;
	}
	public static File getDirToErase() {
		return dirToErase;
	}
	public static void setDirToErase(File dirToErase) {
		CycleCommand.dirToErase = dirToErase;
	} 
}

