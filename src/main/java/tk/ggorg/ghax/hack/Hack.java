package tk.ggorg.ghax.hack;

import net.minecraft.client.MinecraftClient;
import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.events.Event;
import tk.ggorg.ghax.utils.ChatUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public abstract class Hack {
    private final String name;
    private final String description;
    private final String internalName;

    private HackConfigScreen configScreen;

    protected final MinecraftClient client = GHax.client;

    private boolean enabled;

    private final List<Event> events = new ArrayList<>();

    public Hack(String name, String description, String internalName, Event[] events) {
        this.name = name;
        this.description = description;
        this.internalName = internalName;
        this.events.addAll(Arrays.asList(events));
    }

    public Hack(String name, String description, String internalName) {
        this(name, description, internalName, new Event[0]);
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

    private void registerEvents() {
        for (Event event : this.events) {
            event.register((EventListener) this);
        }
    }

    private void unregisterEvents() {
        for (Event event : this.events) {
            event.unregister((EventListener) this);
        }
    }

    public final void enable() {
        if(this.isEnabled()) return;
        this.enabled = true;
        this.registerEvents();
        this.onEnable();
        ChatUtils.showHackMessage(this, ChatUtils.FORMAT + "a" + "Enabled");
    }

    public final void disable() {
        if(!this.isEnabled()) return;
        this.enabled = false;
        this.unregisterEvents();
        this.onDisable();
        ChatUtils.showHackMessage(this, ChatUtils.FORMAT + "c" + "Disabled");
    }

    public final void toggle() {
        if(this.isEnabled()) disable();
        else enable();
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

    protected void onEnable() {}
    protected void onDisable() {}
}
