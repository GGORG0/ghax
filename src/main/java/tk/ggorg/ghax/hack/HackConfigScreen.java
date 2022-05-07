package tk.ggorg.ghax.hack;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import tk.ggorg.ghax.GHaxMenuScreen;


public class HackConfigScreen extends Screen {
    private final Hack parentHack;

    public HackConfigScreen(Hack parentHack) {
        super(new LiteralText(parentHack.getName()));
        this.parentHack = parentHack;
    }

    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 40, 200, 20,
                new LiteralText(this.parentHack.isEnabled() ? "Disable" : "Enable"), btn -> {
            this.parentHack.toggle();
            this.clearChildren();
            this.init();
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 138, 200, 20,
                ScreenTexts.DONE, btn -> this.client.setScreen(GHaxMenuScreen.getInstance())));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        HackConfigScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40,
                this.parentHack.isEnabled() ? 0x00FF00 : 0xFF0000);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
