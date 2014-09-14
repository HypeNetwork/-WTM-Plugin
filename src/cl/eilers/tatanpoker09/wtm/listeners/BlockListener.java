package cl.eilers.tatanpoker09.wtm.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import cl.eilers.tatanpoker09.wtm.Match;
import cl.eilers.tatanpoker09.wtm.MatchState;
import cl.eilers.tatanpoker09.wtm.scoreboard.Team;

public class BlockListener implements Listener{
	 @EventHandler
	 public void onPlayerBlockBreak(BlockBreakEvent event){
		 Player eventPlayer = event.getPlayer();
		 if(Team.getPlayerTeam(eventPlayer).getName().equals("Observers")){
			 event.setCancelled(true);
			 return;
		 }
		 if(!Match.getMatch().getMatchState().equals(MatchState.PLAYING)){
			 event.setCancelled(true);
		 }
	 }
	 @EventHandler
	 public void onPlayerBlockPlace(BlockPlaceEvent event){
		 Player eventPlayer = event.getPlayer();
		 if(Team.getPlayerTeam(eventPlayer).getName().equals("Observers")){
			 event.setCancelled(true);
			 return;
		 }
		 if(!Match.getMatch().getMatchState().equals(MatchState.PLAYING)){
			 event.setCancelled(true);
		 }
	 }
}
