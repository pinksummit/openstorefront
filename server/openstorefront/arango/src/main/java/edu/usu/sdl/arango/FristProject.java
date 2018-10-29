/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.arango;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;

/**
 *
 * @author dshurtleff
 */
public class FristProject
{

	public static void main(final String[] args)
	{

		ArangoDB arangoDB = new ArangoDB.Builder().build();

		String dbName = "storefront";
		try {
			arangoDB.createDatabase(dbName);
			System.out.println("Database created: " + dbName);
		} catch (ArangoDBException e) {
			System.err.println("Failed to create database: " + dbName + "; " + e.getMessage());
		}

		String collectionName = "component";
		try {
			CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);
			System.out.println("Collection created: " + myArangoCollection.getName());
		} catch (ArangoDBException e) {
			System.err.println("Failed to create collection: " + collectionName + "; " + e.getMessage());
		}

		BaseDocument myObject = new BaseDocument();
		myObject.setKey("myKey");
		myObject.addAttribute("a", "Foo");
		myObject.addAttribute("b", 42);
		try {
			arangoDB.db(dbName).collection(collectionName).insertDocument(myObject);
			System.out.println("Document created");
		} catch (ArangoDBException e) {
			System.err.println("Failed to create document. " + e.getMessage());
		}

		try {
			BaseDocument myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("myKey",
					BaseDocument.class);
			System.out.println("Key: " + myDocument.getKey());
			System.out.println("Attribute a: " + myDocument.getAttribute("a"));
			System.out.println("Attribute b: " + myDocument.getAttribute("b"));
		} catch (ArangoDBException e) {
			System.err.println("Failed to get document: myKey; " + e.getMessage());
		}

		arangoDB.shutdown();
	}

}
