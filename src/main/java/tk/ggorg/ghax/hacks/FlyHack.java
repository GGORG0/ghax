package tk.ggorg.ghax.hacks;

import net.minecraft.client.MinecraftClient;
import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.events.Event;
import tk.ggorg.ghax.events.TickListener;
import tk.ggorg.ghax.hack.Hack;
import tk.ggorg.ghax.hack.HackConfigScreen;

public class FlyHack extends Hack implements TickListener {
    public FlyHack() {
        super(
                "Fly",
                "Just fly in survival",
                "fly"
        );
        this.setConfigScreen(new HackConfigScreen(this));
        GHax.eventList.tickEvent.register(this);
    }

    public void onTick() {
        if(this.isEnabled()){
            GHax.LOGGER.info("[FlyHack] tick");
        }
    }
}
