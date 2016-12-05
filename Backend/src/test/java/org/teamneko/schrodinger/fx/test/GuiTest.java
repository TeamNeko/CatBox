package org.teamneko.schrodinger.fx.test;
import static org.junit.Assert.*;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;

import org.junit.Before;
import org.junit.Test;	
import org.teamneko.schrodinger.backend.fx.ApplicationLauncher;
import org.teamneko.schrodinger.backend.fx.Context;
import org.teamneko.schrodinger.backend.fx.ModifiedProduct;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import com.athaydes.automaton.Speed;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class GuiTest {

	FXer fxer;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
    @Test
    public void testIntegrationInterface() throws InterruptedException {
    	setupTest();
    	toolkit.setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
    	fxer.pause(100);
    	fxer.clickOn( fxer.getAt( Label.class ) )
    		//Click User barcode textfield
			.moveBy(750, 750, Speed.VERY_FAST) 
			.click()
	    	.type("c5236209fg\n")
			.pause(3000);
		Label loginName = ( Label ) fxer.getAt( Label.class );
    	assertEquals(" Utilisateur: Mew Savaria (C5236209FG)", loginName.getText());
    	
    	fxer.click()
			.type("66224891271\n")
			.pause(3000);
    	String testProduct = Context.getInstance().getDetailPane().getDetailList().getItems().get(0);
		assertEquals("Product: Final Fantasy X/X2 HD Remaster", testProduct);
		
		fxer.click()
			.type("c5a69609fc\n")
			.pause(3000);
		String testUser = Context.getInstance().getDetailPane().getDetailList().getItems().get(0);
		assertEquals("Gestionnaire: Tubbs Blanchette", testUser);
		
		fxer.click()
			.type("14142536476\n")
			.pause(3000);
		String testNull = Context.getInstance().getDetailPane().getDetailList().getItems().get(0);
		assertEquals("Non trouvé", testNull);
		
		fxer.click()
			.type("0587800564\n")
			.pause(3000);
		String testBoite = Context.getInstance().getDetailPane().getDetailList().getItems().get(0);
		assertEquals("Boite: ", testBoite);
		
		//Click Modifier
		fxer.moveBy(-700, -500, Speed.VERY_FAST)
			.click()
			.pause(3000);
    	ModifiedProduct testTableProduit = Context.getInstance().getTablePane().getRowItem(0);
		assertEquals("Final Fantasy XIII", testTableProduit.getName());
		
		int product0Qte = Context.getInstance().getTablePane().getRowItem(0).getQuantity();
		int product1Qte = Context.getInstance().getTablePane().getRowItem(1).getQuantity();
	
		//Click +
		fxer.moveBy(0, -200, Speed.VERY_FAST)
			.click()
			.pause(3000);
		testTableProduit = Context.getInstance().getTablePane().getRowItem(0);
		assertEquals(1,testTableProduit.getModifiedqty());
		
		//Click Down arrow
		fxer.moveBy(200, 200, Speed.VERY_FAST)
			.click()
			//Click + 2x
			.moveBy(-200, -200, Speed.VERY_FAST)
			.click()
			.click()
			//Click -
			.moveBy(0, 200, Speed.VERY_FAST)
			.click()
			.pause(3000);
		testTableProduit = Context.getInstance().getTablePane().getRowItem(1);
		assertEquals(1,testTableProduit.getModifiedqty());
		
		//Click Confirmer
		fxer.moveBy(0, 150, Speed.VERY_FAST)
			.click()
			.pause(7000);
		//Click Modifier
		fxer.moveBy(0, -150, Speed.VERY_FAST) 
			.click()
			.pause(3000);
		testTableProduit = Context.getInstance().getTablePane().getRowItem(0);
		assertEquals(product0Qte+1,testTableProduit.getQuantity());
		testTableProduit = Context.getInstance().getTablePane().getRowItem(1);
		assertEquals(product1Qte+1,testTableProduit.getQuantity());
		
		//Click Annuler
		fxer.moveBy(0, 450, Speed.VERY_FAST)
			.click()
			.pause(3000)
			//Click ListField
			.moveBy(400, 0, Speed.VERY_FAST)
			.click()
			.type("12165798412\n")
			.pause(3000)
			//Click Créer une nouvelle boite
			.moveBy(-400, -500, Speed.VERY_FAST)
			.click()
			.pause(3000)
			//Click TableField
			.moveBy(500, 0, Speed.VERY_FAST)
			.click()
			.type("66224891271\n")
			.pause(3000);
		testTableProduit = Context.getInstance().getTablePane().getRowItem(0);
		assertEquals("Final Fantasy X/X2 HD Remaster",testTableProduit.getName());
		
		//Click Annuler / Parametre
		fxer.moveBy(-500, 550, Speed.VERY_FAST)
			.click()
			.pause(3000)
			.click()
			//Click Deconnexion
			.moveBy(0, -600, Speed.VERY_FAST)
			.click()
			.pause(3000);
		loginName = ( Label ) fxer.getAt( Label.class );
    	assertEquals(" Utilisateur non-connecte", loginName.getText());
    	
    	fxer.moveBy(600, 600, Speed.VERY_FAST)
    		.click()
    		.type("c5a69609fc\n")
    		.pause(3000);
    	loginName = ( Label ) fxer.getAt( Label.class );
    	assertEquals(" Utilisateur: Tubbs Blanchette (C5A69609FC)", loginName.getText());
    	
		fxer.moveBy(-600, 0, Speed.VERY_FAST)
			.click()
			.pause(2000)
			.moveBy(0, -300, Speed.VERY_FAST)
			.click();
    }
 
	private void setupTest() throws InterruptedException {
		FXApp.startApp(new ApplicationLauncher());
        Thread.sleep(500);
        fxer = FXer.getUserWith(FXApp.getScene().getRoot());
	}
}