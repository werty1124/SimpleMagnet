package werty.simplemagnet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import werty.simplemagnet.Items.ItemMagnet;

public class SEventHandler 
{
	@SubscribeEvent
	public void itemTossed(ItemTossEvent event)
	{
		if(getItemsSlot(event.getPlayer(), SItem.magnet) != -1)
		{
			ItemMagnet plant = (ItemMagnet) event.getPlayer().inventory.getStackInSlot(getItemsSlot(event.getPlayer(), SItem.magnet)).getItem();
			plant.addCoolDown(event.getPlayer().inventory.getStackInSlot(getItemsSlot(event.getPlayer(), SItem.magnet)), event.getPlayer().worldObj);
		}
	}
	
	private int getItemsSlot(EntityPlayer player, Item item)
	{
		for (int i = 0; i < player.inventory.mainInventory.length; ++i)
		{
			if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == item)
			{
				return i;
			}
		}
		return -1;
	}
}
