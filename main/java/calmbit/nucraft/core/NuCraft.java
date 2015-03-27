package calmbit.nucraft.core;

import javax.tools.Tool;

import calmbit.nucraft.power.BlockCarbonGenerator;
import calmbit.nucraft.power.BlockCompressor;
import calmbit.nucraft.power.BlockCopperIngot;
import calmbit.nucraft.power.BlockCopperOre;
import calmbit.nucraft.power.BlockFluidSolidifier;
import calmbit.nucraft.power.BlockHeavyCrudeOil;
import calmbit.nucraft.power.BlockLeadOre;
import calmbit.nucraft.power.BlockMachineCasing;
import calmbit.nucraft.power.BlockNitro;
import calmbit.nucraft.power.BlockSteelIngot;
import calmbit.nucraft.power.CommonProxy;
import calmbit.nucraft.power.ElectricianTradeHandler;
import calmbit.nucraft.power.EntityNitroPrimed;
import calmbit.nucraft.power.GuiHandlerCarbonGenerator;
import calmbit.nucraft.power.GuiHandlerCompressor;
import calmbit.nucraft.power.GuiHandlerFluidSolidifier;
import calmbit.nucraft.power.ItemBatteryBase;
import calmbit.nucraft.power.ItemCarbonWafer;
import calmbit.nucraft.power.ItemCopperCoil;
import calmbit.nucraft.power.ItemCopperIngot;
import calmbit.nucraft.power.ItemHeavyCrudeOilBucket;
import calmbit.nucraft.power.ItemLeadIngot;
import calmbit.nucraft.power.ItemRubberBall;
import calmbit.nucraft.power.ItemSiliconFragment;
import calmbit.nucraft.power.ItemSiliconWafer;
import calmbit.nucraft.power.ItemSteelIngot;
import calmbit.nucraft.power.NitroDispenseBehavior;
import calmbit.nucraft.power.TileEntityCarbonGenerator;
import calmbit.nucraft.power.TileEntityCompressor;
import calmbit.nucraft.power.TileEntityFluidSolidifier;
import calmbit.nucraft.power.VillageWorkshopHandler;
import calmbit.nucraft.power.Workshop;
import calmbit.nucraft.rift.BiomeGenRift;
import calmbit.nucraft.rift.BlockAmethiteOre;
import calmbit.nucraft.rift.BlockLeptrusOre;
import calmbit.nucraft.rift.BlockRiftBomb;
import calmbit.nucraft.rift.BlockRiftCobblestone;
import calmbit.nucraft.rift.BlockRiftDirt;
import calmbit.nucraft.rift.BlockRiftEssence;
import calmbit.nucraft.rift.BlockRiftGrass;
import calmbit.nucraft.rift.BlockRiftPlasma;
import calmbit.nucraft.rift.BlockRiftStone;
import calmbit.nucraft.rift.EntityMaercs;
import calmbit.nucraft.rift.EntityRiftBombPrimed;
import calmbit.nucraft.rift.EntityRiftCrawler;
import calmbit.nucraft.rift.EntityRiftDaemon;
import calmbit.nucraft.rift.ItemAmethite;
import calmbit.nucraft.rift.ItemAmethiteAxe;
import calmbit.nucraft.rift.ItemAmethiteHoe;
import calmbit.nucraft.rift.ItemAmethitePickaxe;
import calmbit.nucraft.rift.ItemAmethiteShovel;
import calmbit.nucraft.rift.ItemAmethiteSword;
import calmbit.nucraft.rift.ItemCrawlerEye;
import calmbit.nucraft.rift.ItemLeptrusIngot;
import calmbit.nucraft.rift.ItemRiftEssenceBucket;
import calmbit.nucraft.rift.ItemRiftMatter;
import calmbit.nucraft.rift.ItemRiftPlasmaBucket;
import calmbit.nucraft.rift.TileEntityRiftPortal;
import calmbit.nucraft.rift.WorldProviderRift;
import calmbit.nucraft.world.BlockAcid;
import calmbit.nucraft.world.BlockBarleyBale;
import calmbit.nucraft.world.BlockRubberTreeDoor;
import calmbit.nucraft.world.BlockRubberTreeLeaves;
import calmbit.nucraft.world.BlockRubberTreeLog;
import calmbit.nucraft.world.BlockRubberTreePlanks;
import calmbit.nucraft.world.BlockRubberTreeSapling;
import calmbit.nucraft.world.BlockRubberTreeStairs;
import calmbit.nucraft.world.ItemAcidBucket;
import calmbit.nucraft.world.ItemLatexDroplet;
import calmbit.nucraft.world.ItemRubberTreeDoor;
import calmbit.nucraft.world.NuCraftPlot;
import calmbit.nucraft.world.BlockBamboo;
import calmbit.nucraft.world.BlockBarleyCrop;
import calmbit.nucraft.world.BlockCherryDoor;
import calmbit.nucraft.world.BlockCherryLeaves;
import calmbit.nucraft.world.BlockCherryLog;
import calmbit.nucraft.world.BlockCherryPlanks;
import calmbit.nucraft.world.BlockCherrySapling;
import calmbit.nucraft.world.BlockCherryStairs;
import calmbit.nucraft.world.BlockElmDoor;
import calmbit.nucraft.world.BlockElmLeaves;
import calmbit.nucraft.world.BlockElmLog;
import calmbit.nucraft.world.BlockElmPlanks;
import calmbit.nucraft.world.BlockElmSapling;
import calmbit.nucraft.world.BlockElmStairs;
import calmbit.nucraft.world.BlockHellTreeDoor;
import calmbit.nucraft.world.BlockHellTreeLeaves;
import calmbit.nucraft.world.BlockHellTreeLog;
import calmbit.nucraft.world.BlockHellTreePlanks;
import calmbit.nucraft.world.BlockHellTreeSapling;
import calmbit.nucraft.world.BlockHellTreeStairs;
import calmbit.nucraft.world.BlockHopsCrop;
import calmbit.nucraft.world.BlockFermentationTank;
import calmbit.nucraft.world.GuiHandlerFermentationTank;
import calmbit.nucraft.world.ItemBamboo;
import calmbit.nucraft.world.ItemBarley;
import calmbit.nucraft.world.ItemBarleySeeds;
import calmbit.nucraft.world.ItemBottleAlcohol;
import calmbit.nucraft.world.ItemCherryDoor;
import calmbit.nucraft.world.ItemElmDoor;
import calmbit.nucraft.world.ItemHellFruit;
import calmbit.nucraft.world.ItemHellTreeDoor;
import calmbit.nucraft.world.ItemHops;
import calmbit.nucraft.world.ItemHopsSeeds;
import calmbit.nucraft.world.ItemYeast;
import calmbit.nucraft.world.PotionAlcohol;
import calmbit.nucraft.world.TileEntityFermentationTank;
import calmbit.nucraft.world.VillageNuCraftPlotHandler;
import calmbit.nucraft.world.WorldGenElmTrees;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.potion.Potion;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerRegisterEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(modid = "NuCraft", name = "Nu-Craft", version="0.0.1")
public class NuCraft {

