package net.liongamer.honkaimod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class EdenstarEffectParticles3 extends TextureSheetParticle {
    private final SpriteSet spriteSet;
    protected boolean isGlowing;

    private double speedX = 0.0;
    private double speedY = 0.0;
    private double speedZ = 0.0;

    protected EdenstarEffectParticles3(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.spriteSet = spriteSet;
        this.friction = 0F;
        this.gravity = 0F;
        this.isGlowing = true;
        this.speedX = pXSpeed;
        this.speedY = pYSpeed;
        this.speedZ = pZSpeed;
        this.setSize((float) (Math.random() * 100f), (float) (Math.random() * 100f));
        this.lifetime = (int)(16.0D / ((double)this.random.nextFloat() * 0.8D + 0.2D)) + 50;

        this.setSpriteFromAge(spriteSet);

        this.rCol = 100f;
        this.gCol = 100f;
        this.bCol = 100f;
    }

    @Override
    public void tick() {
        super.tick();
        move(this.xd + this.speedX, this.yd + this.speedY, this.zd + this.speedZ);
        this.setSize((float) (Math.random() * 3.5f), (float) (Math.random() * 3.5f));
        this.setSpriteFromAge(this.spriteSet);
    }


    public int getLightColor(float pPartialTick) {
        return this.isGlowing ? 240 : super.getLightColor(pPartialTick);
    }

    @NotNull
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


/*
    @OnlyIn(Dist.CLIENT)
    public static class EmissiveProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public EmissiveProvider(SpriteSet pSprite) {
            this.sprite = pSprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            EdenstarEffectParticles edenstarEffectParticles = new EdenstarEffectParticles(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, this.sprite);
            edenstarEffectParticles.setAlpha(1.0F);
            edenstarEffectParticles.isGlowing = true;
            return edenstarEffectParticles;
        }
    }
*/
    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {
            EdenstarEffectParticles3 particles = new EdenstarEffectParticles3(pLevel, pX, pY, pZ,this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
            particles.setColor(1F, 1F, 1F);
            return particles;
        }
    }
}
