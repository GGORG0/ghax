package tk.ggorg.ghax.hacks;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import tk.ggorg.ghax.utils.ChatUtils;
import tk.ggorg.ghax.hack.HackConfigScreen;

public class FlyHackConfigScreen extends HackConfigScreen<FlyHack> {
    public FlyHackConfigScreen(FlyHack parentHack) {
        super(parentHack);
    }

    @Override
    protected void init() {
        super.init();

        TextFieldWidget maxSpeedField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 45, 200, 20,
                new LiteralText("Max speed"));
        maxSpeedField.setText(String.valueOf(this.parentHack.maxSpeed));
        maxSpeedField.setChangedListener((String text) -> {
            try {
                this.parentHack.maxSpeed = Float.parseFloat(text);
                ChatUtils.showHackMessage(this.parentHack, "Max speed set to " + ChatUtils.FORMAT + "3" + this.parentHack.maxSpeed);
            }
            catch (NumberFormatException ignored) {}
        });
        this.addDrawableChild(maxSpeedField);


        TextFieldWidget fallSpeedField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 80, 200, 20,
                new LiteralText("Fall speed"));
        fallSpeedField.setText(String.valueOf(this.parentHack.fallSpeed));
        fallSpeedField.setChangedListener((String text) -> {
            try {
                this.parentHack.fallSpeed = Float.parseFloat(text);
                ChatUtils.showHackMessage(this.parentHack, "Fall speed set to " + ChatUtils.FORMAT + "3" + this.parentHack.fallSpeed);
            }
            catch (NumberFormatException ignored) {}
        });
        this.addDrawableChild(fallSpeedField);


        TextFieldWidget cooldownTimeField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 4 + 115, 200, 20,
                new LiteralText("Fall cooldown time"));
        cooldownTimeField.setText(String.valueOf(this.parentHack.cooldownTime));
        cooldownTimeField.setChangedListener((String text) -> {
            try {
                this.parentHack.cooldownTime = Integer.parseInt(text);
                ChatUtils.showHackMessage(this.parentHack, "Fall cooldown time set to " + ChatUtils.FORMAT + "3" + this.parentHack.cooldownTime);
            }
            catch (NumberFormatException ignored) {}
        });
        this.addDrawableChild(cooldownTimeField);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        HackConfigScreen.drawCenteredText(matrices, this.textRenderer, "Max speed", this.width / 2, this.height / 4 + 35, 0xFFFFFF);
        HackConfigScreen.drawCenteredText(matrices, this.textRenderer, "Fall speed", this.width / 2, this.height / 4 + 70, 0xFFFFFF);
        HackConfigScreen.drawCenteredText(matrices, this.textRenderer, "Fall cooldown time", this.width / 2, this.height / 4 + 105, 0xFFFFFF);
    }
}
