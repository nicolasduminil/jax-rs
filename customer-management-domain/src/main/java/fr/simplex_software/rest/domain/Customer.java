package fr.simplex_software.rest.domain;

import java.io.*;

import javax.xml.bind.annotation.*;

@XmlTransient
public class Customer implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String firstName;
  private String lastName;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String country;

  public Customer()
  {
  }

  public Customer(String firstName, String lastName, String street, String city, String state, String zip, String country)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.country = country;
  }
  
  public String getFirstName()
  {
    return firstName;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getStreet()
  {
    return street;
  }

  public void setStreet(String street)
  {
    this.street = street;
  }

  public String getCity()
  {
    return city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getState()
  {
    return state;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  public String getZip()
  {
    return zip;
  }

  public void setZip(String zip)
  {
    this.zip = zip;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String toString()
  {
    return "*** Customer:\n\t-->firstName: " + this.firstName + "\n\t-->lastName: " + this.getLastName() + "\n\t-->city: " + this.city
        + "\n\t-->country: " + this.country + "\n\t-->state: " + this.state + "\n\t-->street: " + this.street + "\n\t-->zip: " + this.zip;
  }
}
