package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.TpUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.BlockPos;

public class TpBack extends Module {
	private int k;
	private int vie=10;
	private int delay=0;
	private boolean classic=false;
	private boolean top=false;
	private BlockPos spawn = new BlockPos(0, 0, 0);
	private TpUtils tp = new TpUtils();	
	private static TpBack instance;
	
	public TpBack() {
		super("TpBack", -1, Category.Special);
		instance=this;
	}
	
	public static TpBack getInstance() {
		return instance;
	}
    
	public void onEnabled() {
		if (u.display)
		u.addChat("�a�oTpBack activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oTpBack d�sactiv� !");
		super.onDisabled();
	}
	
    public void onUpdate() {
       
    	if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
        
        if (spawn!=null && mc.thePlayer.getDistance(spawn.getX(), spawn.getY(), spawn.getZ())>4 && mc.thePlayer.getHealth()<vie && delay>10) {         	                                       	
        	int a=spawn.getX();
    		int b=spawn.getY();
    		int c=spawn.getZ();
        	if ((mc.theWorld.getBlockState(new BlockPos(a, b+1, c)).getBlock().getMaterial() != Material.air || mc.theWorld.getBlockState(new BlockPos(a, b, c)).getBlock().getMaterial() != Material.air) && top) {
        		for (;;b++) {
        			if (mc.theWorld.getBlockState(new BlockPos(a, b, c)).getBlock().getMaterial() == Material.air && mc.theWorld.getBlockState(new BlockPos(a, b+1, c)).getBlock().getMaterial() == Material.air) {
        				b--;
        				break;
        			}
        		}
        		
        	}
        	BlockPos bll = new BlockPos(a, b, c);
        	
			double entityPosX = bll.getX()-mc.thePlayer.posX;				
    		double entityPosY = bll.getY()-mc.thePlayer.posY+1.5;
    		double entityPosZ = bll.getZ()-mc.thePlayer.posZ;
    		
    		for (k=1;u.verif(entityPosX, k) > 4;k++) {}
    		for (;u.verif(entityPosY, k) > 4;k++) {}
    		for (;u.verif(entityPosZ, k) > 4;k++) {} 
    		EntityWitch en1 = new EntityWitch(mc.theWorld);
    		en1.setPosition(bll.getX(), bll.getY(), bll.getZ());
    		tp.doTpAller(en1, entityPosX, entityPosY, entityPosZ, classic, k);
    		// Puis on re set notre position finale exact
    		BlockPos actualPos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
    		mc.thePlayer.setPosition(mc.thePlayer.posX+0.5, mc.thePlayer.posY+0.01*Math.random(), mc.thePlayer.posZ+0.5);
        	mc.thePlayer.playSound("mob.endermen.portal", 1.0F, 1.0F);
        	if (Math.random()<0.2)
        		u.checkXp(u.getRandInt(7)+1);
        	delay=0;
        }        
        delay++;
        
    }

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public boolean isClassic() {
		return classic;
	}

	public void setClassic(boolean classic) {
		this.classic = classic;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public BlockPos getSpawn() {
		return spawn;
	}

	public void setSpawn(BlockPos spawn) {
		this.spawn = spawn;
	}
    
    
    	
}
