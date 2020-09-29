package neko.command.commands;

import neko.command.Command;
import neko.command.CommandType;
import neko.module.modules.hide.Friends;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class FriendRadius extends Command {

	public FriendRadius() {
		super("friend radius", "friend radius []", "Ajoute/supprime les joueurs autour", 3, CommandType.Friend);
	}
	
	public void onCommand(String[] args) {
		for(EntityPlayer en : mc.theWorld.playerEntities) {
            if (en instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase) en;
               
                if (entity instanceof EntityPlayerSP) continue;
                
                if (mc.thePlayer.getDistanceToEntity(entity) <= Integer.parseInt(args[2])) {
        			Friends.updateFriend(entity.getName());
                }
            }
		}
	}
}
