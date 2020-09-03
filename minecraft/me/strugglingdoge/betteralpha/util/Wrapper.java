package me.strugglingdoge.betteralpha.util;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GameSettings;
import net.minecraft.src.Gui;
import net.minecraft.src.GuiChat;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet11PlayerPosition;
import net.minecraft.src.PlayerController;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderManager;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.SoundManager;
import net.minecraft.src.Timer;
import net.minecraft.src.World;
import net.minecraft.src.WorldProvider;

public class Wrapper {

	/*
	 * 
	 * Main
	 * 
	 */

	public static Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}

	public static EntityPlayerSP thePlayer() {
		return getMinecraft().thePlayer;
	}

	public static PlayerController playerController() {
		return getMinecraft().field_6327_b;
	}

	public static World theWorld() {
		return getMinecraft().theWorld;
	}

	public static ScaledResolution scaledResolution() {
		return new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth,
				Minecraft.getMinecraft().displayHeight);
	}

	public static Timer getTimer() {
		return getMinecraft().timer;
	}

	public static String minecraftDirAbsolute() {
		return getMinecraft().minecraftDir.getAbsolutePath();
	}

	public static GameSettings getGameSettings() {
		return getMinecraft().gameSettings;
	}

	/*
	 * 
	 * GUI
	 * 
	 */

	public static void drawRect(int x1, int y1, int x2, int y2, int color) {
		Gui.drawRect(x1, y1, x2, y2, color);
	}

	public static void displayScreen(GuiScreen screen) {
		getMinecraft().displayGuiScreen(screen);
	}

	public static GuiIngame inGameGUI() {
		return getMinecraft().ingameGUI;
	}

	public static GuiScreen getCurrentScreen() {
		return getMinecraft().currentScreen;
	}

	/*
	 * 
	 * Render Stuff
	 * 
	 */

	public static EntityRenderer getEntityRenderer() {
		return getMinecraft().field_9243_r;
	}

	public static RenderManager getRenderManager() {
		return getMinecraft().getRenderManager();
	}

	public static FontRenderer getFontRenderer() {
		return getMinecraft().fontRenderer;
	}

	public static void drawString(String s, int x, int y, int color) {
		getFontRenderer().drawString(s, x, y, color);
	}

	public static void drawStringWithShadow(String s, int x, int y, int color) {
		getFontRenderer().drawStringWithShadow(s, x, y, color);
	}

	public static RenderEngine getRenderEngine() {
		return Minecraft.getMinecraft().renderEngine;
	}

	public static RenderGlobal getRenderGlobal() {
		return getMinecraft().field_6323_f;
	}

	public static double renderPosX() {
		return RenderManager.field_1232_b;
	}

	public static double renderPosY() {
		return RenderManager.field_1231_c;
	}

	public static double renderPosZ() {
		return RenderManager.field_1230_d;
	}

	public static void loadRenderers() {
		getRenderGlobal().func_958_a();
	}

	public static void reloadWorld() {
		loadRenderers();
		WorldProvider.generateLightBrightnessTable();
	}

	/*
	 * 
	 * Packet stuff
	 * 
	 */

	public static NetClientHandler getSendQueue() {
		return getMinecraft().getSendQueue();
	}

	public static void addToSendQueue(Packet packet) {
		getSendQueue().addToSendQueue(packet);
	}

	/*
	 * 
	 * Sound
	 * 
	 */

	public static SoundManager getSoundManager() {
		return getMinecraft().sndManager;
	}

	public static void playSound(String sound, float volume, float f) {
		getSoundManager().func_337_a(sound, volume, f);
	}

	public static void playClick(float f) {
		getSoundManager().func_337_a("random.click", 1.0f, f);
	}

	/*
	 * 
	 * Player stuff
	 * 
	 */

	public static class PlayerWrapper {

		public static double getPosX() {
			return thePlayer().posX;
		}

		public static double getPosY() {
			return thePlayer().posY;
		}

		public static double getPosZ() {
			return thePlayer().posZ;
		}

		public static float getEyeHeight() {
			return thePlayer().func_373_s();
		}

		public static float distanceToEntity(Entity entity) {
			return thePlayer().getDistanceToEntity(entity);
		}

		public static String getUsername() {
			return getMinecraft().field_6320_i.inventory;
		}

		public static InventoryPlayer getInventory() {
			return thePlayer().inventory;
		}

		public static void dropItem(ItemStack item) {
			thePlayer().dropPlayerItemWithRandomChoice(item, false);
		}

//		public static void addToInventory(int itemID, int amount) {
//			try {
//				getInventory().addItemStackToInventory(new ItemStack(itemID, amount, 0));
//			} catch (Exception e) {
//				WolfClient.addChatMessage("Item ID: " + itemID + " does not exist.");
//			}
//		}

		public static boolean onGround() {
			return thePlayer().onGround;
		}

		public static void setOnGround(boolean onGround) {
			thePlayer().onGround = onGround;
		}

		public static int getHealth() {
			return thePlayer().health;
		}
		
		public static void heal(int health) {
			thePlayer().heal(health);
		}
		
		public static void attackEntity(Entity entity) {
			playerController().func_6472_b(thePlayer(), entity);
		}

		public static boolean canSeeEntity(Entity entity) {
			return thePlayer().canEntityBeSeen(entity);
		}

		public static void swingItem() {
			thePlayer().func_457_w();
		}

		public static void setPos(final double d, final double d1, final double d2) {
			thePlayer().setPosition(d, d1, d2);
			final double d3 = thePlayer().posX;
			final double d4 = thePlayer().boundingBox.minY;
			final double d5 = thePlayer().posY;
			final double d6 = thePlayer().posZ;
			addToSendQueue(new Packet11PlayerPosition(d3, d4, d5, d6, onGround()));
		}

		public static boolean isDead() {
			return thePlayer().isDead;
		}

	}

}