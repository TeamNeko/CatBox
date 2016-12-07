package org.teamneko.schrodinger.client;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.BoxSearchResult;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.json.ProductSearchResult;
import org.teamneko.meowlib.json.SearchResult;
import org.teamneko.meowlib.json.TransactionRequest;
import org.teamneko.meowlib.json.User;

import com.sun.jersey.api.client.UniformInterfaceException;

public class Application {
	private static final int MODE_INVALIDE = 0;
	private static final int MODE_AJOUTER = 1;
	private static final int MODE_ENLEVER = 2;
	private static final int MODE_QUITTER = 3;
	
	private static Scanner inputScanner;
	private static Application application;
	
	private HashMap<Integer, Integer> produitsModifies;
	private SchrodingerClient client;
	private User user;
	
	public static void main(String[] args) {
		String userCode, codeBarre;
		
		application = new Application();
		inputScanner = new Scanner(System.in);
		
		do 
		{
			System.out.print("Entrer code utilisateur: ");
			userCode = inputScanner.nextLine();
		} while (!application.loginUser(userCode));
		
		System.out.println("Bon matin " + application.getUser().getFirstName());
		
		do {
			System.out.print("Scan: ");
			codeBarre = inputScanner.nextLine();
			if(!codeBarre.isEmpty())
				application.chercher(codeBarre);
		} while(!codeBarre.isEmpty());
		
		inputScanner.close();
		System.out.println("Bye Bye");
	}
	
	public void chercher(String codeBarre) {
		SearchResult resultat = application.getClient().search(codeBarre);
		
		if("not found".equals(resultat.getType())) {
			System.out.println("Nouvelle boite");
			ajouterEneleverItemsBoite(codeBarre);
		} else {
			if(resultat.getClass() == BoxSearchResult.class) {
				Box boite = ((BoxSearchResult) resultat).getBox();
				afficherContenuBoite(boite.getId());
				ajouterEneleverItemsBoite(codeBarre);
			} else {
				System.out.println(resultat);
			}
		}
	}
	
	public void afficherContenuBoite(int idBoite) {
		for(NamedProduct product :  client.getBoxDetails(idBoite))
		{
			System.out.println(product.getName() + ": " + product.getQuantity());
		}
	}
	
	public void ajouterEneleverItemsBoite(String boxBarcode) {
		int mode;
		produitsModifies = new HashMap<Integer, Integer>();
		
		do {
			mode = lireMode();
			modifierContenuBoite(mode);
		} while(mode != MODE_QUITTER);
		
		if(produitsModifies.size() > 0) {
			envoyerTransaction(boxBarcode);
			System.out.println("Transaction envoyée");
		}
	}
	
	public void envoyerTransaction(String codeBarre) {
		TransactionRequest transaction = new TransactionRequest();
		List<TransactionRequest.Product> produits = transaction.getProductsModified();
		
		transaction.setUser((int)user.getId());
		transaction.setBox(codeBarre);
		
		for(Integer id: produitsModifies.keySet()) {
			int quantite = produitsModifies.get(id);
			produits.add(new TransactionRequest.Product(id, quantite));
		}
		
		try {
			client.postTransaction(transaction);
		} catch(UniformInterfaceException e) {
			System.err.println("Erreur lors de l'envoi au serveur: " + e.getMessage());
		}

	}
	public int lireMode() {
		int mode = MODE_INVALIDE;
		System.out.println("1: Ajouter, 2: Enlever");
		
		while(mode == MODE_INVALIDE) {
			System.out.print(">");
			String choice = inputScanner.nextLine();
			if(choice.isEmpty()) {
				mode = MODE_QUITTER;
			} else if("1".equals(choice)) {
				System.out.println("Mode Ajouter");
				mode = MODE_AJOUTER;
			} else if("2".equals(choice)) {
				System.out.println("Mode Enlever");
				mode = MODE_ENLEVER;
			}
		}
		
		return mode;
	}
	
	public void modifierContenuBoite(int mode) {
		String codeBarre;
		
		if(mode == MODE_QUITTER)
			return;
		
		do {
			System.out.print("Scan: ");
			codeBarre = inputScanner.nextLine();
			if(!codeBarre.isEmpty())
				modifierProduit(codeBarre, mode);
		} while(!codeBarre.isEmpty());
		
		System.out.println("Done");
	}
	
	public void modifierProduit(String codeBarre, int mode) {
		SearchResult resultat = client.search(codeBarre);
		
		//Si le code barre est une code barre de produit
		if(resultat.getClass() == ProductSearchResult.class) {
			//Récupérer produit et id produit
			Product product = ((ProductSearchResult)resultat).getProduct();
			int productId = product.getId();
			
			//Récupéret quantité actuelle
			int quantite = produitsModifies.containsKey(productId) ? produitsModifies.get(productId) : 0;
			
			//+1/-1 dépendant du mode
			if(mode == MODE_AJOUTER)
				quantite++;
			else if(mode == MODE_ENLEVER)
				quantite--;
				
			//Enlever si quantité = 0
			if(quantite == 0)
				produitsModifies.remove(productId);
			//Sinon modifier
			else
				produitsModifies.put(productId, quantite);
			
			System.out.println(product.getName() + ": " + quantite);
		} else {
			System.out.println("Produit Invalide");
		}
	}
	
	public Application() {
		client = new SchrodingerClient("http://192.168.0.1:8080/Frontend/rest");
		client.removeAllFilters();
	}
	
	public SchrodingerClient getClient() {
		return client;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean loginUser(String userCode) {
		try {
			//Attempt to authenticate user
			user = client.requestUser(userCode);
			
			//Successful
			return true;
		} catch(UniformInterfaceException e) {
			//Could not authenticate
			return false;
		}
	}

}
