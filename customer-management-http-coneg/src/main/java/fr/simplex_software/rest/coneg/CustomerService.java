package fr.simplex_software.rest.coneg;

import java.math.*;

import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import fr.simplex_software.rest.domain.*;
import fr.simplex_software.rest.repository.*;

public class CustomerService
{
  @EJB
  private CustomerFacade customerFacade;

  @GET
  @Path("{id}")
  @Produces(
  { "application/xml", "application/json" })
  public Customer getCustomer(@PathParam("id") BigInteger id)
  {
    Customer customer = customerFacade.findBy(id);
    if (customer == null)
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    return customer;
  }

  @GET
  @Path("{id}")
  @Produces("text/plain")
  public String getCustomerString(@PathParam("id") BigInteger id)
  {
    return getCustomer(id).toString();
  }
}
