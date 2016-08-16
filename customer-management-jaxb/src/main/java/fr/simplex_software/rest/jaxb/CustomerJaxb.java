package fr.simplex_software.rest.jaxb;

import javax.xml.bind.annotation.*;

import fr.simplex_software.rest.domain.*;

@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class CustomerJaxb extends Customer
{
  private static final long serialVersionUID = 1L;

  public CustomerJaxb()
  {
    super();
  }

  public CustomerJaxb(String firstName, String lastName, String street, String city, String state, String zip, String country)
  {
    super(firstName, lastName, street, city, state, zip, country);
  }
  
  public CustomerJaxb(Customer customer)
  {
    this.setCity(customer.getCity());
    this.setCountry(customer.getCountry());
    this.setFirstName(customer.getFirstName());
    this.setLastName(customer.getLastName());
    this.setState(customer.getState());
    this.setStreet(customer.getStreet());
    this.setZip(customer.getZip());
  }

  @XmlElement
  public String getFirstName()
  {
    return super.getFirstName();
  }

  @XmlElement
  public String getLastName()
  {
    return super.getLastName();
  }

  @XmlElement
  public String getStreet()
  {
    return super.getStreet();
  }
  
  @XmlElement
  public String getCity()
  {
    return super.getCity();
  }

  @XmlElement
  public String getState()
  {
    return super.getState();
  }

  @XmlElement
  public String getZip()
  {
    return super.getZip();
  }

  @XmlElement
  public String getCountry()
  {
    return super.getCountry();
  }

}
