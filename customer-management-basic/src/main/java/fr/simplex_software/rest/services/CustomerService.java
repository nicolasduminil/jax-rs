package fr.simplex_software.rest.services;

import java.math.*;
import java.net.*;
import java.util.*;

import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import fr.simplex_software.rest.domain.*;
import fr.simplex_software.rest.entities.*;
import fr.simplex_software.rest.repository.*;

@Path("/customers")
public class CustomerService
{
  @EJB
  private CustomerFacade customerFacade;

  @POST
  @Consumes("application/x-java-serialized-object")
  public Response createCustomer(Customer customer)
  {
    CustomerEntity customerEntity = new CustomerEntity(customer);
    customerEntity = customerFacade.save(customerEntity);
    return Response.created(URI.create("/customers/" + customerEntity.getId())).build();
  }

  @GET
  @Produces("application/x-java-serialized-object")
  public List<? extends Customer> getCustomers()
  {
    return customerFacade.findAll();
  }

  @GET
  @Path("{id}")
  @Produces("application/x-java-serialized-object")
  public Customer getCustomer(@PathParam("id") BigInteger id)
  {
    return customerFacade.findBy(id);
  }

  @PUT
  @Path("{id}")
  @Consumes("application/x-java-serialized-object")
  public void updateCustomer(@PathParam("id") BigInteger id, Customer customer)
  {
    CustomerEntity ceCurrent = customerFacade.findBy(id);
    if (ceCurrent == null)
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    CustomerEntity ceNew = new CustomerEntity(customer);
    ceNew.setId(ceCurrent.getId());
    customerFacade.save(ceNew);
  }

  @DELETE
  @Path("{id}")
  public void deleteCustomer(@PathParam("id") BigInteger id)
  {
    customerFacade.findAndRemove(id);
  }
}
