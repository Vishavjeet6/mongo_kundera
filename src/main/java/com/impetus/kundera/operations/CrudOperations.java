package com.impetus.kundera.operations;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.impetus.kundera.entities.Person;


public class CrudOperations {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mongo_pu");
	    EntityManager em = emf.createEntityManager();
	    Person person = new Person();
	 
//	   inserting 
	    System.out.println("***************");
	    System.out.println("Inserting");	
    	person.setPersonId("1");
        person.setPersonName("John Smith");
        person.setAge(32);
        em.persist(person);
        em.clear();   //Clear cache before finding record	    
        System.out.println("Inserting Done");
	    System.out.println("***************");	    
	    System.out.println("***************");
	    System.out.println("Insert Testing");
	    Person personFound = em.find(Person.class, "1");
	    if(personFound != null) {
	    	System.out.println(personFound.getAge());
		    System.out.println(personFound.getPersonName());
		    System.out.println("Insert Testing Success");
	    }
	    else {
	    	System.out.println("Insert Testing Fail");
	    }
	    System.out.println("***************");
	    
//  updating	    
	    System.out.println("***************");
	    System.out.println("Updating");
	    personFound.setAge(33);
	    em.merge(personFound);
        System.out.println("Updating Done");
	    System.out.println("***************");
	    
	    System.out.println("***************");
	    System.out.println("Update Testing");
	    Person updatedPerson = em.find(Person.class, "1");
	    if(updatedPerson != null) {
	    	System.out.println(updatedPerson.getAge());
		    System.out.println(updatedPerson.getPersonName());
		    System.out.println("Update Testing Success");
	    }
	    else {
	    	System.out.println("Update Testing Fail");
	    }
	    System.out.println("***************");	
	    
//	    deleting
	    System.out.println("***************");
	    System.out.println("Deleting");
	    em.remove(personFound);
        System.out.println("Deleting Done");
	    System.out.println("***************");
	    
	    System.out.println("***************");
	    System.out.println("Delete Testing");
	    if(em.find(Person.class, "1") != null) {
	    	System.out.println("Delete Testing Fail");
	    }else {
	    	System.out.println("Delete Testing Success");
	    }
	    System.out.println("***************");	
	    
	    em.close();
	    emf.close();
	    
	}
}
