package tk.ggorg.ghax.events;

import tk.ggorg.ghax.GHax;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Event<T extends EventListener> {
    private final List<T> listeners = new ArrayList<>();

    public void fire() {
        for(T listener : listeners) {
            Method method = listener.getClass().getDeclaredMethods()[0];
            try {
                method.invoke(listener);
            }
            catch (IllegalAccessException|InvocationTargetException e){
                GHax.LOGGER.error("[Event] Error while firing event! (On element " + listener.getClass().getName() + "." + method.getName() + ")" + "\n" + e.getMessage());
            }
        }
    }

    public void register(T listener) {
        listeners.add(listener);
    }

    public void unregister(T listener) {
        listeners.remove(listener);
    }
}
