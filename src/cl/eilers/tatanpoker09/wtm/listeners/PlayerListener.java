package cl.eilers.tatanpoker09.wtm.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cl.eilers.tatanpoker09.wtm.scoreboard.Team;

public class PlayerListener implements Listener{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player playerJoined = event.getPlayer();
		Team.getTeam("observers").addPlayer(playerJoined);
		playerJoined.setDisplayName(Team.getPlayerTeam(playerJoined).getTeamColor()+playerJoined.getDisplayName());
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		Player playerLeft = event.getPlayer();
		if(Team.getPlayerTeam(playerLeft)!=null){
			Team.getPlayerTeam(playerLeft).removePlayer(playerLeft);
		}

	}
}
