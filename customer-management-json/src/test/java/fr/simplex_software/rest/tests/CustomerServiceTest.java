package fr.simplex_software.rest.tests;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;
import javax.xml.bind.*;
import javax.xml.bind.JAXBContext;

import org.eclipse.persistence.jaxb.*;
import org.hamcrest.*;
import org.junit.*;
import org.junit.runners.*;

import fr.simplex_software.rest.domain.*;
import fr.simplex_software.rest.jaxb.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceTest
{
  private static Client client;

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
    printXml(newCustomer);
    printJson(newCustomer);
    Response response = client.target("http://localhost:8080/jaxb-json/services/customers").request().post(Entity.entity(new CustomerJaxb(newCustomer), "application/json"));
    Assert.assertEquals(201, response.getStatus());
    String location = response.getLocation().toString();
    System.out.println("*** Location: " + location + " ***");
    response.close();
  }
  
  @Test
  public void test2() throws Exception
  {
    System.out.println("*** Get Customers Badger ***");
    CustomerListJaxb customers = client.target("http://localhost:8080/jaxb-json/services/customers/badger").request().get(CustomerListJaxb.class);
    Assert.assertNotNull(customers);
    Assert.assertNotNull(customers.getCustomers());
    Assert.assertTrue(customers.getCustomers().size() > 0);
    Customer customer = customers.getCustomers().get(0);
    Assert.assertEquals("Nick", customer.getFirstName());
  }
  
  @Test
  public void test3() throws Exception
  {
    System.out.println("*** Get Customers Mapped ***");
    CustomerListJaxb customers = client.target("http://localhost:8080/jaxb-json/services/customers/mapped").request().get(CustomerListJaxb.class);
    Assert.assertNotNull(customers);
    Assert.assertNotNull(customers.getCustomers());
    Assert.assertTrue(customers.getCustomers().size() > 0);    
    Customer customer = customers.getCustomers().get(0);
    Assert.assertEquals("Nick", customer.getFirstName());
  }
  
  @Test
  public void test4() throws Exception
  {
    System.out.println("*** Get Customers Badger HTML ***");
    Response html = client.target("http://localhost:8080/jaxb-json/services/customers/badger.html").request().get();
    Assert.assertEquals(200, html.getStatus());
    String customersHTML = html.readEntity(String.class);
    Assert.assertThat(customersHTML, Matchers.containsString("customerList"));
    System.out.println(customersHTML);
  }

  @Test
  public void test5() throws Exception
  {
    System.out.println("*** Get Customers Mapped HTML ***");
    Response html = client.target("http://localhost:8080/jaxb-json/services/customers/mapped.html").request().get();
    Assert.assertEquals(200, html.getStatus());
    String customersHTML = html.readEntity(String.class);
    Assert.assertThat(customersHTML, Matchers.containsString("customerList"));
    System.out.println(customersHTML);
  }

  private void printXml(Customer customer) throws Exception
  {
    System.out.println("*** Customer XML ***");
    JAXBContext jc = JAXBContext.newInstance(CustomerJaxb.class);
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(new CustomerJaxb(customer), System.out);
    System.out.println();
  }
  
  private void printJson (Customer customer) throws Exception
  {
    System.out.println("*** Customer JSON ***");
    JAXBContext jc = JAXBContext.newInstance(CustomerJaxb.class);
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
    marshaller.marshal(new CustomerJaxb(customer), System.out);    
    System.out.println();
  }
}
