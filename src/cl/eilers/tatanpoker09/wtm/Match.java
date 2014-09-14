package cl.eilers.tatanpoker09.wtm;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import cl.eilers.tatanpoker09.wtm.scoreboard.ScoreboardMain;
import cl.eilers.tatanpoker09.wtm.scoreboard.Team;

public class Match {
	private int taskToCancel;
	private Maps map;
	private static List<Team> teamsList = new ArrayList<Team>();
	private int matchId;
	private MatchState matchState;
	private static Match match;
	private int countdown;

	public Match(Maps map, int match){
		this.map = map;
		this.matchId = match;
		this.matchState = MatchState.PREMATCH;
		loadMatch();
		Match.setMatch(this);
	}
	public MatchState getMatchState(){
		return matchState;
	}

	public void loadMatch(){
		ScoreboardMain.reloadScoreboard(this);
	}

	public static List<Team> getTeams(){
		return teamsList;
	}
	public static void registerTeam(Team teamToAdd){
		Match.getTeams().add(teamToAdd);
	}

	public int getMatchId(){
		return matchId;
	}
	public Maps getMap(){
		return map;
	}
	public void setMap(Maps map){
		this.map = map;
	}

	public void startCountdown(final int input){
		countdown = input;
		if(countdown>5 && countdown % 5 !=0){
			Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Comenzando partida en "+ChatColor.GOLD+countdown);	
		}
		taskToCancel = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("WTM"), new Runnable(){
			public void run(){
				if(countdown!=0){
					if(countdown>5){
						if (countdown % 5 == 0) {
							Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Comenzando partida en "+ChatColor.GOLD+countdown);	
						}
					} else {
						Bukkit.broadcastMessage(ChatColor.DARK_GREEN+"[WTM] "+ChatColor.GREEN+"Comenzando partida en "+ChatColor.GOLD+countdown);
					}
					countdown--;
				} else {
					startMatch();
					Bukkit.getServer().getScheduler().cancelTask(taskToCancel);
				}
			} 
		}, 0L, 20L);
	}
	public void startMatch(){
		for(Team teams : getTeams()){
			if(!teams.getName().equals("Observers")){
				Location playerSpawn = teams.getSpawnPoint();
				Match.getMatch().setMatchState(MatchState.PLAYING);
				for(Player player : teams.getPlayersInTeam()){
					player.getInventory().clear();
					player.setGameMode(GameMode.SURVIVAL);
					player.teleport(playerSpawn);
					for(Player obsPlayer : Team.getTeam("Observers").getPlayersInTeam()){
						player.hidePlayer(obsPlayer);
					}
				}
			}
		}
	}
	private void setMatchState(MatchState state) {
		this.matchState = state;

	}
	public static Match getMatch() {
		return match;
	}
	public static void setMatch(Match match) {
		Match.match = match;
	}
}