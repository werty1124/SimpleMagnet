package werty.simplemagnet;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config 
{
	public static boolean drawParticles;
	
	public static int magRange;
	public static int maxCooldown;
	public static int maxPull;
	
	public static double pullSpeed;
	
	public static double r1;
	public static double g1;
	public static double b1;
	public static double r2;
	public static double g2;
	public static double b2;
	
	public static String[] itemsBlackListed;
	
	public static void configLoad()
	{
		Configuration config = new Configuration(new File("config/SimpleMagnet/Main.cfg"));
		config.load();
		magRange = config.get("General", "Range", 5, "Sets the magnets reach").getInt();
		maxCooldown = config.get("General", "Cooldown", 50, "Cooldown in ticks").getInt();
		maxPull = config.get("General", "Pull", 75, "How long items will follow before cooldown").getInt();
		
		pullSpeed = config.get("General", "Speed", 0.03, "How quick items are pulled to you").getDouble();
		
		config.addCustomCategoryComment("Client", "Color of the particles, Etc.");
		drawParticles = config.get("Client", "Draw Particles", true, "Should magnet particles be drawn").getBoolean();
		
		r1 = config.get("Client", "R1", 0.92, "Red 1 amount 1.0 - 0.0").getDouble();
		g1 = config.get("Client", "G1", 0.92, "Green 1 amount 1.0 - 0.0").getDouble();
		b1 = config.get("Client", "B1", 0.92, "Blue 1 amount 1.0 - 0.0").getDouble();
		r2 = config.get("Client", "R2", 0.94, "Red 2 amount 1.0 - 0.0").getDouble();
		g2 = config.get("Client", "G2", 1.0, "Green 2 amount 1.0 - 0.0").getDouble();
		b2 = config.get("Client", "B2", 1.0, "Blue 2 amount 1.0 - 0.0").getDouble();
		
		String[] items = config.getStringList("BlackList", "General", new String[] {}, "Magnet blacklist Ex: minecraft:dirt. Entries on new line no comma");
		itemsBlackListed = items;
		config.save();
	}
}
