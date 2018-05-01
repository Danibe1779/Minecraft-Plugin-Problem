package de.Danibe;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Start extends JavaPlugin implements Listener{
	public void onEnable() {
		//loadConfig();
		this.getServer().getPluginManager().registerEvents(this, this);
		System.out.println("Befehle v" + this.getDescription().getVersion() + "wurde Aktiviert!");
	}
	public void onDisable() {
		System.out.println("Befehle v" + this.getDescription().getVersion() + "wurde Deaktiviert!");
	}
//-----------------------------------------------------------------------------------------------
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("test")){
			sender.sendMessage("Funktioniert");
			return true;
		} return false;
		//------gift-------
	    if(cmd.getName().equalsIgnoreCase("gift")){
	    	if(sender instanceof Player) {
	    		Player p = (Player) sender;
	    		if(p.hasPermission("Befehl.gift")) {
	    			Player target = p.getWorld().getPlayer(args[0]);
	    			target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1200, 2));
	    			p.sendMessage(target + "§4wurde vergiftet!");
	    			
	    			return true;
	    		}else {
	    			p.sendMessage("§4Du hast keine Berechtigung hierzu!");
	    		} } } 
	//-----------heilung----
	    if(cmd.getName().equalsIgnoreCase("heilung")){
	    	if(sender instanceof Player) {
	    		Player p = (Player) sender;
	    		if(p.hasPermission("Befehl.heilung")) {
	    			Player target = p.getWorld().getPlayer(args[0]);
	    			target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 2));
	    			p.sendMessage(target + "§2wird geheilt!");
	    			
	    			return true;
	    		}else {
	    			p.sendMessage("§4Du hast keine Berechtigung hierzu!");
	    		} } }
	//-----------ignite-----
	    if(cmd.getName().equalsIgnoreCase("ignite")){
	    	if(sender instanceof Player) {
	    		Player p = (Player) sender;
	    		if(p.hasPermission("Befehl.ignite")) {
	    			Player target = p.getWorld().getPlayer(args[0]);
	    			target.setFireTicks(10000);
	    			return true;
	    		}else {
	    			p.sendMessage("§4Du hast keine Berechtigung hierzu!");
	    		} } }      
	//-----------kill----
	    if(cmd.getName().equalsIgnoreCase("kill")){
	    	if(sender instanceof Player) {
	    		Player p = (Player) sender;
	    		if(p.hasPermission("Befehl.kill")) {
	    			Player target = Bukkit.getPlayer(args[0]);
	    	        target.setHealth(0);
	    			return true;
	    		}else {
	    			p.sendMessage("§4Du hast keine Berechtigung hierzu!");
	    		} } }
	//-----------sethome----
	    if(cmd.getName().equalsIgnoreCase("sethome")) {
	    	if (sender instanceof Player) {
	    		Player p = (Player) sender;
	    		if(p.hasPermission("Befehl.sethome")) {
	    			if (args.length == 0) {
	    				FileConfiguration cfg = this.getConfig();
	    				String n = p.getWorld().getName();
	    				double x = p.getLocation().getX();
	    				double y = p.getLocation().getY();
	    				double z = p.getLocation().getZ();
	    				double yaw = p.getLocation().getYaw();
	    				double pitch = p.getLocation().getPitch();
	    				cfg.set("homes." + p.getName() + ".home.world", n);
	    				cfg.set("homes." + p.getName() + ".home.x", x);
	    				cfg.set("homes." + p.getName() + ".home.y", y);
	    				cfg.set("homes." + p.getName() + ".home.z", z);
	    				cfg.set("homes." + p.getName() + ".home.yaw", yaw);
	    				cfg.set("homes." + p.getName() + ".home.pitch", pitch);
	    				this.saveConfig();
	    				p.sendMessage("§2Dein Zuhause wurde gesetzt!");
	    			}else if(args.length == 1) {
	    				if(p.hasPermission("Befehl.sethome.multihome")) {
	    					FileConfiguration cfg = this.getConfig();
		    				String n = p.getWorld().getName();
		    				double x = p.getLocation().getX();
		    				double y = p.getLocation().getY();
		    				double z = p.getLocation().getZ();
		    				double yaw = p.getLocation().getYaw();
		    				double pitch = p.getLocation().getPitch();
		    				cfg.set("homes." + p.getName() + "." + args[0] + ".world", n);
		    				cfg.set("homes." + p.getName() + "." + args[0] + ".x", x);
		    				cfg.set("homes." + p.getName() + "." + args[0] + ".y", y);
		    				cfg.set("homes." + p.getName() + "." + args[0] + "z", z);
		    				cfg.set("homes." + p.getName() + "." + args[0] + ".yaw", yaw);
		    				cfg.set("homes." + p.getName() + "." + args[0] + ".pitch", pitch);
		    				this.saveConfig();
		    				p.sendMessage("§2Dein Zuhause" + args[0] + "wurde gesetzt!");
	    				}else {
	    					p.sendMessage("§4Du hast keine Berechtigung hierzu!");
	    				}
	    				} }else {
	    			p.sendMessage("§4Du hast keine Berechtigung hierzu!");
	    		} } return true;}
	    //-----------home----
	    if(cmd.getName().equalsIgnoreCase("home")) {
	    	if(sender instanceof Player) {
	    		Player p = (Player) sender;
	    				if (p.hasPermission("Befehl.sethome")) {
	    					if (args.length == 0) {
	    						FileConfiguration cfg = this.getConfig();
	    						String n = cfg.getString("homes." + p.getName() + ".home.world");
			    				double x = cfg.getDouble("homes." + p.getName() + ".home.x");
			    				double y = cfg.getDouble("homes." + p.getName() + ".home.y");
			    				double z = cfg.getDouble("homes." + p.getName() + ".home.z");
			    				double yaw = cfg.getDouble("homes." + p.getName() + ".home.yaw");
			    				double pitch = cfg.getDouble("homes." + p.getName() + ".home.pitch");
			    				Location loc = new Location(Bukkit.getWorld(n), x, y, z);
			    				loc.setYaw((float) yaw);
			    				loc.setPitch((float) pitch);
			    				p.teleport(loc);
			    				p.sendMessage("§2Du wurdest Teleportiert!");
	    					}else if (args.length == 1) {
	    						FileConfiguration cfg = this.getConfig();
	    						String n = cfg.getString("homes." + p.getName() + "." + args[0] + ".world");
			    				double x = cfg.getDouble("homes." + p.getName() + "." + args[0] + ".x");
			    				double y = cfg.getDouble("homes." + p.getName() + "." + args[0] + ".y");
			    				double z = cfg.getDouble("homes." + p.getName() + "." + args[0] + ".z");
			    				double yaw = cfg.getDouble("homes." + p.getName() + "." + args[0] + ".yaw");
			    				double pitch = cfg.getDouble("homes." + p.getName() + "." + args[0] + ".pitch");
			    				Location loc = new Location(Bukkit.getWorld(n), x, y, z);
			    				loc.setYaw((float) yaw);
			    				loc.setPitch((float) pitch);
			    				p.teleport(loc);
			    				p.sendMessage("§2Du wurdest zu" + args[0] + "Teleportiert!");
	    					} } }return true; }
	    //----------------
	    
	}
}
