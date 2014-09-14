package cl.eilers.tatanpoker09.wtm.utils;

import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardUtils {
	@SuppressWarnings("deprecation")
	public static void organizeScoreboard(List<String> scoreboardInfo, Scoreboard board){
		Collections.reverse(scoreboardInfo);
		for(int amount = scoreboardInfo.size();amount>0;amount--){
			
			Score score = board.getObjective(DisplaySlot.SIDEBAR).getScore(Bukkit.getOfflinePlayer(scoreboardInfo.get(amount-1)));
			score.setScore(amount-1);
		}
	}
}
