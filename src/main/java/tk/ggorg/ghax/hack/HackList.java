package tk.ggorg.ghax.hack;

import tk.ggorg.ghax.GHax;
import tk.ggorg.ghax.hacks.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HackList {
    public final FlyHack flyHack = new FlyHack();
    public final SprintHack sprintHack = new SprintHack();

    public final List<Hack> hacks = new ArrayList<>();

    public HackList() {
        for(Field f : HackList.class.getDeclaredFields()) {
            if(!f.getName().endsWith("Hack")) continue;

            try {
                Hack hack = (Hack)f.get(this);
                hacks.add(hack);
            }
            catch (IllegalAccessException e) {
                GHax.LOGGER.error("[HackList] Error while building hack list! (On element " + f.getName() + ")");
            }
        }
    }
}
