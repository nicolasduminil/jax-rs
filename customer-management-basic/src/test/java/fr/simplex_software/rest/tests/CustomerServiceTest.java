package fr.simplex_software.rest.tests;

import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.junit.*;
import org.junit.runners.*;

import fr.simplex_software.rest.domain.*;
import fr.simplex_software.rest.services.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceTest
{
  private static Client client;

  @BeforeClass
  public static void initClient() throws Exception
  {
    client = ClientBuilder.newClient();
    client.register(JavaMarshaller.class);
  }

  @AfterClass
  public static void closeClient() throws Exception
  {
    client.close();
    client = null;
  }

  @SuppressWarnings("unchecked")
  @Test
  public void test()
  {
    // Create a new customer
    System.out.println("*** Create a new Customer ***");
    Customer newCustomer = new Customer("Nick", "DUMINIL", "26 Allée des Sapins", "Soisy sous Montmorency", "None", "95230", "France");
    Response response = client.target("http://localhost:8080/ex03_1/services/customers").request().post(Entity.entity(newCustomer, "application/x-java-serialized-object"));
    Assert.assertEquals(201, response.getStatus());
    String location = response.getLocation().toString();
    response.close();

    //Get the new customer
    System.out.println("*** Get Created Customer at " + location + " ***"); 
    Customer customer = client.target(location).request().get(Customer.class); 
    Assert.assertEquals("Nick", customer.getFirstName()); 
    Assert.assertEquals(201, response.getStatus());

    // Update the new customer. Change Nick's name to Nicolas
    System.out.println("*** Update Customer at " + location + " ***");
    customer.setFirstName("Nicolas");
    response = client.target(location).request().put(Entity.entity(customer, "application/x-java-serialized-object"));
    Assert.assertEquals(204, response.getStatus());
    response.close();

    // Show the update 
    System.out.println("*** After Update at " + location + " ***");
    customer = client.target(location).request().get(Customer.class); 
    Assert.assertEquals("Nicolas", customer.getFirstName()); 
    Assert.assertEquals(204, response.getStatus());

    // Get all the customers
    System.out.println("*** Get Customers ***");
    List<Customer> customers = client.target("http://localhost:8080/ex03_1/services/customers").request().get(ArrayList.class);
    Assert.assertTrue(customers.size() > 0);
    for (Customer c : customers)
    {
      System.out.println(c.toString());
    }

    // Delete the customer 
    System.out.println("*** Delete Customer at " + location + " ***");
    //Client c = ClientBuilder.newClient();
    client.target(location).request().delete();

    // Show the delete 
    System.out.println("*** After Delete ***");
    try
    {
      customer = client.target(location).request().get(Customer.class); 
      if (customer != null)
        Assert.fail();
    }
    catch (InternalServerErrorException ex)
    {}
    Assert.assertEquals(204, response.getStatus());
    
    // Get all the customers
    System.out.println("*** Get Customers ***");
    customers = client.target("http://localhost:8080/ex03_1/services/customers").request().get(ArrayList.class);
    Assert.assertEquals(0, customers.size());
  }
}
