package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import me.strugglingdoge.betteralpha.util.SkinFix;
import net.minecraft.client.Minecraft;

public class EntityPlayerSP extends EntityPlayer
{

    public EntityPlayerSP(Minecraft minecraft, World world, Session session, int i)
    {
        super(world);
        field_9373_b = 20;
        field_9374_bx = false;
        mc = minecraft;
        dimension = i;
        if(session != null && session.inventory != null && session.inventory.length() > 0)
        {
        	//TODO: BetterAlpha SkinFix
            skinUrl = SkinFix.getSkinUrl(session.inventory);
            System.out.println((new StringBuilder()).append("Loading texture ").append(skinUrl).toString());
        }
        field_771_i = session.inventory;
    }

    public void func_418_b_()
    {
        super.func_418_b_();
        field_9342_ah = field_787_a.field_1174_a;
        field_9340_ai = field_787_a.field_1173_b;
        isJumping = field_787_a.field_1176_d;
    }

    public void onLivingUpdate()
    {
        field_4133_d = field_4134_c;
        if(field_9374_bx)
        {
            if(field_4134_c == 0.0F)
            {
                mc.sndManager.func_337_a("portal.trigger", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
            }
            field_4134_c += 0.0125F;
            if(field_4134_c >= 1.0F)
            {
                field_4134_c = 1.0F;
                field_9373_b = 10;
                mc.sndManager.func_337_a("portal.travel", 1.0F, rand.nextFloat() * 0.4F + 0.8F);
                mc.func_6237_k();
            }
            field_9374_bx = false;
        } else
        {
            if(field_4134_c > 0.0F)
            {
                field_4134_c -= 0.05F;
            }
            if(field_4134_c < 0.0F)
            {
                field_4134_c = 0.0F;
            }
        }
        if(field_9373_b > 0)
        {
            field_9373_b--;
        }
        field_787_a.func_797_a(this);
        if(field_787_a.field_1175_e && field_9287_aY < 0.2F)
        {
            field_9287_aY = 0.2F;
        }
        super.onLivingUpdate();
    }

    public void func_458_k()
    {
        field_787_a.func_798_a();
    }

    public void func_460_a(int i, boolean flag)
    {
        field_787_a.func_796_a(i, flag);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Score", score);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        score = nbttagcompound.getInteger("Score");
    }

    public void displayGUIChest(IInventory iinventory)
    {
        mc.displayGuiScreen(new GuiChest(inventory, iinventory));
    }

    public void displayGUIEditSign(TileEntitySign tileentitysign)
    {
        mc.displayGuiScreen(new GuiEditSign(tileentitysign));
    }

    public void displayWorkbenchGUI()
    {
        mc.displayGuiScreen(new GuiCrafting(inventory));
    }

    public void displayGUIFurnace(TileEntityFurnace tileentityfurnace)
    {
        mc.displayGuiScreen(new GuiFurnace(inventory, tileentityfurnace));
    }

    public void func_443_a_(Entity entity, int i)
    {
        mc.field_6321_h.func_1192_a(new EntityPickupFX(mc.theWorld, entity, this, -0.5F));
    }

    public int getPlayerArmorValue()
    {
        return inventory.getTotalArmorValue();
    }

    public void func_6415_a_(Entity entity)
    {
        if(entity.interact(this))
        {
            return;
        }
        ItemStack itemstack = getCurrentEquippedItem();
        if(itemstack != null && (entity instanceof EntityLiving))
        {
            itemstack.useItemOnEntity((EntityLiving)entity);
            if(itemstack.stackSize <= 0)
            {
                itemstack.func_1097_a(this);
                destroyCurrentEquippedItem();
            }
        }
    }

    public void sendChatMessage(String s)
    {
    }

    public void func_6420_o()
    {
    }

    public boolean func_381_o()
    {
        return field_787_a.field_1175_e;
    }

    public void func_4039_q()
    {
        if(field_9373_b > 0)
        {
            field_9373_b = 10;
            return;
        } else
        {
            field_9374_bx = true;
            return;
        }
    }

    public void setHealth(int i)
    {
        int j = health - i;
        if(j <= 0)
        {
            health = i;
        } else
        {
            field_9346_af = j;
            field_9335_K = health;
            field_9306_bj = field_9366_o;
            damageEntity(j);
            hurtTime = field_9332_M = 10;
        }
    }

    //TODO: BetterAlpha
    public void addChatMessage(String s) {
		this.mc.ingameGUI.addChatMessage(s);
	}
    
    public void func_9367_r()
    {
        mc.respawn();
    }

    public MovementInput field_787_a;
    private Minecraft mc;
    public int field_9373_b;
    private boolean field_9374_bx;
    public float field_4134_c;
    public float field_4133_d;
}
