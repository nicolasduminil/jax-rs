package fr.simplex_software.rest.entities;

import java.math.*;

import javax.persistence.*;

import fr.simplex_software.rest.domain.*;

@Entity
@Table(name="CUSTOMERS")
public class CustomerEntity extends Customer
{
  private static final long serialVersionUID = 1L;
  private BigInteger id;

  public CustomerEntity()
  {
    super();
  }

  public CustomerEntity(String firstName, String lastName, String street, String city, String state, String zip, String country)
  {
    super(firstName, lastName, street, city, state, zip, country);
  }
  
  public CustomerEntity(Customer customer)
  {
    this (customer.getFirstName(), customer.getLastName(), customer.getStreet(), customer.getCity(), customer.getState(), customer.getZip(), customer.getCountry());
  }

  @Id
  @SequenceGenerator(name = "CUSTOMERS_ID_GENERATOR", sequenceName = "CUSTOMERS_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMERS_ID_GENERATOR")
  @Column(name = "CUSTOMER_ID", unique = true, nullable = false, length = 8)
  public BigInteger getId()
  {
    return id;
  }

  public void setId(BigInteger id)
  {
    this.id = id;
  }
  
  @Column(name="FIRST_NAME", nullable = false, length = 40)  
  public String getFirstName()
  {
    return super.getFirstName();
  }

  public void setFirstName(String firstName)
  {
    super.setFirstName(firstName);
  }

  @Column(name="LAST_NAME", nullable = false, length = 40)  
  public String getLastName()
  {
    return super.getLastName();
  }

  public void setLastName(String lastName)
  {
    super.setLastName(lastName);
  }

  @Column(name="ADDRESS_STREET", nullable = false, length = 80)
  public String getStreet()
  {
    return super.getStreet();
  }
  
  @Column(name="ADDRESS_CITY", nullable = false, length = 80)  
  public String getCity()
  {
    return super.getCity();
  }

  @Column(name="ADDRESS_STATE", nullable = false, length = 40)
  public String getState()
  {
    return super.getState();
  }

  @Column(name="ADDRESS_ZIP", nullable = false, length = 8)
  public String getZip()
  {
    return super.getZip();
  }

  @Column(name="ADDRESS_COUNTRY", nullable = false, length = 40)
  public String getCountry()
  {
    return super.getCountry();
  }
}
