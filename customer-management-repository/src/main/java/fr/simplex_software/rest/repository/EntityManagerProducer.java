package fr.simplex_software.rest.repository;

import javax.enterprise.inject.*;
import javax.persistence.*;

public class EntityManagerProducer
{
  @PersistenceContext
  private EntityManager em;

  @Produces
  public EntityManager createEntityManager()
  {
    return em;
  }
}
