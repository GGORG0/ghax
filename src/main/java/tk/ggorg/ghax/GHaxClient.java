package tk.ggorg.ghax;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class GHaxClient implements ClientModInitializer {
    private static KeyBinding openGuiKey;

    private static String modVersion;

    @Override
    public void onInitializeClient() {
        ModContainer mod = FabricLoader.getInstance()
                .getModContainer(GHax.modId)
                .orElseThrow(NullPointerException::new);
        modVersion = mod.getMetadata()
                .getVersion()
                .getFriendlyString();

        openGuiKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.ghax.opengui",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_RIGHT_SHIFT,
                        "category.ghax.keys"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                GHax.client.setScreen(GHaxMenuScreen.getInstance());
            }
        });
    }

    public static String getModVersion() {
        if(modVersion == null) return "";
        return modVersion;
    }
}
