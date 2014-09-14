package cl.eilers.tatanpoker09.wtm.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.wtm.scoreboard.ScoreboardMain;

public class CycleCommand implements CommandExecutor {
	private int taskToCancel;
	private int countdown;
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length>0){
			if(SetNextCommand.getNextMap()!=null){
				countdown = Integer.parseInt(args[0]);
				Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Cambiando de mapa a "+ChatColor.RED+SetNextCommand.getNextMap().getName()+ChatColor.GREEN+" en "+ChatColor.GOLD+countdown);	
				Bukkit.getServer().getScheduler().cancelTask(taskToCancel);
				taskToCancel = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("Water The Monument"), new Runnable(){
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
							ScoreboardMain.reloadScoreboard();
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
}

