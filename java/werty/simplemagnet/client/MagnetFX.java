package werty.simplemagnet.client;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import werty.simplemagnet.Config;

public class MagnetFX extends Particle
{
	private ResourceLocation texture = new ResourceLocation("simplemagnet:entitys/magnet_fx");
	Random rand = new Random();
	public int life = 8;

	public MagnetFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) 
	{
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

		this.particleRed = (float)Config.r1;
		this.particleGreen = (float)Config.g1;
    	this.particleBlue = (float)Config.b1;
		
		this.particleAge = life;
		
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(texture.toString());
	    this.setParticleTexture(sprite);

	}
   
    @Override
    public int getFXLayer(){
        return 1;
    }
    
    @Override
    public void onUpdate()
    {
    	
    	if (this.particleAge-- <= 0){
            this.setExpired();
        }
    	
    	if(this.particleAge % 2 == 0)
    	{
    		this.particleRed = (float)Config.r1;
    		this.particleGreen = (float)Config.g1;
        	this.particleBlue = (float)Config.b1;
    	}
    	else
    	{
    		this.particleRed = (float)Config.r2;
    		this.particleGreen = (float)Config.g2;
        	this.particleBlue = (float)Config.b2;
    	}

    }


}
