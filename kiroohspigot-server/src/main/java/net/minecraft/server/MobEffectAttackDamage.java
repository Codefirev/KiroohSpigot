package net.minecraft.server;

import network.secondlife.spigot.SLSpigotConfig;

public class MobEffectAttackDamage extends MobEffectList {

    protected MobEffectAttackDamage(int i, boolean flag, int j) {
        super(i, flag, j);
    }

    public double a(int i, AttributeModifier attributemodifier) {
        // PaperSpigot - Configurable modifiers for strength and weakness effects

        if(this.id == MobEffectList.WEAKNESS.id) return -0.5 * (float) (i + 1);

        // GOMID Start
        if((i + 1) == 1) {
            return 1.3 * SLSpigotConfig.strength_1_nerf;
        } else {
            return 2.6 * SLSpigotConfig.strength_2_nerf;
        }
        // GOMID End
    }
}
