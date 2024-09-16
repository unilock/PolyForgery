package com.campersamu.polyforgery;

import com.campersamu.polyforgery.compat.polydex.AlloyForgeryRecipeView;
import com.campersamu.polyforgery.poly.ForgeGuiPoly;
import io.github.theepicblock.polymc.api.PolyMcEntrypoint;
import io.github.theepicblock.polymc.api.PolyRegistry;
import io.github.theepicblock.polymc.api.resource.ModdedResources;
import io.github.theepicblock.polymc.api.resource.PolyMcResourcePack;
import io.github.theepicblock.polymc.api.resource.SoundAsset;
import io.github.theepicblock.polymc.api.resource.TextureAsset;
import io.github.theepicblock.polymc.impl.misc.logging.SimpleLogger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wraith.alloyforgery.AlloyForgery;

public class PolyForgery implements PolyMcEntrypoint, ModInitializer {
    public static final String MOD_ID = "polyforgery";
    public static final Logger LOGGER = LoggerFactory.getLogger("PolyForgery");

    @Override
    public void registerModSpecificResources(ModdedResources moddedResources, PolyMcResourcePack pack, SimpleLogger logger) {
        pack.setAsset(MOD_ID, "font/fuel.json", new SoundAsset(() -> moddedResources.getInputStream(MOD_ID, "font/fuel.json")));
        pack.setAsset(MOD_ID, "font/gui.json", new SoundAsset(() -> moddedResources.getInputStream(MOD_ID, "font/gui.json")));
        pack.setAsset(MOD_ID, "font/lava.json", new SoundAsset(() -> moddedResources.getInputStream(MOD_ID, "font/lava.json")));
        pack.setAsset(MOD_ID, "font/smelt.json", new SoundAsset(() -> moddedResources.getInputStream(MOD_ID, "font/smelt.json")));
        pack.setAsset(MOD_ID, "lang/en_us.json", new SoundAsset(() -> moddedResources.getInputStream(MOD_ID, "lang/en_us.json")));
        pack.setAsset(MOD_ID, "textures/gui/forge_controller.png",  new TextureAsset(() -> moddedResources.getInputStream(MOD_ID, "textures/gui/forge_controller.png"), null));
        pack.setAsset(MOD_ID, "textures/gui/fuel.png",  new TextureAsset(() -> moddedResources.getInputStream(MOD_ID, "textures/gui/fuel.png"), null));
        pack.setAsset(MOD_ID, "textures/gui/lava.png",  new TextureAsset(() -> moddedResources.getInputStream(MOD_ID, "textures/gui/lava.png"), null));
        pack.setAsset(MOD_ID, "textures/gui/smelt.png",  new TextureAsset(() -> moddedResources.getInputStream(MOD_ID, "textures/gui/smelt.png"), null));
    }

    @Override
    public void registerPolys(PolyRegistry registry) {
        registry.registerGuiPoly(AlloyForgery.ALLOY_FORGE_SCREEN_HANDLER_TYPE, new ForgeGuiPoly());
    }

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("polydex")){
            LOGGER.info("Adding recipe screen to Polydex...");
            AlloyForgeryRecipeView.init();
        }
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}