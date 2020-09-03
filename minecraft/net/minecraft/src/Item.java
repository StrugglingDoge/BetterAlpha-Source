package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.PrintStream;
import java.util.Random;

public class Item
{

    protected Item(int i)
    {
        maxStackSize = 64;
        maxDamage = 32;
        bFull3D = false;
        shiftedIndex = 256 + i;
        if(itemsList[256 + i] != null)
        {
            System.out.println((new StringBuilder()).append("CONFLICT @ ").append(i).toString());
        }
        itemsList[256 + i] = this;
    }

    public Item setIconIndex(int i)
    {
        iconIndex = i;
        return this;
    }
    
    public Item setIconIndex(int i, int j)
    {
        iconIndex = i + j * 16;
        return this;
    }

    public int getIconIndex(ItemStack itemstack)
    {
        return iconIndex;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        return false;
    }

    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return 1.0F;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        return itemstack;
    }

    public int getItemStackLimit()
    {
        return maxStackSize;
    }

    public int getMaxDamage()
    {
        return maxDamage;
    }

    public void hitEntity(ItemStack itemstack, EntityLiving entityliving)
    {
    }

    public void hitBlock(ItemStack itemstack, int i, int j, int k, int l)
    {
    }

    public int getDamageVsEntity(Entity entity)
    {
        return 1;
    }

    public boolean canHarvestBlock(Block block)
    {
        return false;
    }

    public void func_4019_b(ItemStack itemstack, EntityLiving entityliving)
    {
    }

    public Item setFull3D()
    {
        bFull3D = true;
        return this;
    }

    public boolean isFull3D()
    {
        return bFull3D;
    }

    public boolean shouldRotateAroundWhenRendering()
    {
        return false;
    }

    //TODO: BetterAlpha
    public Item setItemName(String s)
    {
        itemName = (new StringBuilder()).append("item.").append(s).toString();
        return this;
    }

    public String getItemName()
    {
        return itemName;
    }
    
    protected static Random itemRand = new Random();
    public static Item itemsList[] = new Item[32000];
    public static Item shovelSteel = (new ItemSpade(0, 2)).setIconIndex(2, 5).setItemName("shovelIron");
    public static Item pickaxeSteel = (new ItemPickaxe(1, 2)).setIconIndex(2, 6).setItemName("pickaxeIron");
    public static Item axeSteel = (new ItemAxe(2, 2)).setIconIndex(2, 7).setItemName("hatchetIron");
    public static Item flintAndSteel = (new ItemFlintAndSteel(3)).setIconIndex(5, 0).setItemName("flintAndSteel");
    public static Item appleRed = (new ItemFood(4, 4)).setIconIndex(10, 0).setItemName("apple");
    public static Item bow = (new ItemBow(5)).setIconIndex(5, 1).setItemName("bow");
    public static Item arrow = (new Item(6)).setIconIndex(5, 2).setItemName("arrow");
    public static Item coal = (new Item(7)).setIconIndex(7, 0).setItemName("coal");
    public static Item diamond = (new Item(8)).setIconIndex(7, 3).setItemName("emerald");
    public static Item ingotIron = (new Item(9)).setIconIndex(7, 1).setItemName("ingotIron");
    public static Item ingotGold = (new Item(10)).setIconIndex(7, 2).setItemName("ingotGold");
    public static Item swordSteel = (new ItemSword(11, 2)).setIconIndex(2, 4).setItemName("swordIron");
    public static Item swordWood = (new ItemSword(12, 0)).setIconIndex(0, 4).setItemName("swordWood");
    public static Item shovelWood = (new ItemSpade(13, 0)).setIconIndex(0, 5).setItemName("shovelWood");
    public static Item pickaxeWood = (new ItemPickaxe(14, 0)).setIconIndex(0, 6).setItemName("pickaxeWood");
    public static Item axeWood = (new ItemAxe(15, 0)).setIconIndex(0, 7).setItemName("hatchetWood");
    public static Item swordStone = (new ItemSword(16, 1)).setIconIndex(1, 4).setItemName("swordStone");
    public static Item shovelStone = (new ItemSpade(17, 1)).setIconIndex(1, 5).setItemName("shovelStone");
    public static Item pickaxeStone = (new ItemPickaxe(18, 1)).setIconIndex(1, 6).setItemName("pickaxeStone");
    public static Item axeStone = (new ItemAxe(19, 1)).setIconIndex(1, 7).setItemName("hatchetStone");
    public static Item swordDiamond = (new ItemSword(20, 3)).setIconIndex(3, 4).setItemName("swordDiamond");
    public static Item shovelDiamond = (new ItemSpade(21, 3)).setIconIndex(3, 5).setItemName("shovelDiamond");
    public static Item pickaxeDiamond = (new ItemPickaxe(22, 3)).setIconIndex(3, 6).setItemName("pickaxeDiamond");
    public static Item axeDiamond = (new ItemAxe(23, 3)).setIconIndex(3, 7).setItemName("hatchetDiamond");
    public static Item stick = (new Item(24)).setIconIndex(5, 3).setFull3D().setItemName("stick");
    public static Item bowlEmpty = (new Item(25)).setIconIndex(7, 4).setItemName("bowl");
    public static Item bowlSoup = (new ItemSoup(26, 10)).setIconIndex(8, 4).setItemName("mushroomStew");
    public static Item swordGold = (new ItemSword(27, 0)).setIconIndex(4, 4).setItemName("swordGold");
    public static Item shovelGold = (new ItemSpade(28, 0)).setIconIndex(4, 5).setItemName("shovelGold");
    public static Item pickaxeGold = (new ItemPickaxe(29, 0)).setIconIndex(4, 6).setItemName("pickaxeGold");
    public static Item axeGold = (new ItemAxe(30, 0)).setIconIndex(4, 7).setItemName("hatchetGold");
    public static Item silk = (new Item(31)).setIconIndex(8, 0).setItemName("string");
    public static Item feather = (new Item(32)).setIconIndex(8, 1).setItemName("feather");
    public static Item gunpowder = (new Item(33)).setIconIndex(8, 2).setItemName("sulphur");
    public static Item hoeWood = (new ItemHoe(34, 0)).setIconIndex(0, 8).setItemName("hoeWood");
    public static Item hoeStone = (new ItemHoe(35, 1)).setIconIndex(1, 8).setItemName("hoeStone");
    public static Item hoeSteel = (new ItemHoe(36, 2)).setIconIndex(2, 8).setItemName("hoeIron");
    public static Item hoeDiamond = (new ItemHoe(37, 3)).setIconIndex(3, 8).setItemName("hoeDiamond");
    public static Item hoeGold = (new ItemHoe(38, 1)).setIconIndex(4, 8).setItemName("hoeGold");
    public static Item seeds;
    public static Item wheat = (new Item(40)).setIconIndex(9, 1).setItemName("wheat");
    public static Item bread = (new ItemFood(41, 5)).setIconIndex(9, 2).setItemName("bread");
    public static Item helmetLeather = (new ItemArmor(42, 0, 0, 0)).setIconIndex(0, 0).setItemName("helmetCloth");
    public static Item plateLeather = (new ItemArmor(43, 0, 0, 1)).setIconIndex(0, 1).setItemName("chestplateCloth");
    public static Item legsLeather = (new ItemArmor(44, 0, 0, 2)).setIconIndex(0, 2).setItemName("leggingsCloth");
    public static Item bootsLeather = (new ItemArmor(45, 0, 0, 3)).setIconIndex(0, 3).setItemName("bootsCloth");
    public static Item helmetChain = (new ItemArmor(46, 1, 1, 0)).setIconIndex(1, 0).setItemName("helmetChain");
    public static Item plateChain = (new ItemArmor(47, 1, 1, 1)).setIconIndex(1, 1).setItemName("chestplateChain");
    public static Item legsChain = (new ItemArmor(48, 1, 1, 2)).setIconIndex(1, 2).setItemName("leggingsChain");
    public static Item bootsChain = (new ItemArmor(49, 1, 1, 3)).setIconIndex(1, 3).setItemName("bootsChain");
    public static Item helmetSteel = (new ItemArmor(50, 2, 2, 0)).setIconIndex(2, 0).setItemName("helmetIron");
    public static Item plateSteel = (new ItemArmor(51, 2, 2, 1)).setIconIndex(2, 1).setItemName("chestplateIron");
    public static Item legsSteel = (new ItemArmor(52, 2, 2, 2)).setIconIndex(2, 2).setItemName("leggingsIron");
    public static Item bootsSteel = (new ItemArmor(53, 2, 2, 3)).setIconIndex(2, 3).setItemName("bootsIron");
    public static Item helmetDiamond = (new ItemArmor(54, 3, 3, 0)).setIconIndex(3, 0).setItemName("helmetDiamond");
    public static Item plateDiamond = (new ItemArmor(55, 3, 3, 1)).setIconIndex(3, 1).setItemName("chestplateDiamond");
    public static Item legsDiamond = (new ItemArmor(56, 3, 3, 2)).setIconIndex(3, 2).setItemName("leggingsDiamond");
    public static Item bootsDiamond = (new ItemArmor(57, 3, 3, 3)).setIconIndex(3, 3).setItemName("bootsDiamond");
    public static Item helmetGold = (new ItemArmor(58, 1, 4, 0)).setIconIndex(4, 0).setItemName("helmetGold");
    public static Item plateGold = (new ItemArmor(59, 1, 4, 1)).setIconIndex(4, 1).setItemName("chestplateGold");
    public static Item legsGold = (new ItemArmor(60, 1, 4, 2)).setIconIndex(4, 2).setItemName("leggingsGold");
    public static Item bootsGold = (new ItemArmor(61, 1, 4, 3)).setIconIndex(4, 3).setItemName("bootsGold");
    public static Item flint = (new Item(62)).setIconIndex(6, 0).setItemName("flint");
    public static Item porkRaw = (new ItemFood(63, 3)).setIconIndex(7, 5).setItemName("porkchopRaw");
    public static Item porkCooked = (new ItemFood(64, 8)).setIconIndex(8, 5).setItemName("porkchopCooked");
    public static Item painting = (new ItemPainting(65)).setIconIndex(10, 1).setItemName("painting");
    public static Item appleGold = (new ItemFood(66, 42)).setIconIndex(11, 0).setItemName("appleGold");
    public static Item sign = (new ItemSign(67)).setIconIndex(10, 2).setItemName("sign");
    public static Item doorWood;
    public static Item bucketEmpty = (new ItemBucket(69, 0)).setIconIndex(10, 4).setItemName("bucket");
    public static Item bucketWater;
    public static Item bucketLava;
    public static Item minecartEmpty = (new ItemMinecart(72, 0)).setIconIndex(7, 8).setItemName("minecart");
    public static Item saddle = (new ItemSaddle(73)).setIconIndex(8, 6).setItemName("saddle");
    public static Item doorSteel;
    public static Item redstone = (new ItemRedstone(75)).setIconIndex(8, 3).setItemName("redstone");
    public static Item snowball = (new ItemSnowball(76)).setIconIndex(14, 0).setItemName("snowball");
    public static Item boat = (new ItemBoat(77)).setIconIndex(8, 8).setItemName("boat");
    public static Item leather = (new Item(78)).setIconIndex(7, 6).setItemName("leather");
    public static Item bucketMilk = (new ItemBucket(79, -1)).setIconIndex(13, 4).setItemName("milk");
    public static Item brick = (new Item(80)).setIconIndex(6, 1).setItemName("brick");
    public static Item clay = (new Item(81)).setIconIndex(9, 3).setItemName("clay");
    public static Item reed;
    public static Item paper = (new Item(83)).setIconIndex(10, 3).setItemName("paper");
    public static Item book = (new Item(84)).setIconIndex(11, 3).setItemName("book");
    public static Item slimeBall = (new Item(85)).setIconIndex(14, 1).setItemName("slimeball");
    public static Item minecartCrate = (new ItemMinecart(86, 1)).setIconIndex(7, 9).setItemName("minecartChest");
    public static Item minecartPowered = (new ItemMinecart(87, 2)).setIconIndex(7, 10).setItemName("minecartFurnace");
    public static Item egg = (new Item(88)).setIconIndex(12, 0).setItemName("egg");
    public static Item compass = (new Item(89)).setIconIndex(6, 3).setItemName("compass");
    public static Item fishingRod = (new ItemFishingRod(90)).setIconIndex(5, 4).setItemName("fishingRod");
    public static Item pocketSundial = (new Item(91)).setIconIndex(6, 4).setItemName("clock");
    public static Item lightStoneDust = (new Item(92)).setIconIndex(9, 4).setItemName("yellowDust");
    public static Item fishRaw = (new ItemFood(93, 2)).setIconIndex(9, 5).setItemName("fishRaw");
    public static Item fishCooked = (new ItemFood(94, 5)).setIconIndex(10, 5).setItemName("fishCooked");
    public static Item record13 = (new ItemRecord(2000, "13")).setIconIndex(0, 15).setItemName("record");
    public static Item recordCat = (new ItemRecord(2001, "cat")).setIconIndex(1, 15).setItemName("record");
    public final int shiftedIndex;
    public int maxStackSize;
    protected int maxDamage;
    private String itemName;
    protected int iconIndex;
    protected boolean bFull3D;

    static 
    {
        seeds = (new ItemSeeds(39, Block.crops.blockID)).setIconIndex(9, 0).setItemName("seeds");
        doorWood = (new ItemDoor(68, Material.wood)).setIconIndex(11, 2).setItemName("doorWood");
        bucketWater = (new ItemBucket(70, Block.waterStill.blockID)).setIconIndex(11, 4).setItemName("bucketWater");
        bucketLava = (new ItemBucket(71, Block.lavaStill.blockID)).setIconIndex(12, 4).setItemName("bucketLava");
        doorSteel = (new ItemDoor(74, Material.iron)).setIconIndex(12, 2).setItemName("doorIron");
        reed = (new ItemReed(82, Block.reed)).setIconIndex(11, 1).setItemName("reeds");
    }

	public int getColorFromDamage(int i) {
		return 0xffffff;
	}
}
