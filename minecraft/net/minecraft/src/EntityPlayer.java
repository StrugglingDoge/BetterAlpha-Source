package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

import me.strugglingdoge.betteralpha.BetterAlpha;
import me.strugglingdoge.betteralpha.util.SkinFix;
import me.strugglingdoge.betteralpha.util.Wrapper;
import net.minecraft.client.Minecraft;

public class EntityPlayer extends EntityLiving
{

    public EntityPlayer(World world)
    {
        super(world);
        inventory = new InventoryPlayer(this);
        field_9371_f = 0;
        score = 0;
        field_9369_j = false;
        field_9368_k = 0;
        field_781_a = 0;
        fishEntity = null;
        yOffset = 1.62F;
        setLocationAndAngles((double)world.spawnX + 0.5D, world.spawnY + 1, (double)world.spawnZ + 0.5D, 0.0F, 0.0F);
        health = 20;
        field_9351_C = "humanoid";
        field_9353_B = 180F;
        field_9310_bf = 20;
        texture = "/mob/char.png";
    }

    public void func_350_p()
    {
        super.func_350_p();
        field_775_e = field_774_f;
        field_774_f = 0.0F;
    }

    public void preparePlayerToSpawn()
    {
        yOffset = 1.62F;
        setSize(0.6F, 1.8F);
        super.preparePlayerToSpawn();
        health = 20;
        deathTime = 0;
    }

    protected void func_418_b_()
    {
        if(field_9369_j)
        {
            field_9368_k++;
            if(field_9368_k == 8)
            {
                field_9368_k = 0;
                field_9369_j = false;
            }
        } else
        {
            field_9368_k = 0;
        }
        swingProgress = (float)field_9368_k / 8F;
    }

    public void onUpdate()
    {
        super.onUpdate();
        field_20066_r = field_20063_u;
        field_20065_s = field_20062_v;
        field_20064_t = field_20061_w;
        double d = posX - field_20063_u;
        double d1 = posY - field_20062_v;
        double d2 = posZ - field_20061_w;
        double d3 = 10D;
        if(d > d3)
        {
            field_20066_r = field_20063_u = posX;
        }
        if(d2 > d3)
        {
            field_20064_t = field_20061_w = posZ;
        }
        if(d1 > d3)
        {
            field_20065_s = field_20062_v = posY;
        }
        if(d < -d3)
        {
            field_20066_r = field_20063_u = posX;
        }
        if(d2 < -d3)
        {
            field_20064_t = field_20061_w = posZ;
        }
        if(d1 < -d3)
        {
            field_20065_s = field_20062_v = posY;
        }
        field_20063_u += d * 0.25D;
        field_20061_w += d2 * 0.25D;
        field_20062_v += d1 * 0.25D;
    }
    
