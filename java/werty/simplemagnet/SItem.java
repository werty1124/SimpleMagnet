package werty.simplemagnet;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import werty.simplemagnet.Items.ItemMagnet;

public class SItem 
{
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	
	public static Item magnet;
	
	public static void init()
	{
		magnet = new ItemMagnet().setCreativeTab(CreativeTabs.MISC).setUnlocalizedName("simple_magnet");
	}
	
	public static void register()
	{
		register(magnet);
	}
	
	public static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
		itemList.add(item);
	}
	
	public static void registerRenders()
	{
		for(Item item: itemList)
		{
			registerRender(item);
		}
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(SReference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
