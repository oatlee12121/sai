package dev.oatlee.client.ui;

import dev.oatlee.client.OatleeClient;
import dev.oatlee.client.module.Module;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import java.util.*;

public final class ClickGuiScreen extends Screen {
    private final Map<String, Panel> panels = new LinkedHashMap<>();
    private Panel dragging; private int dragX, dragY;
    private final int[] colors = {0xFFFF4FCB,0xFFB36BFF,0xFF55A8FF,0xFFFF5C6C,0xFFF4F4F8};
    public ClickGuiScreen() { super(Component.literal("OatleeCLIENT")); String[] names={"Combat","Movement","Render","Player","Misc","BaseFinder"}; for(int i=0;i<names.length;i++) panels.put(names[i],new Panel(names[i],24+(i%3)*190,30+(i/3)*220)); }
    @Override public boolean isPauseScreen(){return false;}
    @Override public void render(GuiGraphics g,int mouseX,int mouseY,float delta){
        g.fill(0,0,width,height,0x9906040B); g.drawString(font,"OatleeCLIENT",18,12,0xFFFF75D2); g.drawString(font,"RIGHT SHIFT • toggle modules  |  ESC • close",150,13,0xFF887B91);
        int color=OatleeClient.CONFIG.accent; int i=0; for(Panel p:panels.values()){ renderPanel(g,p,color,mouseX,mouseY); i++; } super.render(g,mouseX,mouseY,delta);
    }
    private void renderPanel(GuiGraphics g,Panel p,int accent,int mx,int my){
        int x=p.x,y=p.y; g.fill(x+3,y+4,x+p.w+3,y+p.h+4,0x44000000); g.fill(x,y,x+p.w,y+p.h, OatleeClient.CONFIG.panel); g.fill(x,y,x+p.w,y+26,0xEE160C24); g.fill(x,y+25,x+p.w,y+27,accent);
        g.drawString(font,p.name,x+10,y+8,accent); int yy=y+35; for(Module m: OatleeClient.MODULES.all()) if(m.category.equals(p.name)){ boolean hover=mx>=x+7&&mx<=x+p.w-7&&my>=yy-3&&my<yy+20; g.fill(x+7,yy-3,x+p.w-7,yy+20,hover?0xAA3A2145:0x6620192D); g.drawString(font,m.name,x+17,yy+3,m.enabled()?accent:0xFFE2D8E6); g.fill(x+p.w-18,yy+5,x+p.w-12,yy+11,m.enabled()?0xFF55E884:0xFFE34462); yy+=25; }
        g.drawString(font,p.name.equals("BaseFinder")?"loaded-chunk intelligence":"client-side utility",x+10,y+p.h-14,0xFF8E7A96);
    }
    @Override public boolean mouseClicked(double mx,double my,int button){ for(Panel p:panels.values()){ if(mx>=p.x&&mx<=p.x+p.w&&my>=p.y&&my<=p.y+27){dragging=p;dragX=(int)mx-p.x;dragY=(int)my-p.y;return true;} int yy=p.y+35; for(Module m:OatleeClient.MODULES.all()) if(m.category.equals(p.name)){if(mx>=p.x+7&&mx<=p.x+p.w-7&&my>=yy-3&&my<yy+20){if(button==0)m.toggle(); return true;} yy+=25;}} return super.mouseClicked(mx,my,button); }
    @Override public boolean mouseReleased(double mx,double my,int button){dragging=null;return super.mouseReleased(mx,my,button);}
    @Override public boolean mouseDragged(double mx,double my,int button,double dx,double dy){if(dragging!=null){dragging.x=(int)mx-dragX;dragging.y=(int)my-dragY;return true;}return super.mouseDragged(mx,my,button,dx,dy);}
    @Override public void onClose(){OatleeClient.CONFIG.save();super.onClose();}
    private static final class Panel { final String name; int x,y; final int w=172,h=190; Panel(String n,int x,int y){name=n;this.x=x;this.y=y;} }
}
