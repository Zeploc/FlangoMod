package org.zeppy.flango.core;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlangoMod implements ModInitializer {
    public static final String MOD_ID = "flango";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        FlangoItems.initialize();
        FlangoBlocks.initialize();
        FlangoScreens.initialize();
        LOGGER.info("Hello Fabric world!");
    }
}
