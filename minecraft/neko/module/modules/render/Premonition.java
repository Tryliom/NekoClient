package neko.module.modules.render;

import org.lwjgl.opengl.GL11;

import neko.module.Category;
import neko.module.Module;
import neko.utils.RenderUtils;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class Premonition extends Module {
	private static Premonition instance=null;
	
	public Premonition() {
		super("Premonition", -1, Category.RENDER, false);
		instance=this;
	}
	
	public static Premonition getInstance() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onRender3D() {
        boolean bow = false;
        boolean potion = false;
        if (this.mc.thePlayer.getHeldItem() == null) {
            return;
        }
        if (!(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemBow) && !(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSnowball) && !(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemEnderPearl) && !(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemEgg) && (!(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemPotion) || !ItemPotion.isSplash(this.mc.thePlayer.getHeldItem().getItemDamage()))) {
            return;
        }
        bow = (this.mc.thePlayer.getHeldItem().getItem() instanceof ItemBow);
        potion = (this.mc.thePlayer.getHeldItem().getItem() instanceof ItemPotion);
        final float throwingYaw = this.mc.thePlayer.rotationYaw;
        final float throwingPitch = this.mc.thePlayer.rotationPitch;
        this.mc.getRenderManager();
        double posX = RenderManager.renderPosX - MathHelper.cos(throwingYaw / 180.0f * 3.141593f) * 0.16f;
        this.mc.getRenderManager();
        double posY = RenderManager.renderPosY + this.mc.thePlayer.getEyeHeight() - 0.1000000014901161;
        this.mc.getRenderManager();
        double posZ = RenderManager.renderPosZ - MathHelper.sin(throwingYaw / 180.0f * 3.141593f) * 0.16f;
        double motionX = -MathHelper.sin(throwingYaw / 180.0f * 3.141593f) * MathHelper.cos(throwingPitch / 180.0f * 3.141593f) * (bow ? 1.0 : 0.4);
        double motionY = -MathHelper.sin((throwingPitch - (potion ? 20 : 0)) / 180.0f * 3.141593f) * (bow ? 1.0 : 0.4);
        double motionZ = MathHelper.cos(throwingYaw / 180.0f * 3.141593f) * MathHelper.cos(throwingPitch / 180.0f * 3.141593f) * (bow ? 1.0 : 0.4);
        final int var6 = 72000 - this.mc.thePlayer.getItemInUseCount();
        float power = var6 / 20.0f;
        power = (power * power + power * 2.0f) / 3.0f;
        if (power < 0.1) {
            return;
        }
        if (power > 1.0f) {
            power = 1.0f;
        }
        final float distance = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
        motionX /= distance;
        motionY /= distance;
        motionZ /= distance;
        motionX *= (bow ? (power * 2.0f) : 1.0f) * (potion ? 0.5 : 1.5);
        motionY *= (bow ? (power * 2.0f) : 1.0f) * (potion ? 0.5 : 1.5);
        motionZ *= (bow ? (power * 2.0f) : 1.0f) * (potion ? 0.5 : 1.5);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 200.0f, 0.0f);
        GL11.glDisable(2896);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glPushMatrix();
        GL11.glColor4f(0.5f, 1.0f, 1.0f, 0.5f);
        GL11.glDisable(3553);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glBegin(3);
        boolean hasLanded = false;
        final Entity hitEntity = null;
        MovingObjectPosition landingPosition = null;
        while (!hasLanded && posY > 0.0) {
            final Vec3 present = new Vec3(posX, posY, posZ);
            final Vec3 future = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);
            final MovingObjectPosition possibleLandingStrip = this.mc.theWorld.rayTraceBlocks(present, future, false, true, false);
            if (possibleLandingStrip != null) {
                if (possibleLandingStrip.typeOfHit != MovingObjectPosition.MovingObjectType.MISS) {
                    landingPosition = possibleLandingStrip;
                    hasLanded = true;
                }
            }
            else {
                final Entity entityHit = this.getEntityHit(bow, present, future);
                if (entityHit != null) {
                    landingPosition = new MovingObjectPosition(entityHit);
                    hasLanded = true;
                }
            }
            posX += motionX;
            posY += motionY;
            posZ += motionZ;
            final float motionAdjustment = 0.99f;
            motionX *= motionAdjustment;
            motionY *= motionAdjustment;
            motionZ *= motionAdjustment;
            motionY -= (potion ? 0.05 : (bow ? 0.05 : 0.03));
            final double n = posX;
            this.mc.getRenderManager();
            final double x = n - RenderManager.renderPosX;
            final double n2 = posY;
            this.mc.getRenderManager();
            final double y = n2 - RenderManager.renderPosY;
            final double n3 = posZ;
            this.mc.getRenderManager();
            GL11.glVertex3d(x, y, n3 - RenderManager.renderPosZ);
        }
        GL11.glEnd();
        GL11.glPushMatrix();
        final double n4 = posX;
        this.mc.getRenderManager();
        final double x2 = n4 - RenderManager.renderPosX;
        final double n5 = posY;
        this.mc.getRenderManager();
        final double y2 = n5 - RenderManager.renderPosY;
        final double n6 = posZ;
        this.mc.getRenderManager();
        GL11.glTranslated(x2, y2, n6 - RenderManager.renderPosZ);
        if (landingPosition != null && landingPosition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            final int kek = landingPosition.getfield_178784_b().getIndex();
            if (kek == 1) {
                GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            }
            else if (kek == 2) {
                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            }
            else if (kek == 3) {
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
            }
            else if (kek == 4) {
                GL11.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
            }
            else if (kek == 5) {
                GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
            RenderUtils.drawBorderedRect(-0.4f, -0.4f, 0.4f, 0.4f, 1.0f, -8388609, 1518338047);
        }
        GL11.glPopMatrix();
        if (landingPosition != null && landingPosition.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            this.mc.getRenderManager();
            final double x3 = -RenderManager.renderPosX;
            this.mc.getRenderManager();
            final double y3 = -RenderManager.renderPosY;
            this.mc.getRenderManager();
            GL11.glTranslated(x3, y3, -RenderManager.renderPosZ);
            GL11.glColor4f(0.5f, 1.0f, 1.0f, 0.17f);
            RenderUtils.drawBoundingBox(landingPosition.entityHit.getEntityBoundingBox().expand(0.125, 0.0, 0.125).offset(0.0, 0.1, 0.0));
            GL11.glColor4f(0.5f, 1.0f, 1.0f, 1.0f);
            GL11.glLineWidth(0.5f);
            RenderUtils.drawOutlinedBoundingBox(landingPosition.entityHit.getEntityBoundingBox().expand(0.125, 0.0, 0.125).offset(0.0, 0.1, 0.0));
            this.mc.getRenderManager();
            final double renderPosX = RenderManager.renderPosX;
            this.mc.getRenderManager();
            final double renderPosY = RenderManager.renderPosY;
            this.mc.getRenderManager();
            GL11.glTranslated(renderPosX, renderPosY, RenderManager.renderPosZ);
        }
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glEnable(3553);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    private java.util.ArrayList getEntities() {
        final java.util.ArrayList ret = new java.util.ArrayList();
        for (final Object e : this.mc.theWorld.loadedEntityList) {
            if (e != this.mc.thePlayer && e instanceof EntityLivingBase) {
                ret.add(e);
            }
        }
        return ret;
    }
    
    private Entity getEntityHit(final boolean bow, final Vec3 vecOrig, final Vec3 vecNew) {
        for (final Object o : this.getEntities()) {
            final EntityLivingBase entity = (EntityLivingBase)o;
            if (entity != this.mc.thePlayer) {
                final float expander = 0.2f;
                final AxisAlignedBB bounding2 = entity.getEntityBoundingBox().expand(expander, expander, expander);
                final MovingObjectPosition possibleEntityLanding = bounding2.calculateIntercept(vecOrig, vecNew);
                if (possibleEntityLanding != null) {
                    return entity;
                }
                continue;
            }
        }
        return null;
    }
	
	
	
}
