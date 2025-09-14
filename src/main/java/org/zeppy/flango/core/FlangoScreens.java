package org.zeppy.flango.core;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.zeppy.flango.FizzleBlock.FizzleBlockScreenHandler;

public class FlangoScreens {

    public static final ScreenHandlerType<FizzleBlockScreenHandler> FIZZLE_BLOCK_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER,
            Identifier.of(FlangoMod.MOD_ID, "fizzle_block"),
            new ScreenHandlerType<>(FizzleBlockScreenHandler::new, FeatureSet.empty())
    );
    //new ScreenHandlerType<>(FizzleBlockScreenHandler::new, FeatureSet.empty());

    public static void initialize() {}
}
