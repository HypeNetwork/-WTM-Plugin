package cl.eilers.tatanpoker09.wtm.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cl.eilers.tatanpoker09.wtm.Match;
import cl.eilers.tatanpoker09.wtm.utils.TatanUtils;

public class StartCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length > 0){
			if(TatanUtils.isNumeric(args[0])){
				
				Match.getMatch().startCountdown(Integer.parseInt(args[0]));
				return true;
			}
		}
		return false;
	}

}