	@Instance(value = "NuCraft")
	public static NuCraft instance;
	
	public static Configuration config;
	public final static Block copperOre = new BlockCopperOre();
	public final static Block carbonGenerator = new BlockCarbonGenerator(false);
	public final static Block active_carbonGenerator = new BlockCarbonGenerator(true);
	public final static Block compressor = new BlockCompressor(false);
	public final static Block active_compressor = new BlockCompressor(true);
	public final static Block copperBlock = new BlockCopperIngot();
	public final static Block logElm = new BlockElmLog();
	public final static Block leavesElm = new BlockElmLeaves();
	public final static Block planksElm = new BlockElmPlanks();
	public final static Block saplingElm = new BlockElmSapling();
	public final static Block doorElm = new BlockElmDoor();
	public final static Block logHellTree = new BlockHellTreeLog();
	public final static Block leavesHellTree = new BlockHellTreeLeaves();
	public final static Block planksHellTree = new BlockHellTreePlanks();
	public final static Block doorHellTree = new BlockHellTreeDoor();
	public final static Block saplingHellTree = new BlockHellTreeSapling();
	public final static Item doorHellTreeItem = new ItemHellTreeDoor();
	public final static Item copperIngot = new ItemCopperIngot();
	public final static Item doorElmItem = new ItemElmDoor();
	public final static Block hopsCrop = new BlockHopsCrop();
	public final static Item hopsCropSeeds = new ItemHopsSeeds();
	public final static Item hopsCropItem = new ItemHops();
	public final static Item carbonWafer = new ItemCarbonWafer();
	public final static Item steelIngot = new ItemSteelIngot();
	public final static Block steelBlock = new BlockSteelIngot();
	public final static Item hellFruit = new ItemHellFruit();
	public final static Item siliconFragment = new ItemSiliconFragment();
	public final static Item siliconWafer = new ItemSiliconWafer();
	public final static Block machineCasing = new BlockMachineCasing();
	public final static Item copperCoil = new ItemCopperCoil();
	public final static Block fermentationTank = new BlockFermentationTank();
	public final static Block blockNitro = new BlockNitro();
	public final static Block logCherry = new BlockCherryLog();
	public final static Block leavesCherry = new BlockCherryLeaves();
	public final static Block planksCherry = new BlockCherryPlanks();
	public final static Block saplingCherry = new BlockCherrySapling();
	public final static Block doorCherry = new BlockCherryDoor();
	public final static Item doorCherryItem = new ItemCherryDoor();
	public final static Block blockBamboo = new BlockBamboo();
	public final static Block riftDirt = new BlockRiftDirt();
	public final static Block riftGrass = new BlockRiftGrass();
	public final static Item riftMatter = new ItemRiftMatter();
	public final static Item yeastDust = new ItemYeast();
	public final static Item bottleAlcohol = new ItemBottleAlcohol();
	public final static BiomeGenBase riftBiome = new BiomeGenRift(40);
	public final static Potion basicAlcohol = new PotionAlcohol(0);
	public final static Block riftStone = new BlockRiftStone();
	public final static Block riftCobblestone = new BlockRiftCobblestone();
	public final static Item amethite = new ItemAmethite();
	public final static Block amethiteOre = new BlockAmethiteOre();
	public final static ToolMaterial amethiteMaterial = EnumHelper.addToolMaterial("AMETHITE", 3, 800, 14.0f, 3.0f, 16);
	public final static Item amethiteSword = new ItemAmethiteSword();
	public final static Item amethitePickaxe = new ItemAmethitePickaxe();
	public final static Item amethiteShovel = new ItemAmethiteShovel();
	public final static Item amethiteHoe = new ItemAmethiteHoe();
	public final static Item amethiteAxe = new ItemAmethiteAxe();
	public final static Block barleyCrop = new BlockBarleyCrop();
	public final static Item barleyCropItem  = new ItemBarley();
	public final static Item barleyCropSeeds = new ItemBarleySeeds();
	public final static Block barleyBale = new BlockBarleyBale();
	public final static Block leptrusOre = new BlockLeptrusOre();
	public final static Item leptrusIngot = new ItemLeptrusIngot();
	public final static Item crawlerEye = new ItemCrawlerEye();
	public final static Block blockRiftBomb = new BlockRiftBomb();
	public final static Item doubleABatteryAlkaline = new ItemBatteryBase(2700, ItemBatteryBase.BatteryComposition.ALKALINE);
	public final static Item tripleABatteryAlkaline = new ItemBatteryBase(1200, ItemBatteryBase.BatteryComposition.ALKALINE);
	public final static Item cBatteryAlkaline = new ItemBatteryBase(8000, ItemBatteryBase.BatteryComposition.ALKALINE);
	public final static Item dBatteryAlkaline = new ItemBatteryBase(12000, ItemBatteryBase.BatteryComposition.ALKALINE);
	public final static Block leadOre = new BlockLeadOre();
	public final static Item leadIngot = new ItemLeadIngot();
	public final static Block logRubberTree = new BlockRubberTreeLog();
	public final static Block leavesRubberTree = new BlockRubberTreeLeaves();
	public final static Block planksRubberTree = new BlockRubberTreePlanks();
	public final static Block saplingRubberTree = new BlockRubberTreeSapling();
	public final static Block doorRubberTree = new BlockRubberTreeDoor();
	public final static Item doorRubberTreeItem = new ItemRubberTreeDoor();
	public final static Item latexDroplet = new ItemLatexDroplet();
	public final static Item rubberBall = new ItemRubberBall();
	public final static Block fluidSolidifier = new BlockFluidSolidifier();
	
