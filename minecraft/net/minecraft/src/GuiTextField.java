package net.minecraft.src;

public class GuiTextField extends Gui {
	  private final FontRenderer fontRenderer;
	  
	  private final int xPos;
	  
	  private final int yPos;
	  
	  private final int width;
	  
	  private final int height;
	  
	  private String text;
	  
	  private int maxStringLength;
	  
	  private int cursorCounter;
	  
	  public boolean isFocused;
	  
	  public boolean isEnabled;
	  
	  private GuiScreen parentGuiScreen;
	  
	  public GuiTextField(GuiScreen guiscreen, FontRenderer fontrenderer, int i, int j, int k, int l, String s) {
	    this.isFocused = false;
	    this.isEnabled = true;
	    this.parentGuiScreen = guiscreen;
	    this.fontRenderer = fontrenderer;
	    this.xPos = i;
	    this.yPos = j;
	    this.width = k;
	    this.height = l;
	    setText(s);
	  }
	  
	  public void setText(String s) {
	    this.text = s;
	  }
	  
	  public String getText() {
	    return this.text;
	  }
	  
	  public void updateCursorCounter() {
	    this.cursorCounter++;
	  }
	  
	  public void textboxKeyTyped(char c, int i) {
	    if (!this.isEnabled || !this.isFocused)
	      return; 
	    if (c == '\026') {
	      String s = GuiScreen.getClipboardString();
	      if (s == null)
	        s = ""; 
	      int j = this.maxStringLength - this.text.length();
	      if (j > s.length())
	        j = s.length(); 
	      if (j > 0)
	        this.text = String.valueOf(this.text) + s.substring(0, j); 
	    } 
	    if (i == 14 && this.text.length() > 0)
	      this.text = this.text.substring(0, this.text.length() - 1); 
	    if (" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~".indexOf(c) >= 0 && (this.text.length() < this.maxStringLength || this.maxStringLength == 0))
	      this.text = String.valueOf(this.text) + c; 
	  }
	  
	  public void mouseClicked(int i, int j, int k) {
	    boolean flag = (this.isEnabled && i >= this.xPos && i < this.xPos + this.width && j >= this.yPos && j < this.yPos + this.height);
	    setFocused(flag);
	  }
	  
	  public void setFocused(boolean flag) {
	    if (flag && !this.isFocused)
	      this.cursorCounter = 0; 
	    this.isFocused = flag;
	  }
	  
	  public void drawTextBox() {
	    drawRect(this.xPos - 1, this.yPos - 1, this.xPos + this.width + 1, this.yPos + this.height + 1, -6250336);
	    drawRect(this.xPos, this.yPos, this.xPos + this.width, this.yPos + this.height, -16777216);
	    if (this.isEnabled) {
	      boolean flag = (this.isFocused && this.cursorCounter / 6 % 2 == 0);
	      drawString(this.fontRenderer, this.text + (flag ? "_" : ""), this.xPos + 4, this.yPos + (this.height - 8) / 2, 14737632);
	    } else {
	      drawString(this.fontRenderer, this.text, this.xPos + 4, this.yPos + (this.height - 8) / 2, 7368816);
	    } 
	  }
	  
	  public void setMaxStringLength(int i) {
	    this.maxStringLength = i;
	  }
	}