    public void onLivingUpdate()
    {
        if(worldObj.difficultySetting == 0 && health < 20 && (field_9311_be % 20) * 4 == 0)
        {
            heal(1);
        }
        inventory.decrementAnimations();
        field_775_e = field_774_f;
        super.onLivingUpdate();
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        float f1 = (float)Math.atan(-motionY * 0.20000000298023224D) * 15F;
        if(f > 0.1F)
        {
            f = 0.1F;
        }
        if(!onGround || health <= 0)
        {
            f = 0.0F;
        }
        if(onGround || health <= 0)
        {
            f1 = 0.0F;
        }
        field_774_f += (f - field_774_f) * 0.4F;
        field_9328_R += (f1 - field_9328_R) * 0.8F;
        if(health > 0)
        {
            List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expands(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    func_451_h((Entity)list.get(i));
                }

            }
        }
    }

    private void func_451_h(Entity entity)
    {
        entity.onCollideWithPlayer(this);
    }

    public int func_6417_t()
    {
        return score;
    }
    
    public void updateCloak()
    {
    	if(this.field_771_i != null && !this.field_771_i.isEmpty() && this.field_771_i == Minecraft.getMinecraft().field_6320_i.inventory)
    		playerCloakUrl = Wrapper.getGameSettings().userCapeUrl;
    	else if(this.field_771_i != null && !this.field_771_i.isEmpty() && this.field_771_i != Minecraft.getMinecraft().field_6320_i.inventory) {
    		for(String capes : BetterAlpha.playerCapes) {
    			String[] splitName = capes.split(" ");
    			if(field_771_i.equalsIgnoreCase(splitName[0])) {
    				System.out.println(splitName[0] + " url: " + splitName[1]);
    				playerCloakUrl = splitName[1];
    			}
    		}
    	}
    	else if(this.field_771_i != null && !this.field_771_i.isEmpty())
    		playerCloakUrl = SkinFix.getCapeUrl(this.field_771_i);
        cloakUrl = playerCloakUrl;
    }

    public void onDeath(Entity entity)
    {
        super.onDeath(entity);
        setSize(0.2F, 0.2F);
        setPosition(posX, posY, posZ);
        motionY = 0.10000000149011612D;
        if(field_771_i.equals("Notch"))
        {
            dropPlayerItemWithRandomChoice(new ItemStack(Item.appleRed, 1), true);
        }
        inventory.dropAllItems();
        if(entity != null)
        {
            motionX = -MathHelper.cos(((field_9331_N + rotationYaw) * 3.141593F) / 180F) * 0.1F;
            motionZ = -MathHelper.sin(((field_9331_N + rotationYaw) * 3.141593F) / 180F) * 0.1F;
        } else
        {
            motionX = motionZ = 0.0D;
        }
        yOffset = 0.1F;
    }

    public void addToPlayerScore(Entity entity, int i)
    {
        score += i;
    }

    public void dropPlayerItem(ItemStack itemstack)
    {
        dropPlayerItemWithRandomChoice(itemstack, false);
    }

    public void dropPlayerItemWithRandomChoice(ItemStack itemstack, boolean flag)
    {
        if(itemstack == null)
        {
            return;
        }
        EntityItem entityitem = new EntityItem(worldObj, posX, (posY - 0.30000001192092896D) + (double)func_373_s(), posZ, itemstack);
        entityitem.field_805_c = 40;
        float f = 0.1F;
        if(flag)
        {
            float f2 = rand.nextFloat() * 0.5F;
            float f4 = rand.nextFloat() * 3.141593F * 2.0F;
            entityitem.motionX = -MathHelper.sin(f4) * f2;
            entityitem.motionZ = MathHelper.cos(f4) * f2;
            entityitem.motionY = 0.20000000298023224D;
        } else
        {
            float f1 = 0.3F;
            entityitem.motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f1 + 0.1F;
            f1 = 0.02F;
            float f3 = rand.nextFloat() * 3.141593F * 2.0F;
            f1 *= rand.nextFloat();
            entityitem.motionX += Math.cos(f3) * (double)f1;
            entityitem.motionY += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
            entityitem.motionZ += Math.sin(f3) * (double)f1;
        }
        joinEntityItemWithWorld(entityitem);
    }

    protected void joinEntityItemWithWorld(EntityItem entityitem)
    {
        worldObj.entityJoinedWorld(entityitem);
    }

    public float getCurrentPlayerStrVsBlock(Block block)
    {
        float f = inventory.getStrVsBlock(block);
        if(isInsideOfMaterial(Material.water))
        {
            f /= 5F;
        }
        if(!onGround)
        {
            f /= 5F;
        }
        return f;
    }

    public boolean canHarvestBlock(Block block)
    {
        return inventory.canHarvestBlock(block);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Inventory");
        inventory.readFromNBT(nbttaglist);
        dimension = nbttagcompound.getInteger("Dimension");
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setTag("Inventory", inventory.writeToNBT(new NBTTagList()));
        nbttagcompound.setInteger("Dimension", dimension);
    }

    public void displayGUIChest(IInventory iinventory)
    {
    }

    public void displayWorkbenchGUI()
    {
    }

    public void func_443_a_(Entity entity, int i)
    {
    }

    public float func_373_s()
    {
        return 0.12F;
    }

    public boolean canAttackEntity(Entity entity, int i)
    {
        field_9344_ag = 0;
        if(health <= 0)
        {
            return false;
        }
        if((entity instanceof EntityMobs) || (entity instanceof EntityArrow))
        {
            if(worldObj.difficultySetting == 0)
            {
                i = 0;
            }
            if(worldObj.difficultySetting == 1)
            {
                i = i / 3 + 1;
            }
            if(worldObj.difficultySetting == 3)
            {
                i = (i * 3) / 2;
            }
        }
        if(i == 0)
        {
            return false;
        } else
        {
            return super.canAttackEntity(entity, i);
        }
    }

    protected void damageEntity(int i)
    {
        int j = 25 - inventory.getTotalArmorValue();
        int k = i * j + field_781_a;
        inventory.damageArmor(i);
        i = k / 25;
        field_781_a = k % 25;
        super.damageEntity(i);
    }

    public void displayGUIFurnace(TileEntityFurnace tileentityfurnace)
    {
    }

    public void displayGUIEditSign(TileEntitySign tileentitysign)
    {
    }

    public void func_6415_a_(Entity entity)
    {
        entity.interact(this);
    }

    public ItemStack getCurrentEquippedItem()
    {
        return inventory.getCurrentItem();
    }

    public void destroyCurrentEquippedItem()
    {
        inventory.setInventorySlotContents(inventory.currentItem, null);
    }

    public double func_388_v()
    {
        return (double)(yOffset - 0.5F);
    }

    public void func_457_w()
    {
        field_9368_k = -1;
        field_9369_j = true;
    }

    public void attackTargetEntityWithCurrentItem(Entity entity)
    {
        int i = inventory.getDamageVsEntity(entity);
        if(i > 0)
        {
            entity.canAttackEntity(this, i);
            ItemStack itemstack = getCurrentEquippedItem();
            if(itemstack != null && (entity instanceof EntityLiving))
            {
                itemstack.hitEntity((EntityLiving)entity);
                if(itemstack.stackSize <= 0)
                {
                    itemstack.func_1097_a(this);
                    destroyCurrentEquippedItem();
                }
            }
        }
    }

    public void func_9367_r()
    {
    }

    public InventoryPlayer inventory;
    public byte field_9371_f;
    public int score;
    public float field_775_e;
    public float field_774_f;
    public boolean field_9369_j;
    public double field_20066_r;
    public double field_20065_s;
    public double field_20064_t;
    public double field_20063_u;
    public double field_20062_v;
    public double field_20061_w;
    public int field_9368_k;
    public String field_771_i;
    public int dimension;
    private int field_781_a;
    public EntityFish fishEntity;
	public String playerCloakUrl;
}
