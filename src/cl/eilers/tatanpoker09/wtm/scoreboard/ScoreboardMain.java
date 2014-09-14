package cl.eilers.tatanpoker09.wtm.scoreboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import cl.eilers.tatanpoker09.wtm.Match;
import cl.eilers.tatanpoker09.wtm.WaterTheMonument;
import cl.eilers.tatanpoker09.wtm.commands.CycleCommand;
import cl.eilers.tatanpoker09.wtm.commands.SetNextCommand;
import cl.eilers.tatanpoker09.wtm.utils.FileUtils;
import cl.eilers.tatanpoker09.wtm.utils.ScoreboardUtils;

public class ScoreboardMain{
	private static ScoreboardManager manager = Bukkit.getScoreboardManager();
	private static Scoreboard board;




	@SuppressWarnings("deprecation")
	public static void reloadScoreboard(Match match){
		Match.getTeams().clear();
		try {
			File mapInput = match.getMap().getMapFolder();
			File finalFolder = mapInput.getParentFile().getParentFile();
			new File(finalFolder, mapInput.getName()).mkdir();
			FileUtils.copyFolder(mapInput, new File(finalFolder, mapInput.getName()));
			World matchWorld = new WorldCreator(mapInput.getName()).createWorld();
			matchWorld.setGameRuleValue("doMobSpawning", "false");
			WaterTheMonument.setCurrentWorld(matchWorld);
		} catch (IOException e) {
			e.printStackTrace();
		}


		setBoard(manager.getNewScoreboard());
		YamlConfiguration ymlFile = YamlConfiguration.loadConfiguration(new File(match.getMap().getMapFolder(), "map.yml"));
		Match.registerTeam(new Team("Observers", 999, ChatColor.AQUA, ScoreboardUtils.getLocation(ymlFile.getString("spawn"))));

		for(String childrenNode : ymlFile.getConfigurationSection("teams").getKeys(false)){
			String teamName = ymlFile.getConfigurationSection("teams."+childrenNode).getString("name");
			int teamSize = ymlFile.getConfigurationSection("teams."+childrenNode).getInt("size");
			ChatColor color = ChatColor.valueOf(ymlFile.getConfigurationSection("teams."+childrenNode).getString("color"));	
			Location spawnPoint = ScoreboardUtils.getLocation(ymlFile.getConfigurationSection("teams."+childrenNode).getString("spawnpoint"));
			Match.registerTeam(new Team(teamName, teamSize, color, spawnPoint));
		}
		List<String> scoreboardInfo = new ArrayList<String>();
		for(Team teams : Match.getTeams()){
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
			Team observers = Team.getTeam("Observers");
			observers.addPlayer(player);
			player.setGameMode(GameMode.CREATIVE);
			player.teleport(observers.getSpawnPoint());
			player.setScoreboard(getBoard());
		}
		if(SetNextCommand.getNextMap()!=null){
			Bukkit.unloadWorld(CycleCommand.getMapToErase(), true);
			FileUtils.delete(CycleCommand.getDirToErase());
		}

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
