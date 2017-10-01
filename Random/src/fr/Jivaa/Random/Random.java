package fr.Jivaa.Random;

import java.util.Properties;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.ptibiscuit.framework.JavaPluginEnhancer;

public class Random extends JavaPluginEnhancer
{
	private static Random instance;
	private int minimum;
	private int maximum;

	@Override
	public void onEnable()
	{
		this.setup(ChatColor.GREEN + "[Random]", "random", true);
		instance = this;
		
		myLog.startFrame();
		myLog.addInFrame("Random by Jivaa");
		
		minimum = 1;
		maximum = 100;
		
		myLog.displayFrame(false);
	}
	
	@Override
	public void onDisable()
	{
		myLog.startFrame();
		myLog.addInFrame("Random disabled !");
		myLog.displayFrame(false);
	}

	@Override
	public void onConfigurationDefault(FileConfiguration arg0)
	{
		
	}

	@Override
	public void onLangDefault(Properties arg0)
	{
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!label.equalsIgnoreCase("random") && !label.equalsIgnoreCase("rand") && !label.equalsIgnoreCase("alea"))
			return false;
		
		if(args.length != 0)
		{
			if(args.length == 1)
			{
				if(isNumeric(args[0]))
				{
					minimum = 1;
					maximum = Integer.parseInt(args[0]);
				}
			}
			else if(args.length == 2)
			{
				if(isNumeric(args[0]) && isNumeric(args[1]))
				{
					minimum = Integer.parseInt(args[0]);
					maximum = Integer.parseInt(args[1]);
				}
			}
		}
		else
		{
			minimum = 1;
			maximum = 100;
		}
		
		int random = (int)(Math.random() * (maximum + 1 - minimum)) + minimum;
		
		getServer().broadcastMessage(ChatColor.YELLOW + sender.getName() + " obtient un " + random + " (" + minimum + "-" + maximum + ").");
		
		return true;
		
	}
	
	public static Random getInstance()
	{
		return instance;
	}
	
	public boolean isNumeric(String string)
	{
		try { Integer.parseInt(string); }
		catch(Exception e) { return false; }
		
		return true;
	}

}
