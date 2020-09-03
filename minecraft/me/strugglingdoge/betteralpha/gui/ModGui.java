package me.strugglingdoge.betteralpha.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.lwjgl.opengl.GL11;

import me.strugglingdoge.betteralpha.gui.NahrFont.FontType;
import me.strugglingdoge.betteralpha.util.GlStateManager;
import me.strugglingdoge.betteralpha.util.RenderUtils;
import me.strugglingdoge.betteralpha.util.StringTranslate;
import me.strugglingdoge.betteralpha.util.Wrapper;
import me.strugglingdoge.betteralpha.util.Wrapper.PlayerWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityCow;
import net.minecraft.src.EntityCreeper;
import net.minecraft.src.EntityGhast;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMobs;
import net.minecraft.src.EntityPig;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.GuiChat;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;
import net.minecraft.src.RenderManager;
import net.minecraft.src.ScaledResolution;

public class ModGui {
	
	public static boolean showBlockInfo = false;
	private static int blockX, blockY, blockZ;
	private static Block block;
	
	public static void drawGUI(ScaledResolution scaledresolution, NahrFont font) {
        GL11.glBlendFunc(770, 771);
		if (Minecraft.getMinecraft().objectMouseOver != null
				&& Minecraft.getMinecraft().objectMouseOver.entityHit != null && Wrapper.getGameSettings().showEntityInfo) {
			if (Minecraft.getMinecraft().objectMouseOver.entityHit instanceof EntityLiving) {
				Entity target = Minecraft.getMinecraft().objectMouseOver.entityHit;
				drawEntity((EntityLiving) target, scaledresolution, font);
			}
		}
		if(Wrapper.getGameSettings().showPlayerInfo)
			drawcoords(scaledresolution, font);
		if(Wrapper.getGameSettings().showBlockInfo)
			drawBlockInfo(scaledresolution, font);
		if(Wrapper.getGameSettings().showCompassWatch)
			drawPortable(scaledresolution);
	}
	
	private static void drawPortable(ScaledResolution scaledresolution) {
		int height = 65;
		GL11.glPushMatrix();
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		new RenderItem().renderItemIntoGUIBlockInfo(Wrapper.getFontRenderer(), Wrapper.getRenderEngine(),
				new ItemStack(Item.pocketSundial), scaledresolution.getScaledWidth() - height - 5, scaledresolution.getScaledHeight() - 35, 2.0f);
		RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
		
		GL11.glPushMatrix();
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		new RenderItem().renderItemIntoGUIBlockInfo(Wrapper.getFontRenderer(), Wrapper.getRenderEngine(),
				new ItemStack(Item.compass), scaledresolution.getScaledWidth() - height + 30, scaledresolution.getScaledHeight() - 35, 2.0f);
		RenderHelper.disableStandardItemLighting();
		GL11.glPopMatrix();
	}
	
	private static String getRealTime() {
		DateFormat df1 = new SimpleDateFormat("h:mm");
		DateFormat df2 = new SimpleDateFormat("a");
		Date dateobj = new Date();
		String suffix = "";
		if (df2.format(dateobj).contains("AM")) {
			suffix = "am";
		} else {
			suffix = "pm";
		}
		return df1.format(dateobj) + suffix;
	}

	public static void drawBlockInfo(ScaledResolution sc, NahrFont font) {
		if(!showBlockInfo || block == null)
			return;
		if(blockX == 0 && blockY == 0 && blockZ == 0)
			return;
		
		String blockCoords = "XYZ: " + blockX + ", " + blockY + ", " + blockZ;
		String name = "Name: " + (new StringBuilder()).append("").append(StringTranslate.getInstance().translateNamedKey(block.getBlockName())).toString().trim();
		String hardness = "Hardness: " + block.blockHardness;
		float[] findLongest = {font.getStringWidth(name),font.getStringWidth(blockCoords),font.getStringWidth(hardness)}; 
		float stringWidth = MathHelper.getMax(findLongest);
		RenderUtils.drawBorderedRect((float)sc.getScaledWidth() / 2 - 90, 1.0F, (float)(sc.getScaledWidth() / 2) + stringWidth - 35, 52.0F, 1,
				-16777216, 1593835520);
		if (block.getRenderType() >= 1 && block.getRenderType() != 5 && block.getRenderType() != 13 && block.getRenderType() != 11) {
			GL11.glPushMatrix();
			RenderHelper.enableStandardItemLighting();
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			new RenderItem().renderItemIntoGUIBlockInfo(Wrapper.getFontRenderer(), Wrapper.getRenderEngine(),
					new ItemStack(block), sc.getScaledWidth() / 2 - 90, 3, 3.0f);
			RenderHelper.disableStandardItemLighting();
			GL11.glPopMatrix();
		} else if(block.getRenderType() == -1 || block.getRenderType() == 5){
			GL11.glPushMatrix();
			RenderHelper.enableStandardItemLighting();
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			new RenderItem().renderItemIntoGUIBlockInfo(Wrapper.getFontRenderer(), Wrapper.getRenderEngine(),
					new ItemStack(block), sc.getScaledWidth() / 2 - 80, 13, 2.0f);
			RenderHelper.disableStandardItemLighting();
			GL11.glPopMatrix();
		}else {
			new RenderItem().renderItemIntoGUIBlockInfo(Wrapper.getFontRenderer(), Wrapper.getRenderEngine(),
					new ItemStack(block), sc.getScaledWidth() / 2 - 90, 10, 28.0f);
		}
		font.drawString(name, (sc.getScaledWidth() / 2) - 40,
				4, NahrFont.FontType.NORMAL, -1);
		font.drawString("ID: " + block.blockID, (sc.getScaledWidth() / 2) - 40,
				14, NahrFont.FontType.NORMAL, -1);
		font.drawString(blockCoords, (sc.getScaledWidth() / 2) - 40,
				24, NahrFont.FontType.NORMAL, -1);
		font.drawString(hardness, (sc.getScaledWidth() / 2) - 40,
				34, NahrFont.FontType.NORMAL, -1);
	}
	
