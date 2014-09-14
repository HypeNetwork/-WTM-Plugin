package cl.eilers.tatanpoker09.wtm.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cl.eilers.tatanpoker09.wtm.Match;
import cl.eilers.tatanpoker09.wtm.MatchState;
import cl.eilers.tatanpoker09.wtm.scoreboard.Team;
import cl.eilers.tatanpoker09.wtm.utils.TatanUtils;

public class JoinCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length>0){
			String argsTogether = TatanUtils.concatenateArgs(args);
			Player playerSender = (Player)sender;

			for(Team team : Match.getTeams()){
				if(!team.getPlayersInTeam().contains(playerSender)){
					if(team.getName().toLowerCase().startsWith(argsTogether.toLowerCase())){
						team.addPlayer(playerSender);
						playerSender.setDisplayName(Team.getPlayerTeam(playerSender).getTeamColor()+ChatColor.stripColor(playerSender.getDisplayName()));
						sender.sendMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Haz entrado en el equipo: "+team.getTeamColor()+ChatColor.stripColor(team.getName()));
						if(Match.getMatch().getMatchState().equals(MatchState.PLAYING)){
							playerSender.getInventory().clear();
							playerSender.setHealth(0);
							playerSender.teleport(team.getSpawnPoint());
							if(team.getName().equals("Observers")){
								for(Player player : Bukkit.getOnlinePlayers()){
									if(!Team.getPlayerTeam(player).equals("Observers")){
										player.hidePlayer(playerSender);
									}
								}
								playerSender.setGameMode(GameMode.CREATIVE);
							} else {
								for(Player player : Bukkit.getOnlinePlayers()){
									player.showPlayer(playerSender);
								}
								for(Player player : Team.getTeam("Observers").getPlayersInTeam()){
									playerSender.hidePlayer(player);
								}
								playerSender.setGameMode(GameMode.SURVIVAL);
							}
						}
					} 
				} else {
					Team.getPlayerTeam(playerSender).removePlayer(playerSender);
				}
			}
			return true;
		} else {
			sender.sendMessage(ChatColor.DARK_RED+"[WTM] "+ChatColor.RED+"Por favor especifica un equipo.");
			return false;
		}
	}
}