package tk.ggorg.ghax.hacks;

import net.minecraft.client.network.ClientPlayerEntity;
import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.events.Event;
import tk.ggorg.ghax.events.TickListener;
import tk.ggorg.ghax.hack.Hack;
import tk.ggorg.ghax.hack.HackConfigScreen;

public class SprintHack extends Hack implements TickListener {
    public SprintHack() {
        super(
                "Sprint",
                "Automatically sprints",
                "sprint",
                new Event[]{GHax.eventList.tickEvent}
        );
        this.setConfigScreen(new HackConfigScreen<>(this));
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = this.client.player;

        if(player.horizontalCollision || player.isSneaking())
            return;

        if(player.isInsideWaterOrBubbleColumn() || player.isSubmergedInWater())
            return;

        if(player.forwardSpeed > 0)
            player.setSprinting(true);
    }
}
