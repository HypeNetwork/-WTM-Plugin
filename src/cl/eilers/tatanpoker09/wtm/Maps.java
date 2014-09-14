package cl.eilers.tatanpoker09.wtm;

import java.io.File; 
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

public class Maps{
	static List<Maps> mapsList = new ArrayList<Maps>();
	private String name;
	private File mapFolder;

	public static List<Maps> getMapsList() {
		return mapsList;
	}

	public static void addMapsList(Maps mapToAdd) {
		mapsList.add(mapToAdd);
	}

	public File getMapFolder() {
		return mapFolder;
	}

	public void setMapFolder(File mapFolder) {
		this.mapFolder = mapFolder;
	}

	public double getVersion() {
		return version;
	}

	private String author;
	private double version;
	private YamlConfiguration yml;
	
	public Maps(String name, File mapFolder, String author, double version, YamlConfiguration yml){
		this.name = name;
		this.mapFolder = mapFolder;
		this.author = author;
		this.version = version;
		this.yml = yml;
	}

	public String getName(){
		return name;
	}
	public String getAuthor(){
		return author;
	}
	
	public static List<Maps> getMaps(){
		return mapsList;
	}
	
	public void registerNewMap(String name, File mapFolder, String author, double version){
		mapsList.add(new Maps(name, mapFolder, author, version, YamlConfiguration.loadConfiguration(new File(mapFolder, "map.yml"))));
	}

	public static void loadMaps() {
		File mapsDir = WaterTheMonument.getMapsDirectory();
		for(File map : mapsDir.listFiles()){
			if(map.isDirectory()){
				if(new File(map, "level.dat").exists() && new File(map, "region").exists() && new File(map, "map.yml").exists()){
					YamlConfiguration mapYml = YamlConfiguration.loadConfiguration(new File(map, "map.yml"));
					String name = mapYml.getString("name");
					String author = mapYml.getString("author");
					double version = mapYml.getDouble("version");
					addMapsList(new Maps(name, map, author, version, mapYml));
					System.out.println("[WTM] Found Map! "+name);
				}
			}
		}
	}

	public YamlConfiguration getYml() {
		return yml;
	}
}

