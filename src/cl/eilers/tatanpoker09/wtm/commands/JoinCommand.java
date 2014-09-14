package cl.eilers.tatanpoker09.wtm.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cl.eilers.tatanpoker09.wtm.scoreboard.ScoreboardMain;
import cl.eilers.tatanpoker09.wtm.scoreboard.Team;
import cl.eilers.tatanpoker09.wtm.utils.TatanUtils;

public class JoinCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length>0){
			String argsTogether = TatanUtils.concatenateArgs(args);
			Player playerSender = (Player)sender;
			if(Team.getPlayerTeam(playerSender)!=null){
				Team.getPlayerTeam(playerSender).removePlayer(playerSender);;
			}
			for(Team team : ScoreboardMain.getTeamsList()){
				if(team.getName().toLowerCase().startsWith(argsTogether.toLowerCase())){
					if(!team.getPlayersInTeam().contains(playerSender)){
						team.addPlayer(playerSender);
						playerSender.setDisplayName(Team.getPlayerTeam(playerSender).getTeamColor()+ChatColor.stripColor(playerSender.getDisplayName()));
						sender.sendMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Haz entrado en el equipo: "+team.getName());
						return true;
					} 
				}
			}
			sender.sendMessage(ChatColor.DARK_RED+ "[WTM] "+ChatColor.RED+"No se encontró un equipo con esos argumentos.");
			return false;
		} else {
			sender.sendMessage(ChatColor.DARK_RED+"[WTM] "+ChatColor.RED+"Por favor especifica un equipo.");
			return false;
		}
	}
}
