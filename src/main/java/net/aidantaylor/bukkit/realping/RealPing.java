package net.aidantaylor.bukkit.realping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
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

		log(getName() + " has been enabled!", true);
	}

	@Override
	public void onDisable() {
		log(getName() + " has been disabled!", true);
	}
	
	public static int getPing(Player p) {
        Class<?> CPClass;
        
        String bpName  = Bukkit.getServer().getClass().getPackage().getName(),
        	   version = bpName.substring(bpName.lastIndexOf(".") + 1, bpName.length());
        
        try {
        	CPClass = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
        	Object CraftPlayer = CPClass.cast(p);
            
            Method getHandle = CraftPlayer.getClass().getMethod("getHandle", new Class[0]);
            Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);
            
            Field ping = EntityPlayer.getClass().getDeclaredField("ping");
            
            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return 0;
	}
	
	public void log(String string) {
		log(string, false);
	}
	
	public void log(String string, boolean bypassdebug) {
		if (bypassdebug == true || debug == true) {
			getLogger().info(string);
		}
	}
}
