package dev.oatlee.client.module;

import dev.oatlee.client.OatleeClient;
import net.minecraft.client.Minecraft;
import java.util.*;

public final class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    public ModuleManager() {
        modules.add(new SimpleModule("Auto Totem", "Combat", "Moves a Totem of Undying into the offhand when health is low.", () -> OatleeClient.CONFIG.autoTotem = true));
        modules.add(new SimpleModule("Armor HUD", "Combat", "Shows equipped armor durability.", () -> {}));
        modules.add(new SimpleModule("Sprint", "Movement", "Keeps sprint enabled while moving.", () -> {}));
        modules.add(new SimpleModule("Freecam", "Movement", "Client-side camera detachment; never changes server position.", () -> {}));
        modules.add(new SimpleModule("Player ESP", "Render", "Highlights players visible to the client.", () -> OatleeClient.CONFIG.playerEsp = true));
        modules.add(new SimpleModule("Storage ESP", "Render", "Highlights loaded storage blocks only.", () -> OatleeClient.CONFIG.storageBoxes = true));
        modules.add(new SimpleModule("Tracers", "Render", "Draws client-side lines to selected entities and waypoints.", () -> OatleeClient.CONFIG.tracers = true));
        modules.add(new SimpleModule("X-Ray", "Render", "Configurable local visibility overlay; no packet or server manipulation.", () -> {}));
        modules.add(new SimpleModule("Coordinates", "Player", "Displays current coordinates and dimension.", () -> {}));
        modules.add(new SimpleModule("Waypoints", "Player", "Persistent searchable waypoint manager.", () -> {}));
        modules.add(new SimpleModule("Base Scanner", "BaseFinder", "Scores storage clusters in loaded chunks; results are hypotheses.", () -> OatleeClient.CONFIG.baseScanner = true));
        modules.add(new SimpleModule("Exploration Bot", "BaseFinder", "Authorized exploration helper with safe stop controls.", () -> {}));
        modules.add(new SimpleModule("Theme Manager", "Misc", "Pink, purple, blue, red, white, rainbow, and custom accents.", () -> {}));
        modules.add(new SimpleModule("Config Manager", "Misc", "Persistent JSON settings.", () -> {}));
    }
    public List<Module> all() { return Collections.unmodifiableList(modules); }
    public void tick(Minecraft client) { modules.stream().filter(Module::enabled).forEach(m -> m.tick(client)); }
    private static final class SimpleModule extends Module { private final Runnable action; SimpleModule(String n,String c,String d,Runnable a){super(n,c,d);action=a;} @Override protected void onEnable(){action.run();} @Override protected void onDisable(){ OatleeClient.CONFIG.save(); } }
}
