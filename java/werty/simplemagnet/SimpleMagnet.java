package werty.simplemagnet;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import werty.simplemagnet.network.CommonProxy;

@Mod(modid = SReference.MODID, name = SReference.NAME, version = SReference.VERSION)
public class SimpleMagnet
{
	@Instance(SReference.MODID)
	public static SimpleMagnet	instance;
	
	@SidedProxy(clientSide = SReference.CLIENT_PROXY_CLASS, serverSide = SReference.SERVER_PROXY_CLASS)
	public static CommonProxy		proxy;
	
	public static boolean hasCheckedVersion = false;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{	
		Config.configLoad();
		SItem.init();
		SItem.register();
		
		GameRegistry.addRecipe(new ItemStack(SItem.magnet, 1), "D E", "R R", "RRR", 'D', Items.DIAMOND, 'E', Items.EMERALD, 'R', Items.REDSTONE);
		
		MinecraftForge.EVENT_BUS.register(new SEventHandler());
		
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenders();
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}