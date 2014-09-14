package cl.eilers.tatanpoker09.wtm.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import cl.eilers.tatanpoker09.wtm.scoreboard.Team;

@SuppressWarnings("deprecation")
public class ChatListener implements Listener {
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event){
		event.setCancelled(true);
		String message = event.getMessage();
		String playerName = event.getPlayer().getDisplayName();
		Team team = Team.getPlayerTeam(event.getPlayer());
		System.out.println(team.getTeamColor());
		Bukkit.broadcastMessage("<"+team.getTeamColor()+ChatColor.stripColor(playerName)+ChatColor.WHITE+"> "+message);
	}
}