import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;


public class Bewegungsmelder {

    public static class BewegungsListener implements GpioPinListenerDigital {

		public void handleGpioPinDigitalStateChangeEvent(
				GpioPinDigitalStateChangeEvent event) {
			
			GpioPin pin = event.getPin();
			PinState state = event.getState();
			
			if(state.isHigh()){
				System.out.println("Bewegung registriert");
			}
			else{
				System.out.println("Keine Bewegung registriert");
			}
			// TODO Auto-generated method stub
			
		}

    }  
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput out = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04,PinPullResistance.PULL_DOWN);

        out.addListener(new GpioPinListenerDigital() {			
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {		
	                if(event.getState().isHigh()){	
	                    System.out.println("Motion Detected!"); 
	                }					
            }		
        					
        });
        
        try{
        	for(;;){
        		Thread.sleep(500);
        	}
        	
        }catch(final Exception e){
        	
        }
	}
	
}
