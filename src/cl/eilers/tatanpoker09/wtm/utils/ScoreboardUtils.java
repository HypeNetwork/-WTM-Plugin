package cl.eilers.tatanpoker09.wtm.utils;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import cl.eilers.tatanpoker09.wtm.WaterTheMonument;

public class ScoreboardUtils {
	@SuppressWarnings("deprecation")
	public static void organizeScoreboard(List<String> scoreboardInfo, Scoreboard board){
		Collections.reverse(scoreboardInfo);
		for(int amount = scoreboardInfo.size();amount>0;amount--){

			Score score = board.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(scoreboardInfo.get(amount-1)));
			score.setScore(amount-1);
		}
	}
	public static Location getLocation(String str){
		String[] arg = str.split(",");
		double[] parsed = new double[3];
		for (int a = 0; a < 3; a++) {
			parsed[a] = Double.parseDouble(arg[a]);
		}
		Location returnedLocation = new Location (WaterTheMonument.getCurrentWorld(), parsed[0], parsed[1]+0.5, parsed[2]);
		returnedLocation.setYaw(Float.parseFloat(arg[3]));
		return returnedLocation;
	}
}
