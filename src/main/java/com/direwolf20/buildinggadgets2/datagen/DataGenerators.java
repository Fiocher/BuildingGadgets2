package com.direwolf20.buildinggadgets2.datagen;

import com.direwolf20.buildinggadgets2.BuildingGadgets2;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = BuildingGadgets2.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        //if (event.includeServer()) {
        generator.addProvider(event.includeServer(), new BG2Recipes(packOutput));
        generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(BG2LootTables::new, LootContextParamSets.BLOCK))));
        BG2BlockTags blockTags = new BG2BlockTags(packOutput, lookupProvider, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        BG2ItemTags itemTags = new BG2ItemTags(packOutput, lookupProvider, blockTags, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), itemTags);
        //generator.addProvider(new BG2ItemTags(generator, blockTags, event.getExistingFileHelper()));
        //}
        //if (event.includeClient()) {
        generator.addProvider(event.includeClient(), new BG2BlockStates(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new BG2ItemModels(packOutput, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new BG2LanguageProvider(packOutput, "en_us"));
        //}
    }
}
