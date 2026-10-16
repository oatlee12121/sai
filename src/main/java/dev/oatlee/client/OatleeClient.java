package dev.oatlee.client;

import dev.oatlee.client.config.ClientConfig;
import dev.oatlee.client.module.ModuleManager;
import dev.oatlee.client.ui.ClickGuiScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public final class OatleeClient implements ClientModInitializer {
    public static final String MOD_ID = "oatleeclient";
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(MOD_ID, "main");
    public static ClientConfig CONFIG;
    public static ModuleManager MODULES;
    private static KeyMapping clickGui;

    @Override public void onInitializeClient() {
        CONFIG = ClientConfig.load();
        MODULES = new ModuleManager();
        clickGui = KeyBindingHelper.registerKeyBinding(new KeyMapping("key.oatleeclient.clickgui", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.oatleeclient"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (clickGui.consumeClick() && !(client.screen instanceof ClickGuiScreen)) client.setScreen(new ClickGuiScreen());
            MODULES.tick(client);
        });
    }
    public static void openGui() { Minecraft.getInstance().setScreen(new ClickGuiScreen()); }
}
