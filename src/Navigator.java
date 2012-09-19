import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;


public class Navigator {
	
	float wheelDiameter =  5.6f;
    float trackWidth = 11.4f;
    LightSensor lightSensor = new LightSensor(SensorPort.S4);   
	DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C);
	
	ScanRecorder s = new ScanRecorder(Motor.B, lightSensor);
	double gain = 0.5f;
	public void go(){
	      int maxLight = s.scan();
	      while(true){
	      System.out.println("Max Light = " + maxLight + " Angle =" + s._angle1);
	      if(maxLight>46){
	    	  pilot.rotate(180);
	      }
	      pilot.steer(s._angle1*gain);
	}
	}
	
}