	public static Item bambooItem;
	public static Item heavyCrudeOilBucket;
	public static Item acidBucket;
	public static Item riftPlasmaBucket;
	public static Item riftEssenceBucket;
	
	public static Block stairsElm;
	public static Block stairsHellTree;
	public static Block stairsCherry;
	public static Block stairsRubberTree;
	public static Block heavyCrudeOilBlock;
	public static Block acidBlock;
	public static Block riftPlasmaBlock;
	public static Block riftEssenceBlock;
	//Random attempted conversion.
	public final static double magicViscoConstant = 32.2580645;

	
	@SidedProxy(clientSide = "calmbit.nucraft.client.ClientProxy", serverSide = "calmbit.nucraft.power.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		System.err.println(event.getSuggestedConfigurationFile().getAbsolutePath());
		config.load();
		config.get(NuCraft.config.CATEGORY_GENERAL, "discoCarpets", false).getBoolean(false);
		
		Fluid heavyCrudeOil = new Fluid("heavyCrudeOil").setDensity(920).setTemperature(289).setViscosity(4565).setUnlocalizedName("heavyCrudeOil");
		FluidRegistry.registerFluid(heavyCrudeOil);
		heavyCrudeOilBlock = new BlockHeavyCrudeOil(heavyCrudeOil);
		GameRegistry.registerBlock(heavyCrudeOilBlock , "heavyCrudeOil");
		heavyCrudeOilBlock.setCreativeTab(tabNuCraftPower);
		if(heavyCrudeOil.getBlock() == null) heavyCrudeOil.setBlock(heavyCrudeOilBlock);
		Blocks.fire.setFireInfo(heavyCrudeOilBlock, 60, 100);
		
		heavyCrudeOilBucket = new ItemHeavyCrudeOilBucket(heavyCrudeOilBlock);
		heavyCrudeOilBucket.setCreativeTab(tabNuCraftPower);
		
		Fluid acid = new Fluid("acid").setDensity(1840).setTemperature(600).setViscosity(854).setUnlocalizedName("acid");
		FluidRegistry.registerFluid(acid);
		acidBlock = new BlockAcid(acid);
		GameRegistry.registerBlock(acidBlock, "acid");
		acidBlock.setCreativeTab(tabNuCraftWorld);
		if(acid.getBlock() == null) acid.setBlock(acidBlock);
		
		acidBucket = new ItemAcidBucket(acidBlock);
		acidBucket.setCreativeTab(tabNuCraftWorld);
		
		Fluid riftPlasma = new Fluid("riftPlasma").setDensity(4000).setTemperature(10000).setViscosity(2000).setLuminosity(15).setUnlocalizedName("riftPlasma");
		FluidRegistry.registerFluid(riftPlasma);
		riftPlasmaBlock = new BlockRiftPlasma(riftPlasma);
		GameRegistry.registerBlock(riftPlasmaBlock, "riftPlasma");
		riftPlasmaBlock.setCreativeTab(tabNuCraftRift);
		if(riftPlasma.getBlock() == null) riftPlasma.setBlock(riftPlasmaBlock);
		
		riftPlasmaBucket = new ItemRiftPlasmaBucket(riftPlasmaBlock);
		riftPlasmaBucket.setCreativeTab(tabNuCraftRift);
		
		Fluid riftEssence = new Fluid("riftEssence").setDensity(10000).setTemperature(0).setViscosity(1).setLuminosity(5).setUnlocalizedName("riftEssence");
		FluidRegistry.registerFluid(riftEssence);
		riftEssenceBlock = new BlockRiftEssence(riftEssence);
		GameRegistry.registerBlock(riftEssenceBlock, "riftEssenceBlock");
		riftEssenceBlock.setCreativeTab(tabNuCraftRift);
		if(riftEssence.getBlock() == null) riftEssence.setBlock(riftEssenceBlock);
		
		riftEssenceBucket = new ItemRiftEssenceBucket(riftEssenceBlock);
		riftEssenceBucket.setCreativeTab(tabNuCraftRift);
		
		NuCraftEventHandler.INSTANCE.buckets.put(heavyCrudeOilBlock, heavyCrudeOilBucket);
		NuCraftEventHandler.INSTANCE.buckets.put(acidBlock, acidBucket);
		NuCraftEventHandler.INSTANCE.buckets.put(riftPlasmaBlock, riftPlasmaBucket);
		NuCraftEventHandler.INSTANCE.buckets.put(riftEssenceBlock, riftEssenceBucket);
		MinecraftForge.EVENT_BUS.register(NuCraftEventHandler.INSTANCE);
		
		GameRegistry.registerItem(heavyCrudeOilBucket, "heavyCrudeOilBucket");
		FluidContainerRegistry.registerFluidContainer(heavyCrudeOil, new ItemStack(heavyCrudeOilBucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(acidBucket, "acidBucket");
		FluidContainerRegistry.registerFluidContainer(acid, new ItemStack(acidBucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(riftPlasmaBucket, "riftPlasmaBucket");
		FluidContainerRegistry.registerFluidContainer(riftPlasma, new ItemStack(riftPlasmaBucket), new ItemStack(Items.bucket));
		GameRegistry.registerItem(riftEssenceBucket, "riftEssenceBucket");
		FluidContainerRegistry.registerFluidContainer(riftEssence, new ItemStack(riftEssenceBucket), new ItemStack(Items.bucket));
		
		GameRegistry.registerItem(copperIngot, "copperIngot");
		copperIngot.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(copperOre, "copperOre");
		copperOre.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(carbonGenerator, "carbonGenerator");
		carbonGenerator.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(active_carbonGenerator, "active_carbonGenerator");
		GameRegistry.registerBlock(compressor, "compressor");
		compressor.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(active_compressor, "active_compressor");
		GameRegistry.registerBlock(copperBlock, "copperBlock");
		copperBlock.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(logElm, "logElm");
		logElm.setCreativeTab(tabNuCraftWorld);
		Blocks.fire.setFireInfo(logElm, 5, 20);
		GameRegistry.registerBlock(leavesElm, "leavesElm");
		leavesElm.setCreativeTab(tabNuCraftWorld);
		Blocks.fire.setFireInfo(leavesElm, 30, 60);
		GameRegistry.registerBlock(planksElm, "planksElm");
		planksElm.setCreativeTab(tabNuCraftWorld);
		Blocks.fire.setFireInfo(planksElm, 5, 20);
		stairsElm = new BlockElmStairs(planksElm, 0);
		GameRegistry.registerBlock(stairsElm, "stairsElm");
		stairsElm.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(saplingElm, "saplingElm");
		saplingElm.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(doorElm, "doorElm");
		GameRegistry.registerItem(doorElmItem, "doorElmItem");
		doorElmItem.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(logHellTree, "logHellTree");
		logHellTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(leavesHellTree, "leavesHellTree");
		leavesHellTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(planksHellTree, "planksHellTree");
		planksHellTree.setCreativeTab(tabNuCraftWorld);
		stairsHellTree = new BlockHellTreeStairs(planksHellTree, 0);
		GameRegistry.registerBlock(stairsHellTree, "stairsHellTree");
		stairsHellTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(doorHellTree, "doorHellTree");
		GameRegistry.registerItem(doorHellTreeItem, "doorHellTreeItem");
		doorHellTreeItem.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(saplingHellTree, "saplingHellTree");
		saplingHellTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(hopsCrop, "hopsCrop");
		GameRegistry.registerItem(hopsCropSeeds, "hopsCropSeeds");
		hopsCropSeeds.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerItem(hopsCropItem, "hopsCropItem");
		hopsCropItem.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerItem(carbonWafer, "carbonWafer");
		carbonWafer.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(steelIngot, "steelIngot");
		steelIngot.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(steelBlock, "steelBlock");
		steelBlock.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(hellFruit, "hellFruit");
		hellFruit.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerItem(siliconFragment, "siliconFragment");
		siliconFragment.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(siliconWafer, "siliconWafer");
		siliconWafer.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(machineCasing, "machineCasing");
		machineCasing.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(copperCoil, "copperCoil");
		copperCoil.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(fermentationTank, "fermentationTank");
		fermentationTank.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(blockNitro, "blockNitro");
		blockNitro.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(logCherry, "logCherry");
		logCherry.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(leavesCherry, "leavesCherry");
		leavesCherry.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(planksCherry, "planksCherry");
		planksCherry.setCreativeTab(tabNuCraftWorld);
		stairsCherry = new BlockCherryStairs(planksCherry, 0);
		GameRegistry.registerBlock(stairsCherry, "stairsCherry");
		stairsCherry.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(saplingCherry, "saplingCherry");
		saplingCherry.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(doorCherry, "doorCherry");
		GameRegistry.registerItem(doorCherryItem, "doorCherryItem");
		doorCherryItem.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(blockBamboo, "blockBamboo");
		bambooItem = new ItemBamboo(blockBamboo);
		GameRegistry.registerItem(bambooItem, "bambooItem");
		bambooItem.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(riftDirt, "riftDirt");
		riftDirt.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerBlock(riftGrass, "riftGrass");
		riftGrass.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(riftMatter, "riftMatter");
		riftMatter.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(yeastDust, "yeastDust");
		yeastDust.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerItem(barleyCropItem, "barleyCropItem");
		barleyCropItem.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerItem(bottleAlcohol, "bottleAlcohol");
		bottleAlcohol.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(riftStone, "riftStone");
		riftStone.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerBlock(riftCobblestone, "riftCobblestone");
		riftCobblestone.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(amethite, "amethite");
		amethite.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerBlock(amethiteOre, "amethiteOre");
		amethiteOre.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(amethiteSword, "amethiteSword");
		amethiteSword.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(amethitePickaxe, "amethitePickaxe");
		amethitePickaxe.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(amethiteShovel, "amethiteShovel");
		amethiteShovel.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(amethiteHoe, "amethiteHoe");
		amethiteHoe.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(amethiteAxe, "amethiteAxe");
		amethiteAxe.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerBlock(barleyCrop, "barleyCrop");
		GameRegistry.registerItem(barleyCropSeeds, "barleyCropSeeds");
		barleyCropSeeds.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(barleyBale, "barleyBale");
		barleyBale.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(leptrusOre, "leptrusOre");
		leptrusOre.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(leptrusIngot, "leptrusIngot");
		leptrusIngot.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(crawlerEye, "crawlerEye");
		crawlerEye.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerBlock(blockRiftBomb, "blockRiftBomb");
		blockRiftBomb.setCreativeTab(tabNuCraftRift);
		GameRegistry.registerItem(doubleABatteryAlkaline, "doubleABatteryAlkaline");
		doubleABatteryAlkaline.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(tripleABatteryAlkaline, "tripleABatteryAlkaline");
		tripleABatteryAlkaline.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(cBatteryAlkaline, "cBatteryAlkaline");
		cBatteryAlkaline.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(dBatteryAlkaline, "dBatteryAlkaline");
		dBatteryAlkaline.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(leadOre, "leadOre");
		leadOre.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(leadIngot, "leadIngot");
		leadIngot.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerItem(latexDroplet, "latexDroplet");
		latexDroplet.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(logRubberTree, "logRubberTree");
		logRubberTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(leavesRubberTree, "leavesRubberTree");
		leavesRubberTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(planksRubberTree, "planksRubberTree");
		planksRubberTree.setCreativeTab(tabNuCraftWorld);
		stairsRubberTree = new BlockRubberTreeStairs(planksRubberTree, 0);
		GameRegistry.registerBlock(stairsRubberTree, "stairsRubberTree");
		stairsRubberTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(saplingRubberTree, "saplingRubberTree");
		saplingRubberTree.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerBlock(doorRubberTree, "doorRubberTree");
		GameRegistry.registerItem(doorRubberTreeItem, "doorRubberTreeItem");
		doorRubberTreeItem.setCreativeTab(tabNuCraftWorld);
		GameRegistry.registerItem(rubberBall, "rubberBall");
		rubberBall.setCreativeTab(tabNuCraftPower);
		GameRegistry.registerBlock(fluidSolidifier, "fluidSolidifier");
		fluidSolidifier.setCreativeTab(tabNuCraftPower);
		
		
		EntityRegistry.registerModEntity(EntityNitroPrimed.class, "NitroPrimed", 0, this, 160, 1, true);
		EntityRegistry.registerModEntity(EntityRiftBombPrimed.class, "RiftBombPrimed", 4, this, 160, 1, true);
		EntityRegistry.registerModEntity(EntityMaercs.class, "Maercs", 1, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityRiftCrawler.class, "RiftCrawler", 2, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityRiftDaemon.class, "RiftDaemon", 3, this, 40, 1, true);
		EntityRegistry.addSpawn(EntityMaercs.class, 50, 1, 6, EnumCreatureType.monster, new BiomeGenBase[]{BiomeGenBase.forest, BiomeGenBase.beach, BiomeGenBase.plains, BiomeGenBase.extremeHills});
		
		OreDictionary.registerOre("ingotCopper", copperIngot);
		OreDictionary.registerOre("ingotSteel", steelIngot);
		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("plankWood", planksElm);
		OreDictionary.registerOre("plankWood", planksHellTree);
		OreDictionary.registerOre("plankWood", planksCherry);
		OreDictionary.registerOre("treeWood", logElm);
		OreDictionary.registerOre("treeWood", logHellTree);
		OreDictionary.registerOre("treeWood", logCherry);
		OreDictionary.registerOre("nuggetSilicon", siliconFragment);
		OreDictionary.registerOre("ingotSilicon", siliconWafer);
		OreDictionary.registerOre("oreAmethite", amethiteOre);
		OreDictionary.registerOre("ingotAmethite", amethite);
		OreDictionary.registerOre("oreLeptrus", leptrusOre);
		OreDictionary.registerOre("ingotLeptrus", leptrusIngot);
		OreDictionary.registerOre("oreLead", leadOre);
		OreDictionary.registerOre("ingotLead", leadIngot);
		
		GameRegistry.addSmelting(copperOre, new ItemStack(copperIngot),(float)0.7);
		GameRegistry.addSmelting(leptrusOre, new ItemStack(leptrusIngot), (float)1.0);
		GameRegistry.addSmelting(riftCobblestone, new ItemStack(riftStone), (float)0.1);
		GameRegistry.addSmelting(leadOre, new ItemStack(leadIngot), (float)0.8);
		GameRegistry.addRecipe(new ItemStack(copperBlock), new Object[]{"CCC", "CCC", "CCC", 'C', NuCraft.copperIngot});
		GameRegistry.addRecipe(new ItemStack(doorElmItem), new Object[]{"WW.", "WW.", "WW.", 'W', NuCraft.planksElm});
		GameRegistry.addRecipe(new ItemStack(doorElmItem), new Object[]{".WW", ".WW", ".WW", 'W', NuCraft.planksElm});
		GameRegistry.addRecipe(new ItemStack(doorHellTreeItem), new Object[]{"WW.", "WW.", "WW.", 'W', NuCraft.planksHellTree});
		GameRegistry.addRecipe(new ItemStack(doorHellTreeItem), new Object[]{".WW", ".WW", ".WW", 'W', NuCraft.planksHellTree});
		GameRegistry.addRecipe(new ItemStack(doorRubberTreeItem), new Object[]{"WW.", "WW.", "WW.", 'W', NuCraft.planksRubberTree});
		GameRegistry.addRecipe(new ItemStack(doorRubberTreeItem), new Object[]{".WW", ".WW", ".WW", 'W', NuCraft.planksRubberTree});
		GameRegistry.addRecipe(new ItemStack(doorCherryItem), new Object[]{"CC.", "CC.", "CC.", 'C', NuCraft.planksCherry});
		GameRegistry.addRecipe(new ItemStack(doorCherryItem), new Object[]{".CC", ".CC", ".CC", 'C', NuCraft.planksCherry});
		GameRegistry.addRecipe(new ItemStack(steelIngot, 3), new Object[]{"CCC", "III", "...", 'C', NuCraft.carbonWafer, 'I', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(steelIngot, 3), new Object[]{ "...", "CCC", "III",'C', NuCraft.carbonWafer, 'I', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(steelBlock), new Object[]{"SSS", "SSS", "SSS", 'S', NuCraft.steelIngot});
		GameRegistry.addRecipe(new ItemStack(siliconWafer), new Object[]{"SSS","SSS","SSS", 'S', NuCraft.siliconFragment});
		GameRegistry.addRecipe(new ItemStack(compressor), new Object[]{"RPR","ICI","GGG", 'R', Items.redstone, 'P', Blocks.piston, 'I', Items.iron_ingot, 'C', NuCraft.machineCasing, 'G', Blocks.glass});
		GameRegistry.addRecipe(new ItemStack(machineCasing), new Object[]{"III","I.I", "III", 'I', Items.iron_ingot});
		GameRegistry.addRecipe(new ItemStack(copperCoil), new Object[]{".C.", "C.C", ".C.", 'C', NuCraft.copperIngot});
		GameRegistry.addRecipe(new ItemStack(carbonGenerator), new Object[]{"RIR","IFI","ICI",'I',Items.iron_ingot,'F', NuCraft.machineCasing, 'R', Items.redstone, 'C', NuCraft.copperCoil});
		GameRegistry.addRecipe(new ItemStack(barleyBale), new Object[]{"BBB","BBB","BBB", 'B', NuCraft.barleyCropItem});
		GameRegistry.addRecipe(new ItemStack(blockNitro, 2), new Object[]{"TTT","TTT","TTT", 'T', Blocks.tnt});
		GameRegistry.addRecipe(new ItemStack(blockRiftBomb), new Object[]{"RRR","RNR","RRR", 'R', NuCraft.riftMatter, 'N', NuCraft.blockNitro});
		
		GameRegistry.addSmelting(logElm, new ItemStack(Items.coal,1,1), (float) 0.15);
		GameRegistry.addSmelting(logHellTree, new ItemStack(Items.coal,1,1), (float) 0.15);
		GameRegistry.addSmelting(logCherry, new ItemStack(Items.coal,1,1), (float)0.15);
		GameRegistry.addShapelessRecipe(new ItemStack(planksElm, 4), new ItemStack(logElm));
		GameRegistry.addShapelessRecipe(new ItemStack(planksHellTree, 4), new ItemStack(logHellTree));
		GameRegistry.addShapelessRecipe(new ItemStack(planksCherry, 4), new ItemStack(logCherry));
		GameRegistry.addShapelessRecipe(new ItemStack(planksRubberTree, 4), new ItemStack(logRubberTree));
		GameRegistry.registerTileEntity(TileEntityCarbonGenerator.class, "carbonGenerator");
		GameRegistry.registerTileEntity(TileEntityCompressor.class, "compressor");
		GameRegistry.registerTileEntity(TileEntityFermentationTank.class, "fermentationTank");
		GameRegistry.registerTileEntity(TileEntityFluidSolidifier.class, "fluidSolidifier");
		
		DimensionManager.registerProviderType(2,WorldProviderRift.class, true);
		DimensionManager.registerDimension(2, 2);
		
		VillagerRegistry.instance().registerVillagerId(1000);
		VillagerRegistry.instance().registerVillagerSkin(1000, new ResourceLocation("nucraftpower","textures/entity/villager/electrician.png"));
		ElectricianTradeHandler eth = new ElectricianTradeHandler();
		VillagerRegistry.instance().registerVillageTradeHandler(1000, eth);
		try
		{
			MapGenStructureIO.func_143031_a(Workshop.class, "Workshop");
			MapGenStructureIO.func_143031_a(NuCraftPlot.class, "BarleyPlot");
		}
		catch(Exception e)
		{
			System.err.println("Failed to add piece!");
		}
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageWorkshopHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageNuCraftPlotHandler());
      	NetworkRegistry.INSTANCE.registerGuiHandler(NuCraft.instance, GuiHandlerRegistry.getInstance());
    	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerCarbonGenerator(), GuiHandlerCarbonGenerator.getGuiId());
    	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerCompressor(), GuiHandlerCompressor.getGuiId());
    	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerFermentationTank(), GuiHandlerFermentationTank.getGuiId());
    	GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerFluidSolidifier(), GuiHandlerFluidSolidifier.getGuiId());
    	GameRegistry.registerWorldGenerator(new NuCraftWorldGenerator(), 5);
    	GameRegistry.registerFuelHandler(new NuCraftFuelHandler());
    	BlockDispenser.dispenseBehaviorRegistry.putObject(Item.getItemFromBlock(blockNitro), new NitroDispenseBehavior());
		config.save();
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public static CreativeTabs tabNuCraftPower = new CreativeTabs("tabNuCraftPower") 
	{
		
		@Override
		public Item getTabIconItem() {
			return copperIngot;
		}
		
	};

	public static CreativeTabs tabNuCraftWorld = new CreativeTabs("tabNuCraftWorld")
	{

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(Blocks.sapling);
		}
		
	};
	
	public static CreativeTabs tabNuCraftRift = new CreativeTabs("tabNuCraftRift")
	{

		@Override
		public Item getTabIconItem() {
			return amethite;
		}
		
	};
}