package tk.ggorg.ghax.hacks;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import tk.ggorg.ghax.hack.HackConfigScreen;
import tk.ggorg.ghax.utils.ChatUtils;

public class SpeedHackConfigScreen extends HackConfigScreen<SpeedHack> {
    public SpeedHackConfigScreen(SpeedHack parentHack) {
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
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        HackConfigScreen.drawCenteredText(matrices, this.textRenderer, "Max speed", this.width / 2, this.height / 4 + 35, 0xFFFFFF);
    }
}
