package cl.eilers.tatanpoker09.wtm.utils;

public class TatanUtils {

	public static boolean isNumeric(String str){  
		try{  
			@SuppressWarnings("unused")
			double Number = Double.parseDouble(str); 
		}catch(NumberFormatException nfe){  
			System.out.println(nfe.getStackTrace());
			return false;  
		}  
		return true;  
	}

	public static String concatenateArgs(String[] inputString){
		String mapName = null;
			int i = 0;
			while(i < inputString.length) {
				mapName += " " + inputString[i];
				i++;
			}
			mapName = mapName.substring(5);
		return mapName;
	}
}