package tk.ggorg.ghax;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class GHaxMenuScreen extends Screen {
    private final Screen parent;
    private final GameOptions settings;


    public GHaxMenuScreen(Screen parent, GameOptions settings) {
        super(new LiteralText("GHax menu"));
        this.parent = parent;
        this.settings = settings;
    }

    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 90, 200, 20, new LiteralText("Button"), btn -> {
           GHax.LOGGER.info("Button clicked!");
        }));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 138, 200, 20, ScreenTexts.BACK, btn -> {
            this.client.setScreen(this.parent);
        }));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        GameMenuScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
