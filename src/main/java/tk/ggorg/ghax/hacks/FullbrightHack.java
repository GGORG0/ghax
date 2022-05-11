package tk.ggorg.ghax.hacks;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.events.Event;
import tk.ggorg.ghax.events.TickListener;
import tk.ggorg.ghax.hack.Hack;
import tk.ggorg.ghax.hack.HackConfigScreen;

public class FullbrightHack extends Hack implements TickListener {
    private double prevGamma = 0;

    public FullbrightHack() {
        super(
                "Fullbright",
                "See in the dark",
                "gamma",
                new Event[]{GHax.eventList.tickEvent}
        );
        this.setConfigScreen(new HackConfigScreen<>(this));
    }

    @Override
    protected void onEnable() {
        this.prevGamma = this.client.options.gamma;
    }

    @Override
    protected void onDisable() {
        this.client.options.gamma = this.prevGamma;
    }

    @Override
    public void onTick() {
        this.client.options.gamma = 16;
    }
}
