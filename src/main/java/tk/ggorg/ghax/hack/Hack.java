package tk.ggorg.ghax.hack;

import net.minecraft.client.MinecraftClient;

public abstract class Hack {
    private final String name;
    private final String description;
    private final String internalName;

    private HackConfigScreen configScreen;

    private final MinecraftClient client = MinecraftClient.getInstance();

    private boolean enabled;

    public Hack(String name, String description, String internalName) {
        this.name = name;
        this.description = description;
        this.internalName = internalName;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getButtonText() {
        return this.name + ": " + (this.enabled ? "enabled" : "disabled");
    }

    public final String getInternalName() {
        return this.internalName;
    }

    public final void enable() {
        this.enabled = true;
    }

    public final void disable() {
        this.enabled = false;
    }

    public final void toggle() {
        this.enabled = !this.enabled;
    }

    public final boolean isEnabled() {
        return this.enabled;
    }

    public final void openConfigScreen() {
        client.setScreen(this.configScreen);
    }

    public final void setConfigScreen(HackConfigScreen screen) {
        this.configScreen = screen;
    }
}
