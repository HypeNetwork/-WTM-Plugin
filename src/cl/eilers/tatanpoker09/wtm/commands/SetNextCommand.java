package cl.eilers.tatanpoker09.wtm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.wtm.Maps;

public class SetNextCommand implements CommandExecutor{
	private static Maps nextMap;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(args.length<1){
			sender.sendMessage(ChatColor.DARK_RED+ "[WTM] "+ ChatColor.RED+"Por favor especifica un mapa.");
		} else {
			for(Maps maps : Maps.getMapsList()){
				if(maps.getName().toLowerCase().startsWith(args[0].toLowerCase())){
					sender.sendMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Se ha seleccionado: "+maps.getName());
					setNextMap(maps);
					return true;
				}
			}
			sender.sendMessage(ChatColor.DARK_RED+ "[WTM] "+ChatColor.RED+"No se encontró un mapa con esos argumentos.");
			return false;
		}
		return false;
	}
	public static void setNextMap(Maps map){
		nextMap = map;
	}
	public static Maps getNextMap(){
		return nextMap;
	}
}
