package com.customerTracker.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.customerTracker.entities.Customer;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<Customer> getCustomer() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by last_name",Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
		
	}//Close getCustomer method.

}//Close CustomerDAOImplementation class.
