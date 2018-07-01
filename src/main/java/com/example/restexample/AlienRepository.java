package com.example.restexample;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AlienRepository {

	/**
	 * Returning the session for Database connectivity.
	 * 
	 * @return session
	 */
	Session database() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class);
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		return session;
	}

	// Method to show all the aliens.
	public List<Alien> getAliens() {
		Session session = database();

		session.beginTransaction();

		Query query = session.createQuery("FROM Alien");
		@SuppressWarnings("unchecked")
		List<Alien> aliens = query.list();

		if (aliens.isEmpty()) {
			System.out.println("Sorry! No Aliens in the Store.");
		} else {
			for (Alien alien : aliens) {
				System.out.println(alien);
			}
			



		}
		session.getTransaction().commit();
		session.close();

		return aliens;
	}

	// Method to show a particular aliens
	public List<Alien> getAlien(int id) {
		Session session = database();

		session.beginTransaction();

		Query query = session.createQuery("FROM Alien WHERE id = :id");
		query.setParameter("id", id);

		@SuppressWarnings("unchecked")
		List<Alien> aliens = query.list();

		session.getTransaction().commit();
		session.close();

		return aliens;

	}
    
	
	//Method to add Aliens in the DB
    public void create( Alien alien){
    	
    	Session session = database();
    	
    	session.beginTransaction();
        
        alien.setName(alien.getName());
        alien.setPoints(alien.getPoints());
        
        session.save(alien);
        
        session.getTransaction().commit();
        session.close();
        
    }



	public int updateEmployee(int id, Alien alien) {
		
		if (id <= 0)
			return 0;
		Session session = database();
		session.beginTransaction();
		
		String hql = "update Alien set name = :name, points=:points where id = :id";
		Query query = session.createQuery(hql);
		
		query.setInteger("id", id);
		query.setString("name", alien.getName());
		query.setInteger("points", alien.getPoints());
		int rowCount = query.executeUpdate();
		
		System.out.println("Rows affected: " + rowCount);
		session.getTransaction().commit();
		session.close();
		
		return rowCount;
	}

    public int delete(int id) {
    	
        Session session = database();
        session.beginTransaction();
        
        String hql = "delete from Alien where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        session.getTransaction().commit();
        
        return rowCount;
    }	

}






