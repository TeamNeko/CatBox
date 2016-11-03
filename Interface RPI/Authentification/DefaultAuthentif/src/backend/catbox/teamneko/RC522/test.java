package RC522;

public class test {

	public static void main(String args[]) throws InterruptedException
    {
		
	        MFRC522 rc522=new MFRC522();
	        String strUID;
	        
	    while (true){    
	    	strUID = rc522.ReadID(500, 3);
	    	System.out.println("Detecte card: "+strUID);
	    }
	
    };

}
