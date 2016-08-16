package fr.simplex_software.rest.services;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.*;

import org.jboss.resteasy.annotations.providers.jaxb.json.*;
import org.jboss.resteasy.plugins.providers.jaxb.json.*;

import fr.simplex_software.rest.entities.*;
import fr.simplex_software.rest.jaxb.*;
import fr.simplex_software.rest.repository.*;

@Path("/customers")
public class CustomerService
{
  @EJB
  private CustomerFacade customerFacade;
  
  @POST
  @Consumes("application/json")
  public Response createCustomer(CustomerJaxb customer)
  {
    CustomerEntity customerEntity = new CustomerEntity (customer);
    customerEntity = customerFacade.saveAndFlushAndRefresh(customerEntity);
    return Response.created(URI.create("/customers/" + customerEntity.getId())).build();
  }

  private static List<CustomerJaxb> ce2c (List<CustomerEntity> from, Function<CustomerEntity, CustomerJaxb> func)
  {
    return from.stream().map(func).collect(Collectors.toList());
  }
  
  @GET
  @Path("badger")
  @Produces("application/json")
  @BadgerFish
  public CustomerListJaxb getCustomersBadger()
  {
    return new CustomerListJaxb (ce2c (customerFacade.getCustomerRepository().findAll(), ce -> new CustomerJaxb(ce)));
  }

  @GET
  @Path("mapped")
  @Produces("application/json")
  public CustomerListJaxb getCustomersMapped()
  {
    return getCustomersBadger();
  }

  @GET
  @Path("badger.html")
  @Produces("text/html")
  public Response getCustomersBadgerText() throws Exception
  {
    return getCustomerText (new BadgerContext(CustomerListJaxb.class));
  }

  @GET
  @Path("mapped.html")
  @Produces("text/html")
  public Response getCustomersMappedText() throws Exception
  {
    return getCustomerText (new JettisonMappedContext(CustomerListJaxb.class));
  }
  
  private Response getCustomerText (JAXBContext context) throws Exception
  {
    List<CustomerJaxb> customers = ce2c (customerFacade.getCustomerRepository().findAll(), ce -> new CustomerJaxb(ce));
    CustomerListJaxb cl = new CustomerListJaxb(customers);
    StringWriter writer = new StringWriter();
    Marshaller marshaller = context.createMarshaller();
    marshaller.marshal(cl, writer);
    return Response.ok(writer.toString()).build();    
  }
}
