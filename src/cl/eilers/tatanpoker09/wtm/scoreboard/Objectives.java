package cl.eilers.tatanpoker09.wtm.scoreboard;

import org.bukkit.block.Block;

public class Objectives {
	private String name;
	private ObjectiveType type;
	private Block block;
	
	public Objectives(String name, ObjectiveType type, Block block){
		this.name = name;
		this.type = type;
		this.block = block;
	}

	public String getDisplayName() {
		return name;
	}
	public ObjectiveType getObjectiveType(){
		return type;
	}
	public Block getBlock(){
		return block;
	}
}
