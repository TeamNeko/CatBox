package org.teamneko.schrodinger.backend.gui;

import org.teamneko.meowlib.dto.BoxSearchResult;
import org.teamneko.meowlib.dto.NamedProduct;
import org.teamneko.meowlib.dto.ProductSearchResult;
import org.teamneko.meowlib.dto.UserSearchResult;
import org.teamneko.schrodinger.backend.gui.main_window;
import org.teamneko.schrodinger.client.SchrodingerClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.naming.directory.SearchResult;
import javax.swing.table.DefaultTableModel;

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
		refMain_Window.getjButton_modifier().setEnabled(false);
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
			refMain_Window.getjButton_modifier().setEnabled(true);
			refMain_Window.setjLabel_type("Type: Boîte");
			BoxSearchResult box = (BoxSearchResult)searchResult;	
			refMain_Window.barcode = box.getBox().getBarcode();
			refMain_Window.idContainer = box.getBox().getId();
			
			String col[] = {"#id","Format","Poids", "Dernière modification"};
	        DefaultTableModel model = new DefaultTableModel(col, 0);
	        Object o[] = {box.getBox().getId(), box.getBox().getSize(), box.getBox().getWeight(), box.getBox().getModified()};
	        model.addRow(o);
	        refMain_Window.getjTable_items().setModel(model);
	        refMain_Window.getjTable_items().changeSelection(0, 0, false, false);
			
		}
		else if (searchResult.getType() == "product")
		{
			refMain_Window.setjLabel_type("Type: Item");
			ProductSearchResult product = (ProductSearchResult)searchResult;
			
			String col[] = {"#id","Nom","Quantité", "Poids"};
	        DefaultTableModel model = new DefaultTableModel(col, 0);
	        Object o[] = {product.getProduct().getId(), product.getProduct().getName() , /*product.getProduct().getQuantity()*/"50" , product.getProduct().getWeight()};
	        model.addRow(o);
	        refMain_Window.getjTable_items().setModel(model);
	        refMain_Window.getjTable_items().changeSelection(0, 0, false, false);
	
		}
		else if (searchResult.getType() == "shelf")
		{
			refMain_Window.setjLabel_type("Type: étagère");
			/*
			String col[] = {"#id","Emplacement","Quantité", "Poids"};
	        DefaultTableModel model = new DefaultTableModel(col, 0);
	        Object o[] = {shelf.getShelf().getId(), shelf.getShelf().getLocation(),shelf.getShelf().getQuantity(), shelf.getShelf().getWeight()};
	        model.addRow(o);
	        refMain_Window.getjTable_items().setModel(model);
	        refMain_Window.getjTable_items().changeSelection(0, 0, false, false);
	        */
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
