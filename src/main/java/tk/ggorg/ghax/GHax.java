package tk.ggorg.ghax;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GHax implements ModInitializer {
    public static final String modId = "ghax";

    public static final Logger LOGGER = LoggerFactory.getLogger(modId);

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized!");
    }
}
