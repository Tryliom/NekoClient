package neko.event;

import com.darkmagician6.eventapi.events.Cancellable;
import com.darkmagician6.eventapi.events.Event;
import com.darkmagician6.eventapi.types.EventType;

public class UpdateEvent
  implements Event, Cancellable
{
       float pitch, yaw;
       boolean cancel = false;
       boolean onGround, pre;
       public double x;
       public double y;
       public double z;
       EventType type;

      public UpdateEvent(float yaw, float pitch, double x, double y, double z, boolean onGround, EventType type, boolean pre)
      {
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
        this.x = x;
        this.z = z;
        this.onGround = onGround;
        this.type = type;
        this.pre = pre;
      }

      public UpdateEvent()
      {
      }


      public boolean isCancelled()
      {
        return cancel;
      }


    public void setCancelled(boolean state) {

        cancel = state;
    }

      public float getYaw()
      {
        return yaw;
      }

      public float getPitch()
      {
        return pitch;
      }


      public boolean isOnGround()
      {
        return onGround;
      }


      public void setYaw(float yaw)
      {
        this.yaw = yaw;
      }

      public void setPitch(float pitch)
      {
        this.pitch = pitch;
      }


      public void setOnGround(boolean onGround)
      {
        this.onGround = onGround;
      }

      public boolean isPre() {
          return pre;
      }

      public void setPre(boolean pre) {
          this.pre = pre;
      }

      public EventType getType() {
          return type;
      }


    }
