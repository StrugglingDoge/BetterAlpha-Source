package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

public class ScaledResolution {
	private int scaledWidth;

	private int scaledHeight;

	public double field_25121_a;

	public double field_25120_b;

	public int scaleFactor;

	public ScaledResolution(GameSettings gamesettings, int i, int j) {
		this.scaledWidth = i;
		this.scaledHeight = j;
		this.scaleFactor = 1;
		int k = gamesettings.guiScale;
		if (k == 0)
			k = 1000;
		for (; this.scaleFactor < k && this.scaledWidth / (this.scaleFactor + 1) >= 320
				&& this.scaledHeight / (this.scaleFactor + 1) >= 240; this.scaleFactor++)
			;
		this.field_25121_a = this.scaledWidth / this.scaleFactor;
		this.field_25120_b = this.scaledHeight / this.scaleFactor;
		this.scaledWidth = (int) Math.ceil(this.field_25121_a);
		this.scaledHeight = (int) Math.ceil(this.field_25120_b);
	}

	public int getScaledWidth() {
		return this.scaledWidth;
	}

	public int getScaledHeight() {
		return this.scaledHeight;
	}

	public int getScaleFactor() {
		return this.scaleFactor;
	}
}
