package tk.ggorg.ghax;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Util;
import tk.ggorg.ghax.hack.Hack;
import tk.ggorg.ghax.hack.HackList;

public class GHaxMenuScreen extends Screen {
    private final Screen parent;
    private static GHaxMenuScreen instance;

    private static final String GITHUB_URL = "https://gh.ggorg.tk/ghax";
    private static final String AUTHOR_URL = "https://ggorg.tk/";

    public static GHaxMenuScreen getInstance() {
        return instance;
    }

    public GHaxMenuScreen(Screen parent) {
        super(new LiteralText("GHax menu"));
        this.parent = parent;
        instance = this;
    }

    protected void init() {
        for(int i = 0; i < GHax.hackList.hacks.size(); i++){
            Hack hack = GHax.hackList.hacks.get(i);
            this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 4 + 10 + i*25, 175, 20,
                    new LiteralText(hack.getButtonText()), btn -> {
                hack.toggle();
                this.clearChildren();
                this.init();
            }));

            this.addDrawableChild(new ButtonWidget(this.width / 2 + 80, this.height / 4 + 10 + i*25, 20, 20,
                    new LiteralText(">"), btn -> hack.openConfigScreen()));
        }

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 40, 200, 20,
                ScreenTexts.DONE, btn -> this.client.setScreen(this.parent)));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 205, this.height - 40, 100, 20,
                new LiteralText("GitHub"), btn -> Util.getOperatingSystem().open(GITHUB_URL)));

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 105, this.height - 40, 100, 20,
                new LiteralText("Author"), btn -> Util.getOperatingSystem().open(AUTHOR_URL)));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        GHaxMenuScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 0xFFFFFF);
        GHaxMenuScreen.drawCenteredText(matrices, this.textRenderer, GHaxClient.getModVersion(), this.width / 2, 50, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
