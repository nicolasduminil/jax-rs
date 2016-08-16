package fr.simplex_software.rest.repository;

import java.math.*;

import org.apache.deltaspike.data.api.*;

import fr.simplex_software.rest.entities.*;

@Repository
public interface CustomerRepository extends EntityRepository<CustomerEntity, BigInteger>
{
}
