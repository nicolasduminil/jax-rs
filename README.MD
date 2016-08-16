This project includes some of the most classical JAX-RS 2.0 use cases.
They use the RESTeasy implementation comming with JBoss EAP 6.4
To build the projects:

  cd $WORKSPACE/customer-management-master
  mvn clean install
  
This will compile and build all the projects, will deploy the different WARs and EARs on JBoss EAP 6.4, will run all the unit tests and, when
finished, all the WARs andEArs will be undeployed. The JPA layer uses an Oracle database which will be dynamically created, when the associated EAR is deployed,
and droped when the EAR is undeployed (during the "clean" phase); Another databse server, like MySQL or MariaDB, etc. may be used bu slightly modifying
the persistence.xml file and the JBoss EAP standalone/domain.xml file.