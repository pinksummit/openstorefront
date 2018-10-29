/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.dynamo;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import java.util.Arrays;

/**
 *
 * @author dshurtleff
 */
public class DynamoTest
{

	public static void main(String[] args) throws Exception
	{

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
				.build();

		//DynamoDBMapper mapper = new DynamoDBMapper(client);
		DynamoDB dynamoDB = new DynamoDB(client);

		String tableName = "Components";

		try {
			System.out.println("Attempting to create table; please wait...");

			Table table = dynamoDB.createTable(tableName,
					Arrays.asList(new KeySchemaElement("year", KeyType.HASH), // Partition
							// key
							new KeySchemaElement("name", KeyType.RANGE)), // Sort key
					Arrays.asList(new AttributeDefinition("year", ScalarAttributeType.N),
							new AttributeDefinition("name", ScalarAttributeType.S)),
					new ProvisionedThroughput(10L, 10L));
			table.waitForActive();
			System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

		} catch (Exception e) {
			System.err.println("Unable to create table: ");
			System.err.println(e.getMessage());
		}

	}

}
