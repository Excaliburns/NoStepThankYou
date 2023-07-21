package moe.krp.nostepthankyou.mixin;

import moe.krp.nostepthankyou.NoStepThankYou;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(IForgeEntity.class)
public interface IForgeEntityMixin {

    /**
     * Replace stepHeight with default stepHeight if player is crouching.
     * @author Tut
     * @reason To stop players from going up a block if they're crouching.
     */
    @Overwrite(remap = false)
    default float getStepHeight() {
        Entity entity = ((Entity) (Object) this);
        float vanillaStep = entity.maxUpStep;
        if (entity instanceof Player player) {
            float addedAttribs = (float) player.getAttributeValue(ForgeMod.STEP_HEIGHT_ADDITION.get());
            float stepHeight = NoStepThankYou.SHIFTING_STEP_HEIGHT.get().floatValue();

            if (player.isCrouching() && addedAttribs > stepHeight) {
                return stepHeight;
            }

            return addedAttribs;
        }

        if (entity instanceof LivingEntity living)
        {
            AttributeInstance stepHeightAttribute = living.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());
            if (stepHeightAttribute != null)
            {
                return (float) Math.max(0, vanillaStep + stepHeightAttribute.getValue());
            }
        }
        return vanillaStep;
    }
}
