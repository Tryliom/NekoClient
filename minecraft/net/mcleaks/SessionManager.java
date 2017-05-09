/*
 * Decompiled with CFR 0_115.
 */
package net.mcleaks;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class SessionManager {
    public static void setSession(String mcName) {
        SessionManager.setSession(new Session(mcName, "", "", "mojang"));
    }

    public static void setSession(Session session) {
        SessionManager.setFieldByClass(Minecraft.getMinecraft(), session);
    }

    public static void setFieldByClass(Object parentObject, Object newObject) {
        AccessibleObject field = null;
        for (Field f : parentObject.getClass().getDeclaredFields()) {
            if (!f.getType().isInstance(newObject)) continue;
            field = f;
            break;
        }
        if (field == null) {
            return;
        }
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            ((Field) field).set(parentObject, newObject);
            field.setAccessible(accessible);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

