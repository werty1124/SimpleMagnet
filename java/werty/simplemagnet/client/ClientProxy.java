package werty.simplemagnet.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import werty.simplemagnet.SItem;
import werty.simplemagnet.network.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
	}

	@Override
	public void registerRenders()
	{
		SItem.registerRenders();
	}

	@Override
	public void openGUI(Object gui)
	{
		Minecraft.getMinecraft().displayGuiScreen((GuiScreen) gui);
	}
}
