import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;


public class Navigator { //uses ScanRecorder, Pilot and LightSensor classes to get the data from the sensors and control the robot 
	
	float wheelDiameter =  5.6f; //data for DifferentialPilot class
    float trackWidth = 11.4f;
    LightSensor lightSensor = new LightSensor(SensorPort.S4); //  
	DifferentialPilot pilot = new DifferentialPilot(wheelDiameter, trackWidth, Motor.A, Motor.C); //Pilot class 
	ScanRecorder s = new ScanRecorder(Motor.B, lightSensor);
	int i = 0; //Changes scan sweep direction. Pass it to the ScanRecorder by Scan(i)
	double gain = 1.3f; //control the 
	public void go(){
		pilot.setTravelSpeed(50);
		while(true){
			s.setSpeed(700);
	      int maxLight = s.scan(i); 
	      System.out.println("Max Light = " + maxLight + " Angle =" + s._angle1 + i);
	      if(maxLight>55){
	    	  pilot.stop();
	    	  pilot.rotate(180);
	      }
	      pilot.steer(-s._angle1*gain);
	      i++;
	}
	}
	
}