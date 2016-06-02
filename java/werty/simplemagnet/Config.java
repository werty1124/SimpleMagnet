package werty.simplemagnet;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config 
{
	public static int magRange;
	public static int maxCooldown;
	public static int maxPull;
	public static double pullSpeed;
	
	public static String[] itemsBlackListed;
	
	public static void configLoad()
	{
		Configuration config = new Configuration(new File("config/SimpleMagnet/Main.cfg"));
		config.load();
		magRange = config.get("General", "Range", 5, "Sets the magnets reach").getInt();
		maxCooldown = config.get("General", "Cooldown", 50, "Cooldown in ticks").getInt();
		maxPull = config.get("General", "Pull", 75, "How long items will follow before cooldown").getInt();
		
		pullSpeed = config.get("General", "Speed", 0.03, "How quick items are pulled to you").getDouble();
		
		String[] items = config.getStringList("BlackList", "General", new String[] {}, "Magnet blacklist Ex: minecraft:dirt. Entries on new line no comma");
		itemsBlackListed = items;
		config.save();
	}
}
