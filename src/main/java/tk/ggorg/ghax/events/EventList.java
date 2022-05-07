package tk.ggorg.ghax.events;

import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.hack.HackList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EventList {
    public final Event<TickListener> tickEvent = new Event<>();

    public final List<Event> events = new ArrayList<>();

    public EventList() {
        for(Field f : HackList.class.getDeclaredFields()) {
            if(!f.getName().endsWith("Event")) continue;

            try {
                Event event = (Event)f.get(this);
                events.add(event);
            }
            catch (IllegalAccessException e) {
                GHax.LOGGER.error("[EventList] Error while building event list! (On element " + f.getName() + ")");
            }
        }
    }
}
