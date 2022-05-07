package tk.ggorg.ghax.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tk.ggorg.ghax.GHaxMenuScreen;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text text) {
        super(text);
    }

    @Inject(at = @At("TAIL"), method = "initWidgets")
    private void initWidgets(CallbackInfo ci) {
        this.addDrawableChild(
                new ButtonWidget(this.width / 2 - 102, this.height / 4 + 144 - 16, 204, 20,
                        new LiteralText("GHax menu"), btn -> this.client.setScreen(GHaxMenuScreen.getInstance())));
    }

    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci) {
        new GHaxMenuScreen(this);
    }
}
