package fr.simplex_software.rest.repository;

import java.math.*;
import java.util.*;

import javax.ejb.*;
import javax.inject.*;
import javax.persistence.metamodel.*;

import fr.simplex_software.rest.entities.*;

@Stateless
public class CustomerFacade
{
  @Inject
  private CustomerRepository customerRepository;

  public void attachAndRemove(CustomerEntity customerEntity)
  {
    customerRepository.attachAndRemove(customerEntity);
  }

  public Long count()
  {
    return customerRepository.count();
  }

  public Long count(CustomerEntity customerEntity, @SuppressWarnings("unchecked") SingularAttribute<CustomerEntity, ?>... attribute)
  {
    return customerRepository.count(customerEntity, attribute);
  }

  public Long countLike(CustomerEntity customerEntity, @SuppressWarnings("unchecked") SingularAttribute<CustomerEntity, ?>... attribute)
  {
    return customerRepository.countLike(customerEntity, attribute);
  }

  public List<CustomerEntity> findAll()
  {
    return customerRepository.findAll();
  }

  public List<CustomerEntity> findAll(int first, int last)
  {
    return customerRepository.findAll(first, last);
  }

  public CustomerEntity findBy(BigInteger customerEntity)
  {
    return customerRepository.findBy(customerEntity);
  }

  public List<CustomerEntity> findBy(CustomerEntity customerEntity, int first, int last, @SuppressWarnings("unchecked") SingularAttribute<CustomerEntity, ?>... attribute)
  {
    return customerRepository.findBy(customerEntity, first, last, attribute);
  }

  public List<CustomerEntity> findBy(CustomerEntity customerEntity, @SuppressWarnings("unchecked") SingularAttribute<CustomerEntity, ?>... attribute)
  {
    return customerRepository.findBy(customerEntity, attribute);
  }

  public List<CustomerEntity> findByLike(CustomerEntity customerEntity, int first, int last, @SuppressWarnings("unchecked") SingularAttribute<CustomerEntity, ?>... attribute)
  {
    return customerRepository.findByLike(customerEntity, first, last, attribute);
  }

  public List<CustomerEntity> findByLike(CustomerEntity customerEntity, @SuppressWarnings("unchecked") SingularAttribute<CustomerEntity, ?>... attribute)
  {
    return customerRepository.findByLike(customerEntity, attribute);
  }

  public void flush()
  {
    customerRepository.flush();
  }

  public BigInteger getPrimaryKey(CustomerEntity customerEntity)
  {
    return customerRepository.getPrimaryKey(customerEntity);
  }

  public void refresh(CustomerEntity customerEntity)
  {
    customerRepository.refresh(customerEntity);
  }

  public void remove(CustomerEntity customerEntity)
  {
    customerRepository.remove(customerEntity);
  }

  public void removeAndFlush(CustomerEntity customerEntity)
  {
    customerRepository.removeAndFlush(customerEntity);
  }

  public CustomerEntity save(CustomerEntity customerEntity)
  {
    return customerRepository.save(customerEntity);
  }

  public CustomerEntity saveAndFlush(CustomerEntity customerEntity)
  {
    return customerRepository.saveAndFlush(customerEntity);
  }

  public CustomerEntity saveAndFlushAndRefresh(CustomerEntity customerEntity)
  {
    return customerRepository.saveAndFlushAndRefresh(customerEntity);
  }

  public CustomerRepository getCustomerRepository()
  {
    return customerRepository;
  }
  
  public void findAndRemove (BigInteger customerId)
  {
    customerRepository.removeAndFlush(customerRepository.findBy(customerId));
  }
}
