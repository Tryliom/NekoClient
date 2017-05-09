/*
 * Decompiled with CFR 0_115.
 */
package net.mcleaks;

import net.mcleaks.ChatColor;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class MCLeaks {
    public static Session savedSession = null;
    private static String mcLeaksSession;
    private static String mcName;

    public static boolean isAltActive() {
        return mcLeaksSession != null;
    }

    public static String getMCLeaksSession() {
        return mcLeaksSession;
    }

    public static String getMCName() {
        return mcName;
    }

    public static void refresh(String session, String name) {
        mcLeaksSession = session;
        mcName = name;
    }

    public static void remove() {
        mcLeaksSession = null;
        mcName = null;
    }

    public static String getStatus() {
        String status = (Object)((Object)ChatColor.GOLD) + "No Token redeemed. Using " + (Object)((Object)ChatColor.YELLOW) + Minecraft.getMinecraft().getSession().getUsername() + (Object)((Object)ChatColor.GOLD) + " to login!";
        if (mcLeaksSession != null) {
            status = (Object)((Object)ChatColor.GREEN) + "Token active. Using " + (Object)((Object)ChatColor.AQUA) + mcName + (Object)((Object)ChatColor.GREEN) + " to login!";
        }
        return status;
    }
}

