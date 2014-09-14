package cl.eilers.tatanpoker09.wtm;

import java.util.List;

import cl.eilers.tatanpoker09.wtm.scoreboard.ScoreboardMain;
import cl.eilers.tatanpoker09.wtm.scoreboard.Team;

public class Match {
	private Maps map;
	private List<Team> teams;
	private int matchId;
	
	public Match(Maps map, List<Team> teams, int match){
		this.map = map;
		this.teams = teams;
		this.matchId = match;
		loadMatch();
	}
	public void loadMatch(){
		ScoreboardMain.reloadScoreboard();
	}
	public List<Team> getTeams(){
		return teams;
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
}
