package werty.simplemagnet.Items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;
import werty.simplemagnet.Config;

public class ItemMagnet extends Item
{	
	private static final List<String> BLACKLIST = Arrays.asList(Config.itemsBlackListed);
	
	public boolean enabled = true;
	
	public ItemMagnet()
	{
		this.setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote)
		{
			if(this.enabled == true)
			{
				this.enabled = false;
			}
			else
			{
				this.enabled = true;
			}
		}
		return EnumActionResult.SUCCESS;
    }
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		if (stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		if (!worldIn.isRemote)
		{
			stack.getTagCompound().setInteger("cooldown", 0);
			stack.getTagCompound().setInteger("pull", 0);
		}
	}
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		if(entityIn instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)entityIn;
			
			if(amountHeld(player) <= 1 && enabled == true)
			{
				double x = entityIn.posX;
				double y = entityIn.posY + 1.5;
				double z = entityIn.posZ;
				
				int range = Config.magRange;
				int cooldown = stack.getTagCompound().getInteger("cooldown");
				int pull = stack.getTagCompound().getInteger("pull");
				
				boolean isPulling = false;
					
				List<EntityItem> items = entityIn.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(x - range, y - range, z - range, x + range, y + range, z + range));
				for(EntityItem e: items)
				{
					if(!player.isSneaking() && cooldown == 0)
					{
						if(!isBlackListed(e.getEntityItem()))
						{
							isPulling = true;
							
							double factor = Config.pullSpeed;
							e.motionX += (x - e.posX) * factor;
							e.motionY += (y - e.posY) * factor;
							e.motionZ += (z - e.posZ) * factor;
							
							if(pull == Config.maxPull)
							{
								cooldown = Config.maxCooldown;
								pull = 0;
							}
						}
					}
				}
				if(items.isEmpty())
				{
					pull = 0;
					stack.getTagCompound().setInteger("pull", pull);
					isPulling = false;
				}
				if(isPulling == true)
				{
					pull++;
					stack.getTagCompound().setInteger("pull", pull);
				}
				if(cooldown > 0)
				{
					cooldown--;
					stack.getTagCompound().setInteger("cooldown", cooldown);
				}
			}
		}
	}
	
	public void addCoolDown(ItemStack stack, World worldIn)
	{
		if (stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		if (!worldIn.isRemote)
		{
			stack.getTagCompound().setInteger("cooldown", Config.maxCooldown);
		}
	}
	
	public int amountHeld(EntityPlayer player)
	{
		int amount = 0;
		for (int i = 0; i < player.inventory.mainInventory.length; ++i)
		{
			if (player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == this)
			{
				amount++;
			}
		}
		return amount;
	}
	
	public boolean isBlackListed(ItemStack item)
	{
		if(item.getItem() instanceof ItemBlock)
		{
			return this.BLACKLIST.contains(Block.REGISTRY.getNameForObject(Block.getBlockFromItem(item.getItem())).toString());
		}
		return this.BLACKLIST.contains(Item.REGISTRY.getNameForObject(item.getItem()).toString());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		if (stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
			//tooltip.add("Cooldown: "+Integer.toString(stack.getTagCompound().getInteger("cooldown")));
			//tooltip.add("Pull: "+Integer.toString(stack.getTagCompound().getInteger("pull")));
			if(this.amountHeld(playerIn) > 1)
			{
				tooltip.add("Cannot hold more than 1 magnet!");
			}
			if(this.enabled == true)
			{
				tooltip.add("Enabled");
			}
			else
			{
				tooltip.add("Disabled");
			}
	}
}
