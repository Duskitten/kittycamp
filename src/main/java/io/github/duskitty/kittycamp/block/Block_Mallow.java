package io.github.duskitty.kittycamp.block;

import io.github.duskitty.kittycamp.init.Block_Init;
import io.github.duskitty.kittycamp.init.Item_Init;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
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

import java.util.Random;

public class Block_Mallow extends Block{
   public static DirectionProperty FACING;

   protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);

   protected Block_Mallow(Settings settings) {
      super(settings);
      setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
   }

   @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

      ItemStack itemStack = player.getStackInHand(hand);
      Item item = itemStack.getItem();

      if (itemStack.isEmpty() && Screen.hasShiftDown()) {
         dropStack(world, pos, new ItemStack(Item_Init.STICK_MALLOW, 1));
         world.removeBlock(pos, false);
         return ActionResult.success(world.isClient);
      } else if (itemStack.isEmpty()){
         dropStack(world, pos, new ItemStack(Item_Init.MARSHMELLOW, 1));
         Direction facing = state.get(FACING);
         world.removeBlock(pos, false);
         world.setBlockState(pos, Block_Init.NOMALLOWSTICK.getDefaultState().with(FACING, facing));
         return ActionResult.success(world.isClient);
      } else {
         return super.onUse(state, world, pos, player, hand, hit);

      }
   }

   public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      Direction facing = state.get(FACING);
      BlockPos nextblock = pos;
      if (facing == Direction.NORTH) {
         nextblock = pos.south();}
      else if(facing == Direction.SOUTH){
         nextblock = pos.north();}
      else if(facing == Direction.EAST){
         nextblock = pos.west();}
      else if(facing == Direction.WEST){
         nextblock = pos.east();}

      if (world.getBlockState(nextblock).getBlock().is(Blocks.CAMPFIRE )||world.getBlockState(nextblock).getBlock().is(Blocks.FIRE ) ||world.getBlockState(nextblock).getBlock().is(Blocks.SOUL_CAMPFIRE )||world.getBlockState(nextblock).getBlock().is(Blocks.SOUL_FIRE ) ){
               world.removeBlock(pos, false);
               world.setBlockState(pos, Block_Init.COOKEDMALLOWSTICK.getDefaultState().with(FACING, facing));
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
