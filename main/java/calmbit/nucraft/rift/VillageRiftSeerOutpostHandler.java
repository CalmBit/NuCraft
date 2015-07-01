package calmbit.nucraft.rift;

import java.util.List;
import java.util.Random;

import calmbit.nucraft.power.Workshop;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class VillageRiftSeerOutpostHandler implements IVillageCreationHandler {

	@Override
	public PieceWeight getVillagePieceWeight(Random random, int i) {
		return new PieceWeight(RiftSeerOutpost.class, 15, i + random.nextInt(4));
	}

	@Override
	public Class<?> getComponentClass() {
		return RiftSeerOutpost.class;
	}

	@Override
	public Object buildComponent(PieceWeight villagePiece, Start startPiece, List pieces, Random random, int p1, int p2,
			int p3, int p4, int p5) {
		return RiftSeerOutpost.buildComponent(startPiece, pieces, random,p1, p2, p3, p4, p5);
	}

}