	private static void drawEntity(EntityLiving entity, ScaledResolution sc, NahrFont font) {
		EntityLiving passedIn = new EntityLiving(Wrapper.theWorld());
		passedIn = entity;
		boolean smallBoi = entity.height < 1;
		String blockCoords = "XYZ: " + (int)entity.posX + ", " + (int)entity.posY + ", " + (int)entity.posZ;
		String name = "Name: " + getEntityName(entity);
		String maxHealth = Wrapper.theWorld().multiplayerWorld ? "Max Health: " + entity.health : "Health: " + entity.health;
		float[] findLongest = {font.getStringWidth(name),font.getStringWidth(blockCoords), font.getStringWidth(maxHealth)}; 
		float stringWidth = MathHelper.getMax(findLongest);
		GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glEnable(2903 /*GL_COLOR_MATERIAL*/);
        GL11.glPushMatrix();
        int j = entity instanceof EntitySpider ? sc.getScaledWidth() / 2 - 120 : sc.getScaledWidth() / 2 - 110;
        int k = smallBoi ? -40 : -25;
        GL11.glTranslatef(j + 51, k + 75, 50F);
        float f1 = entity instanceof EntityMobs || entity instanceof EntityPlayer ? 23F : entity instanceof EntityCow ? 28F : 30F;
        GL11.glScalef(-f1, f1, f1);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        float f2 = passedIn.field_735_n;
        float f3 = passedIn.rotationYaw;
        float f4 = passedIn.rotationPitch;
        GL11.glRotatef(135F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(0.0F,	0.0F, 0.0F);
        passedIn.field_735_n = 0;
        passedIn.rotationYaw = 0;
        passedIn.rotationPitch = f4;
        RenderManager.instance.func_853_a(passedIn, 0,0,0, 0.0F, 1.0F);
        passedIn.field_735_n = f2;
        passedIn.rotationYaw = f3;
        passedIn.rotationPitch = f4;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		if (entity instanceof EntitySpider) {
			RenderUtils.drawBorderedRect((float) sc.getScaledWidth() / 2 - 100, 1.0F,
					(float) (sc.getScaledWidth() / 2) + stringWidth - 35, 42.0F + entity.height, 1, -16777216,
					1593835520);
        }else {
    		RenderUtils.drawBorderedRect((float)sc.getScaledWidth() / 2 - 80, 1.0F, (float)(sc.getScaledWidth() / 2) + stringWidth - 35,smallBoi ? 42F : 52.0F + entity.height, 1,
    				-16777216, 1593835520);
        }
		int increment = smallBoi ? 10 : 16;
		
		font.drawString(name, (sc.getScaledWidth() / 2) - 40,
				4, NahrFont.FontType.NORMAL, -1);
		font.drawString(maxHealth, (sc.getScaledWidth() / 2) - 40,
				4 + increment, NahrFont.FontType.NORMAL, -1);
		font.drawString(blockCoords, (sc.getScaledWidth() / 2) - 40,
				4 + (increment * 2), NahrFont.FontType.NORMAL, -1);
	}
	
	private static String getEntityName(EntityLiving entity) {
		if(entity instanceof EntityCow)
			return "Cow";
		else if(entity instanceof EntityPig)
			return "Pig";
		else if(entity instanceof EntityChicken)
			return "Chicken";
		else if(entity instanceof EntityCreeper)
			return "Creeper";
		else if(entity instanceof EntityZombie)
			return "Zombie";
		else if(entity instanceof EntitySpider)
			return "Spider";
		else if(entity instanceof EntitySkeleton)
			return "Skeleton";
		else if(entity instanceof EntityGhast)
			return "Ghast";
		else if(entity instanceof EntityPigZombie)
			return "Zombie Pigman";
		else if(entity instanceof EntityPlayer)
			return ((EntityPlayer) entity).field_771_i;
		else if(entity instanceof EntitySheep)
			return "Sheep";
		else if(entity instanceof EntitySlime)
			return "Slime";
		else
			return "";
	}

	private static void drawcoords(ScaledResolution sc, NahrFont font) {
		List<String> info = new ArrayList<>();
		info.add(String.format("\247rIGN\2477 %s", PlayerWrapper.getUsername()));
		info.add(String.format("\247rFPS\2477 %s", Wrapper.getMinecraft().field_6292_I.split(" fps")[0]));
		info.add(String.format("\247rXYZ\2477 %s, %s, %s", MathHelper.floor_double(PlayerWrapper.getPosX()),
				MathHelper.floor_double(PlayerWrapper.getPosY()), MathHelper.floor_double(PlayerWrapper.getPosZ())));
		int y2 = sc.getScaledHeight() - 14;
		if (Wrapper.getCurrentScreen() instanceof GuiChat)
			y2 -= 14;
		for (String infoString : info) {
			font.drawString(infoString, 2, y2, FontType.NORMAL, -1);
			y2 -= 9;
		}

	}

	public static void setBlock(Block blockIn, int blockXIn, int blockYIn, int blockZIn) {
		block = blockIn;
		showBlockInfo = true;
		blockX = blockXIn;
		blockY = blockYIn;
		blockZ = blockZIn;
	}
	
}
