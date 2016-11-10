package org.teamneko.schrodinger.backend.gui;

import org.teamneko.schrodinger.backend.gui.main_window;
import org.teamneko.schrodinger.client.SchrodingerClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.naming.directory.SearchResult;

public class BarcodeScannerController implements ActionListener,KeyListener {

	private main_window refMain_Window;
	private String text = "";
	public SchrodingerClient client = new SchrodingerClient("http://localhost:8080/Frontend/rest");
	org.teamneko.meowlib.dto.SearchResult searchResult;
	public void setMainWindow(main_window ref) {
		refMain_Window = ref;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String doubleCodebarBuffer = refMain_Window.getjTextField_barcode().getText();
		if(!text.isEmpty()){
			System.out.println(text);
			doubleCodebarBuffer = doubleCodebarBuffer.replaceFirst(text, "");
			refMain_Window.setjTextField_barcode("");
			System.out.println(doubleCodebarBuffer);
		}
		text = doubleCodebarBuffer;
		System.out.println(text);
		refMain_Window.setjTextField_barcode(text);
		searchResult = client.search(text);
		
		if (searchResult.getType() == "box")
		{
			refMain_Window.setjLabel_type("Type: Boîte");
			/*
			refMain_Window.setjTable_items("ID", 0, 0);
			refMain_Window.setjTable_items(searchResult.box.id, 0, 1);
			refMain_Window.setjTable_items("Format", 1, 0);
			refMain_Window.setjTable_items(searchResult.box.size, 1, 1);
			refMain_Window.setjTable_items("Poid", 2, 0);
			refMain_Window.setjTable_items(searchResult.box.weight, 2, 1);
			refMain_Window.setjTable_items("Dernière modification", 3, 0);
			refMain_Window.setjTable_items(searchResult.box.modified, 3, 1);*/
		}
		else if (searchResult.getType() == "product")
		{
			refMain_Window.setjLabel_type("Type: Item");
			/*
			refMain_Window.setjTable_items("ID", 0, 0);
			refMain_Window.setjTable_items(searchResult.product.id, 0, 1);
			refMain_Window.setjTable_items("Nom", 1, 0);
			refMain_Window.setjTable_items(searchResult.product.size, 1, 1);
			refMain_Window.setjTable_items("Quantité", 2, 0);
			refMain_Window.setjTable_items(searchResult.product.weight, 2, 1);
			refMain_Window.setjTable_items("Poid", 3, 0);
			refMain_Window.setjTable_items(searchResult.product.modified, 3, 1);*/
		}
		else if (searchResult.getType() == "shelf")
		{
			refMain_Window.setjLabel_type("Type: Étagère");
			/*
			refMain_Window.setjTable_items("ID", 0, 0);
			refMain_Window.setjTable_items(searchResult.shelf.id, 0, 1);
			refMain_Window.setjTable_items("Emplacement", 1, 0);
			refMain_Window.setjTable_items(searchResult.shelf.size, 1, 1);
			refMain_Window.setjTable_items("Quantité", 2, 0);
			refMain_Window.setjTable_items(searchResult.shelf.weight, 2, 1);
			refMain_Window.setjTable_items("Poid", 3, 0);
			refMain_Window.setjTable_items(searchResult.shelf.modified, 3, 1);*/
		}
		else 
			refMain_Window.setjLabel_type(searchResult.getType());
				
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	  /*  if(text == ""){
	    	refMain_Window.setjTextField_barcode(text);
	    	System.out.println("RESET");
	    }
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			refMain_Window.setjTextField_barcode(text);
			text = "";
	}
		else{
			text += arg0.getKeyChar();
		}*/
		System.out.println("test");
	}

}
