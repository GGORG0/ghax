package tk.ggorg.ghax.events;

import java.lang.reflect.Method;
import java.util.EventListener;
import java.util.function.Function;

public interface TickListener extends EventListener {
    void onTick();
}
