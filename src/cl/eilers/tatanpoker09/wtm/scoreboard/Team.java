package cl.eilers.tatanpoker09.wtm.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Team {
	private List<Player> playersInTeam = new ArrayList<Player>();
	private String name;
	private int maxCapacity;
	private ChatColor teamColor;
	private List<Objectives> objectives = new ArrayList<Objectives>();
	
	public Team(String name, int maxCapacity, ChatColor color){
		this.name = name;
		this.maxCapacity = maxCapacity;
		this.teamColor = color;
	}
	
	public List<Player> getPlayersInTeam(){
		return playersInTeam;
	}
	
	public String getName(){
		return name;
	}
	public int getCapacity(){
		return maxCapacity;
	}

	public ChatColor getTeamColor() {
		return teamColor;
	}
	public void addPlayer(Player player){
		playersInTeam.add(player);
	}
	public void removePlayer(Player player){
		playersInTeam.remove(player);
	}
	
	public static Team getPlayerTeam(Player player){
		for(Team team : ScoreboardMain.getTeamsList()){
			if(team.getPlayersInTeam().contains(player)){
				return team;
			}
		}
		return null;
	}

	public static Team getTeam(String teamName) {
		for(Team team : ScoreboardMain.getTeamsList()){
			if(team.getName().equalsIgnoreCase(teamName)){
				return team;
			}
		}
		return null;
	}
	
	public List<Objectives> getObjectives(){
		return objectives;
	}
}

