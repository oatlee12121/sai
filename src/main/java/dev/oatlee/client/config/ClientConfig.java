package dev.oatlee.client.config;

import com.google.gson.GsonBuilder;
import dev.oatlee.client.OatleeClient;
import net.fabricmc.loader.api.FabricLoader;
import java.nio.file.*;

public final class ClientConfig {
    public int accent = 0xFFFF4FCB, panel = 0xD9161024;
    public float guiScale = 1f, scanDistance = 48f, totemHealth = 8f;
    public boolean animations = true, storageBoxes = true, storageLabels = true;
    public boolean playerEsp = true, mobEsp = false, tracers = false, autoTotem = false, baseScanner = true;
    public String theme = "Pink";
    private static Path path() { return FabricLoader.getInstance().getConfigDir().resolve(OatleeClient.MOD_ID + ".json"); }
    public static ClientConfig load() { try { if (Files.exists(path())) return new GsonBuilder().create().fromJson(Files.readString(path()), ClientConfig.class); } catch (Exception ignored) {} return new ClientConfig(); }
    public void save() { try { Files.createDirectories(path().getParent()); Files.writeString(path(), new GsonBuilder().setPrettyPrinting().create().toJson(this)); } catch (Exception ignored) {} }
}
