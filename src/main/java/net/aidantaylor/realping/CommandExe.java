package net.aidantaylor.realping;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandExe implements CommandExecutor {
	private RealPing rp;

	public CommandExe(JavaPlugin plugin) {
		rp = (RealPing) plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("realping") || cmd.getName().equalsIgnoreCase("ping")) {
			if (args.length < 1) {
				if (sender instanceof Player) {
					if (sender.hasPermission("realping.ping") && sender.isOp()) {
						sender.sendMessage(ChatColor.DARK_GREEN + "You have a ping of " + RealPing.getPing((Player) sender) + " ms");
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Correct usage: /ping [player]");
				}
			} else {
				if ((sender instanceof Player && !sender.hasPermission("realping.ping.other") && !sender.isOp())) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else {
					boolean found = false;
					
					for(Player p : Bukkit.getOnlinePlayers()){
						String name = p.getName();
						
						if (name.equalsIgnoreCase(args[0])) {
							sender.sendMessage(ChatColor.DARK_GREEN + name + " has a ping of " + RealPing.getPing(p) + " ms");
							found = true;
							break;
						}
					}
					
					if (!found) {
						sender.sendMessage(ChatColor.RED + "Player not found.");
					}
				}
			}
		}

		return true;
	}
}