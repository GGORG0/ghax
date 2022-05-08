package tk.ggorg.ghax.hacks;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.events.Event;
import tk.ggorg.ghax.events.TickListener;
import tk.ggorg.ghax.hack.Hack;

public class FlyHack extends Hack implements TickListener {
    public int maxSpeed = 3;
    public double fallSpeed = -0.04;
    public double acceleration = 0.1;

    private int cooldown = 0;

    public FlyHack() {
        super(
                "Fly",
                "Just fly in survival",
                "fly",
                new Event[]{GHax.eventList.tickEvent}
        );
        this.setConfigScreen(new FlyHackConfigScreen(this));
    }

    public void onTick() {
        if(this.client.player != null){
            final boolean jumpPressed = this.client.options.jumpKey.isPressed();
            final boolean forwardPressed = this.client.options.forwardKey.isPressed();
            final boolean backPressed = this.client.options.backKey.isPressed();
            final boolean leftPressed = this.client.options.leftKey.isPressed();
            final boolean rightPressed = this.client.options.rightKey.isPressed();

            Entity object = this.client.player;
            if(this.client.player.hasVehicle()) {
                object = this.client.player.getVehicle();
            }

            Vec3d velocity = object.getVelocity();
            Vec3d newVelocity = new Vec3d(velocity.x, -fallSpeed, velocity.z);

            if(jumpPressed) {
                if(forwardPressed) {
                    newVelocity = this.client.player.getRotationVector().multiply(acceleration);
                }
                if(leftPressed && !this.client.player.hasVehicle()) {
                    newVelocity = this.client.player.getRotationVector().multiply(acceleration).rotateY((float) (Math.PI / 2));
                    newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                }
                if(rightPressed && !this.client.player.hasVehicle()) {
                    newVelocity = this.client.player.getRotationVector().multiply(acceleration).rotateY((float) (-Math.PI / 2));
                    newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                }
                if(backPressed) {
                    newVelocity = this.client.player.getRotationVector().negate().multiply(acceleration);
                }

                newVelocity = new Vec3d(newVelocity.x, (cooldown == 0 && newVelocity.y > fallSpeed) ? fallSpeed : newVelocity.y, newVelocity.z);
                object.setVelocity(newVelocity);

                if((forwardPressed || leftPressed || rightPressed || backPressed) && acceleration < maxSpeed) {
                    acceleration += 0.1;
                }
                else if (acceleration > 0.2) {
                    acceleration -= 0.2;
                }
            }

            if(cooldown == 0 || newVelocity.y <= fallSpeed) {
                cooldown = 40;
            }
            cooldown--;
        }
    }
}
