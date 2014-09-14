package cl.eilers.tatanpoker09.wtm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.wtm.Maps;

public class MapsCommand implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(Maps.getMaps().size()>0){
			sender.sendMessage(ChatColor.RED+"==============================");
			int x = 1;
			for(Maps maps : Maps.getMaps()){
				sender.sendMessage(ChatColor.RED+"="+ChatColor.GRAY+""+x+".- "+ChatColor.GOLD+maps.getName()+" - "+maps.getAuthor()+ChatColor.RED+"=");
			}
			
			sender.sendMessage(ChatColor.RED+"==============================");
			return true;
		}
		return false;
	}
}
