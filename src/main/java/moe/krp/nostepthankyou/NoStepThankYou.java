package moe.krp.nostepthankyou;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NoStepThankYou.MODID)
public class NoStepThankYou
{
    public static final String MODID = "nostepthankyou";
    private static final Logger logger = LogManager.getLogger();

    public static ForgeConfigSpec.DoubleValue SHIFTING_STEP_HEIGHT;
    public NoStepThankYou()
    {
        logger.info("No Step, Thank You initializing");
        registerConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void registerConfig() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

        SHIFTING_STEP_HEIGHT = COMMON_BUILDER
                .comment("Step height when crouching")
                .defineInRange("stepHeight", 0.5, 0, Float.MAX_VALUE);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }
}
