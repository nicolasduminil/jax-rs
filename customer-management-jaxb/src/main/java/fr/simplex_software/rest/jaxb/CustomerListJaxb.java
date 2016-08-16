package fr.simplex_software.rest.jaxb;

import java.util.*;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class CustomerListJaxb
{
  private List<CustomerJaxb> customers;

  public CustomerListJaxb()
  {
    super();
  }

  public CustomerListJaxb(List<CustomerJaxb> customers)
  {
    super();
    this.customers = customers;
  }

  public List<CustomerJaxb> getCustomers()
  {
    return customers;
  }

  public void setCustomers(List<CustomerJaxb> customers)
  {
    this.customers = customers;
  }
}
