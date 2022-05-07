package tk.ggorg.ghax;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.ggorg.ghax.events.EventList;
import tk.ggorg.ghax.hack.HackList;
import tk.ggorg.ghax.hacks.FlyHack;
import tk.ggorg.ghax.hack.Hack;

public class GHax implements ModInitializer {
    public static final String modId = "ghax";

    public static final Logger LOGGER = LoggerFactory.getLogger(modId);

    public static final EventList eventList = new EventList();

    public static final HackList hackList = new HackList();

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized!");
    }
}
