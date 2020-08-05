package io.github.duskitty.kittycamp.block;

import io.github.duskitty.kittycamp.init.Block_Init;
import io.github.duskitty.kittycamp.init.Item_Init;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class Block_Burned extends Block{
   public static DirectionProperty FACING;

   protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

   protected Block_Burned(Settings settings) {
      super(settings);
      setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
   }

   @Override
   public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      ItemStack itemStack = player.getStackInHand(hand);
      Item item = itemStack.getItem();

      if (itemStack.isEmpty() && Screen.hasShiftDown()) {
         dropStack(world, pos, new ItemStack(Item_Init.STICK_NOMALLOW, 1));
         world.removeBlock(pos, false);
         return ActionResult.success(world.isClient);
      }else if(item.isIn(FabricToolTags.AXES)) {
         Direction facing = state.get(FACING);
         world.removeBlock(pos, false);
         world.setBlockState(pos, Block_Init.NOMALLOWSTICK.getDefaultState().with(FACING, facing));
         return ActionResult.success(world.isClient);
      }else{
         return super.onUse(state, world, pos, player, hand, hit);
      }
   }

   public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return BOUNDING_SHAPE;
   }

   public BlockState getPlacementState(ItemPlacementContext ctx) {
      return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
   }

   @Override
   public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
      return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
   }

   @Override
   public BlockRenderType getRenderType(BlockState state) {
      return BlockRenderType.MODEL;
   }

   @Override
   protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
      stateManager.add(FACING);
   }

   @Override
   public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
      return sideCoversSmallSquare(world, pos.down(), Direction.UP);
   }

   static {
      FACING = HorizontalFacingBlock.FACING;
   }
}
