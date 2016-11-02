package backend.catbox.teamneko.RC522;

public class test {

	public static void main(String args[]) throws InterruptedException
    {
		
	        MFRC522 rc522=new MFRC522();
	        
	    while (true){    
	    	if (rc522.ReadID());
	    	System.out.println("Detecte card: "+ MFRC522.currentID);
	    }
	
    };

}
