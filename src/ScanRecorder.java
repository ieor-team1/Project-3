
import lejos.nxt.*;
import lejos.util.Datalogger;

/**
 * records the results of a 180 degree scan (angle and light intensity
 * to a dataLogger and plays it back over a USB port
 * @author owner.GLASSEY
 */

public class ScanRecorder
{


   public ScanRecorder(NXTRegulatedMotor theMotor, LightSensor eye)
   {
      motor = theMotor;    
      _eye = eye;
      _eye.setFloodlight(false);
   }

   /**
    * returns the angle at which the maximum light intensity was found
    * @return 
    */
   public int getTargetBearing()
   {
      return _targetBearing;
   }
/**
    * returns the maximum light intensity found during the scan
    * @return  light intensity
    */
   public int getLight()
   {
      return _maxLight;
   }
/**
    * returns the angle in which the light sensor is pointing
    * @return the angle
    */
   public int getHeadAngle()
   {
      return motor.getTachoCount();
   }
 /**
    * sets the motor sped in deg/sec
    * @param speed 
    */
   public void setSpeed(int speed)
   {
      motor.setSpeed(speed);
   }
/**
    * scan from current head angle to limit angle and write the angle and 
    * light sensor value to the datalog
    * @param limitAngle 
    */
   public int scanTo(int limitAngle)
   {
      int oldAngle = motor.getTachoCount();
      motor.rotateTo(limitAngle, true);
      light = 0;
      _maxLight=0;
      while (motor.isMoving())
      {
         short angle = (short) motor.getTachoCount();
         if (angle != oldAngle)
         {
            light = _eye.getLightValue();
     	   if(light > _maxLight){
    		   _maxLight = light;
    		   _targetBearing = angle;
    	   }
            oldAngle = angle;
         }
         //Thread.yield();
      }
      System.out.println(_maxLight);
      return _maxLight;
   }
   

/**
    * rotate the scanner head to the angle
    * @param angle
    * @param instantReturn if true, the method is non-blocking
    */
   public void rotateTo(int angle, boolean instantReturn)
   {
      motor.rotateTo(angle, instantReturn);
   }
/**
    * rotates the scaner head to angle;  returns when rotation is complete
    * @param angle 
    */
   public void rotateTo(int angle)
   {
      rotateTo(angle, false);
   }
/**
    * scan between -80 and 80 degrees
    * @param args 
    */
   public int scan(int i)
   {
      int maxPrinting = scanTo((int) Math.pow(-1, i) * 80);
      _angle1 = getTargetBearing();
      
      //s.scanTo(-90);

      return maxPrinting;
   }
   /******* instance variabled ***************/
   NXTRegulatedMotor motor;
   LightSensor _eye;
   int _targetBearing;
   int _maxLight;
   int light;
   int maxPrinting;
   boolean _found = false;
   int _angle1;
}
