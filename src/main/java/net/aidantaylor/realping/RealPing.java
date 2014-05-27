package net.aidantaylor.realping;

import net.minecraft.server.v1_7_R3.EntityPlayer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RealPing extends JavaPlugin implements Listener {
	private boolean debug = true;
	private FileConfiguration configFile;

	@Override
	public void onEnable() {
		getCommand("realping").setExecutor(new CommandExe(this));
		getServer().getPluginManager().registerEvents(this, this);

		//this.saveDefaultConfig();

		log(getName() + " has been enabled!", true);
		load();
	}

	@Override
	public void onDisable() {
		log(getName() + " has been disabled!", true);
	}
	public void load() {
		//configFile = getConfig();
	}
	
	public static int getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p;
		EntityPlayer ep = cp.getHandle();
		
		return ep.ping;
	}
	
	@Override
	public void reloadConfig() {
		super.reloadConfig();
		getConfig().options().copyDefaults(true);
		
		load();
	}
	
	public void log(String string) {
		log(string, false);
	}
	
	public void log(String string, boolean bypassdebug) {
		if (bypassdebug == true || debug == true) {
			getLogger().info(string);
		}
	}

	public FileConfiguration getConfigFile() {
		return configFile;
	}

	public void setConfigFile(FileConfiguration config) {
		configFile = config;
	}
}
