package tk.ggorg.ghax.hacks;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.events.Event;
import tk.ggorg.ghax.events.TickListener;
import tk.ggorg.ghax.hack.Hack;

public class SpeedHack extends Hack implements TickListener {
    public float maxSpeed = 0.66f;
    private boolean prevSprintHack = false;

    public SpeedHack() {
        super(
                "Speed",
                "Be fast",
                "speed",
                new Event[]{GHax.eventList.tickEvent}
        );
        this.setConfigScreen(new SpeedHackConfigScreen(this));
    }

    @Override
    protected void onEnable() {
        this.prevSprintHack = GHax.hackList.sprintHack.isEnabled();
        if(!this.prevSprintHack)
            GHax.hackList.sprintHack.enable();
    }

    @Override
    protected void onDisable() {
        if(!this.prevSprintHack)
            GHax.hackList.sprintHack.disable();
    }

    @Override
    public void onTick() {
        if(this.client.player != null) {
            if (this.client.player.isSneaking() || (this.client.player.forwardSpeed == 0 && this.client.player.sidewaysSpeed == 0))
                return;

            if (!this.client.player.hasVehicle() && !this.client.player.isOnGround()) return;

            Entity object = this.client.player;
            if (this.client.player.hasVehicle()) {
                object = this.client.player.getVehicle();
            }

            Vec3d velocity = object.getVelocity();

            if (!this.client.player.hasVehicle()) {
                object.setVelocity(velocity.x * 1.8, velocity.y + 0.1, velocity.z * 1.8);
                velocity = object.getVelocity();
            }

            final double currentSpeed = Math.sqrt(Math.pow(velocity.x, 2) + Math.pow(velocity.z, 2));

            if (currentSpeed > this.maxSpeed) {
                object.setVelocity(
                        velocity.x / currentSpeed * maxSpeed,
                        velocity.y,
                        velocity.z / currentSpeed * maxSpeed
                );
            }
        }
    }
}
