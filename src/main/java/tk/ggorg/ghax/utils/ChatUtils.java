package tk.ggorg.ghax.utils;

import net.minecraft.text.LiteralText;
import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.hack.Hack;

public class ChatUtils {
    public static final String FORMAT = "\u00a7";
    private static final String PREFIX = FORMAT + "9" + "[" + FORMAT + "c" + "GHax" + FORMAT + "9" + "]" + FORMAT + "a" + " ";

    public static void showMessage(String message) {
        GHax.client.inGameHud.getChatHud().addMessage(new LiteralText(PREFIX + message));
    }

    public static void showHackMessage(Hack hack, String message) {
        showMessage(FORMAT + "b" + "(" + FORMAT + "6" + hack.getName() + FORMAT + "b" + ")" + FORMAT + "a" + " " + message);
    }
}
