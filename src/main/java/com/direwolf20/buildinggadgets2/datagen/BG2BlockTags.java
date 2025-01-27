package com.direwolf20.buildinggadgets2.datagen;

import com.direwolf20.buildinggadgets2.BuildingGadgets2;
import com.direwolf20.buildinggadgets2.setup.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BG2BlockTags extends BlockTagsProvider {

    public BG2BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BuildingGadgets2.MODID, existingFileHelper);
    }

    public static final TagKey<Block> BG2DENY = BlockTags.create(new ResourceLocation(BuildingGadgets2.MODID, "deny"));
    public static final TagKey<Block> NO_MOVE = BlockTags.create(new ResourceLocation("forge", "relocation_not_supported"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BG2DENY)
                .add(Blocks.PISTON_HEAD)
                .add(Blocks.BEDROCK)
                .add(Blocks.END_PORTAL_FRAME)
                .add(Blocks.CANDLE_CAKE)
                .addTag(BlockTags.BEDS)
                .addTag(BlockTags.PORTALS)
                .addTag(BlockTags.DOORS);
        /*tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.LaserNode.get())
                .add(Registration.LaserConnector.get());*/
        tag(NO_MOVE)
                .add(Registration.RenderBlock.get());
    }

    @Override
    public String getName() {
        return "BuildingGadgets2 Tags";
    }
}
