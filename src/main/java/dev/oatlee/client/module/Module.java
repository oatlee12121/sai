package dev.oatlee.client.module;

import net.minecraft.client.Minecraft;

public abstract class Module {
    public final String name, category, description;
    private boolean enabled;
    protected Module(String name, String category, String description) { this.name=name; this.category=category; this.description=description; }
    public boolean enabled() { return enabled; }
    public void toggle() { setEnabled(!enabled); }
    public void setEnabled(boolean value) { if (enabled == value) return; enabled=value; if (value) onEnable(); else onDisable(); }
    protected void onEnable() {}
    protected void onDisable() {}
    public void tick(Minecraft client) {}
}
