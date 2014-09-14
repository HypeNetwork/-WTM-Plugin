package cl.eilers.tatanpoker09.wtm.scoreboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import cl.eilers.tatanpoker09.wtm.commands.SetNextCommand;
import cl.eilers.tatanpoker09.wtm.utils.ScoreboardUtils;

public class ScoreboardMain{
	private static ScoreboardManager manager = Bukkit.getScoreboardManager();
	private static Scoreboard board;
	private static List<Team> teamsList = new ArrayList<Team>();

	public static void registerTeam(Team teamToAdd){
		teamsList.add(teamToAdd);
	}


	public static List<Team> getTeamsList() {
		return teamsList;
	}


	@SuppressWarnings("deprecation")
	public static void reloadScoreboard(){
		teamsList.clear();
		registerTeam(new Team("Observers", 999, ChatColor.AQUA));
		setBoard(manager.getNewScoreboard());
		YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(new File(SetNextCommand.getNextMap().getMapFolder(), "map.yml"));
		for(String childrenNode : ymlFile.getConfigurationSection("teams").getKeys(false)){
			String teamName = ymlFile.getConfigurationSection("teams."+childrenNode).getString("name");
			int teamSize = ymlFile.getConfigurationSection("teams."+childrenNode).getInt("size");
			ChatColor color = ChatColor.valueOf(ymlFile.getConfigurationSection("teams."+childrenNode).getString("color"));		
			registerTeam(new Team(teamName, teamSize, color));
		}
		List<String> scoreboardInfo = new ArrayList<String>();
		for(Team teams : getTeamsList()){
			if(!teams.getName().equals("Observers")){
				scoreboardInfo.add(teams.getTeamColor()+teams.getName());
				for(Objectives objectives : teams.getObjectives()){
					scoreboardInfo.add(objectives.getDisplayName());
				}
			}
		}
		Objective objective = board.registerNewObjective("Objetivos", "dummy");
		objective.setDisplayName(ChatColor.GOLD+"Objetivos");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		ScoreboardUtils.organizeScoreboard(scoreboardInfo, board);
		for(Player player : Bukkit.getOnlinePlayers()){
			player.setScoreboard(getBoard());
		}
		Bukkit.broadcastMessage("LOADED");
	}

	public static ScoreboardManager getManager(){
		return manager;
	}


	public static Scoreboard getBoard() {
		return board;
	}


	public static void setBoard(Scoreboard board) {
		ScoreboardMain.board = board;
	}

}
