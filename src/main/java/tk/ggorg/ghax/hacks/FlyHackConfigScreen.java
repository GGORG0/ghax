package tk.ggorg.ghax.hacks;

import net.minecraft.client.gui.widget.TextFieldWidget;
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

        TextFieldWidget maxSpeedField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 6 + 88, 200, 20,
                new LiteralText("Max speed"));
        maxSpeedField.setText(String.valueOf(this.parentHack.maxSpeed));
        maxSpeedField.setChangedListener((String text) -> {
            try {
                this.parentHack.maxSpeed = Integer.parseInt(text);
                ChatUtils.showHackMessage(this.parentHack, "Max speed set to " + ChatUtils.FORMAT + "3" + this.parentHack.maxSpeed);
            }
            catch (NumberFormatException ignored) {}
        });
        this.addDrawableChild(maxSpeedField);
    }
}
