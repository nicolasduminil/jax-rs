package fr.simplex_software.rest.coneg;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.junit.*;

import fr.simplex_software.rest.domain.*;
import fr.simplex_software.rest.jaxb.*;

public class TestCustomerService
{
  private static Client client;
  private String location;

  @Before
  public void setUp() throws Exception
  {
    client = ClientBuilder.newClient();
  }

  @After
  public void tearDown() throws Exception
  {
    client.close();
    client = null;
  }

  @Test
  public void test1() throws Exception
  {
    // Create a new customer
    System.out.println("*** Create a new Customer ***");
    Customer newCustomer = new Customer("Nick", "DUMINIL", "26 Allée des Sapins", "Soisy sous Montmorency", "None", "95230", "France");
    Response response = client.target("http://localhost:8080/jaxb-json/services/customers").request().post(Entity.entity(new CustomerJaxb(newCustomer), "application/json"));
    Assert.assertEquals(201, response.getStatus());
    location = response.getLocation().toString().replaceFirst("jaxb-json", "coneg");
    System.out.println("*** Location: " + location + " ***");
  }
  
  public void test2() throws Exception
  {
    // Get customer JSON
    System.out.println("*** Get Customer JSON ***");
    Customer customer = client.target(location).request().accept("application/json").get(CustomerJaxb.class);
    Assert.assertNotNull(customer);
    Assert.assertEquals("Nick", customer.getFirstName());
  }
  
  public void test3() throws Exception
  {
    // Get customer XML
    System.out.println("*** Get Customer XML ***");
    Customer customer = client.target(location).request().accept("application/xml").get(CustomerJaxb.class);
    Assert.assertNotNull(customer);
    Assert.assertEquals("Nick", customer.getFirstName());
  }

  public void test4() throws Exception
  {
    // Get customer String
    System.out.println("*** Get Customer String ***");
    String customer = client.target(location).request().accept("text/plain").get(String.class);
    Assert.assertNotNull(customer);
    System.out.println(customer);
  }

  public void test5()
  {
    // Delete customer
    System.out.println("*** Delete Customer ***");
    Response response = client.target(location).request().delete();
    Assert.assertEquals(200, response.getStatus());
  }
}
