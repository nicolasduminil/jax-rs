package fr.simplex_software.rest.jaxb;

import java.io.*;

import javax.xml.bind.*;

import org.junit.*;

public class TestCustomerJaxb
{
  @Test
  public void test() throws Exception
  {
    JAXBContext jc = JAXBContext.newInstance(CustomerJaxb.class);
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    CustomerJaxb customer = (CustomerJaxb)unmarshaller.unmarshal(new File ("src/main/resources/fr/simplex_software/rest/jaxb/input.xml"));
    Assert.assertNotNull(customer);
    Assert.assertEquals("Nicolas", customer.getFirstName());
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(customer, System.out);
  }
}
