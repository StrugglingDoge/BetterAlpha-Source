package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class RecipesTools
{

    public RecipesTools()
    {
        field_1664_b = (new Object[][] {
            new Object[] {
                Block.planks, Block.cobblestone, Item.ingotIron, Item.diamond, Item.ingotGold
            }, new Object[] {
                Item.pickaxeWood, Item.pickaxeStone, Item.pickaxeSteel, Item.pickaxeDiamond, Item.pickaxeGold
            }, new Object[] {
                Item.shovelWood, Item.shovelStone, Item.shovelSteel, Item.shovelDiamond, Item.shovelGold
            }, new Object[] {
                Item.axeWood, Item.axeStone, Item.axeSteel, Item.axeDiamond, Item.axeGold
            }, new Object[] {
                Item.hoeWood, Item.hoeStone, Item.hoeSteel, Item.hoeDiamond, Item.hoeGold
            }
        });
    }

    public void func_1122_a(CraftingManager craftingmanager)
    {
        for(int i = 0; i < field_1664_b[0].length; i++)
        {
            Object obj = field_1664_b[0][i];
            for(int j = 0; j < field_1664_b.length - 1; j++)
            {
                Item item = (Item)field_1664_b[j + 1][i];
                craftingmanager.addRecipe(new ItemStack(item), new Object[] {
                    field_1665_a[j], Character.valueOf('#'), Item.stick, Character.valueOf('X'), obj
                });
            }

        }

    }

    private String field_1665_a[][] = {
        {
            "XXX", " # ", " # "
        }, {
            "X", "#", "#"
        }, {
            "XX", "X#", " #"
        }, {
            "XX", " #", " #"
        }
    };
    private Object field_1664_b[][];
}
