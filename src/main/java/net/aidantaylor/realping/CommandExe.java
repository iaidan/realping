package net.aidantaylor.realping;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandExe implements CommandExecutor {
	private JavaPlugin javaplugin;

	public CommandExe(JavaPlugin plugin) {
		javaplugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("realping") || cmd.getName().equalsIgnoreCase("ping")) {
			if (args.length < 1) {
				if (sender instanceof Player && !sender.hasPermission("realping.ping") && !sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else {
					sender.sendMessage(ChatColor.DARK_GREEN + "You have a ping of " + RealPing.getPing((Player) sender) + " ms");
				}
			} else {
				if (sender instanceof Player && !sender.hasPermission("realping.ping.other") && !sender.isOp()) {
					sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
				} else {
					Player other = null;
					
					for(Player p : Bukkit.getOnlinePlayers()){
						if (p.getName() == args[0].toLowerCase()) {
							other = p;
							break;
						}
					}
					
					if (other == null) {
						sender.sendMessage(ChatColor.RED + "Player not found.");
					} else {
						sender.sendMessage(ChatColor.DARK_GREEN + other.getName() + " has a ping of " + RealPing.getPing(other) + " ms");
					}
				}
			}
		}

		return false;
	}
}