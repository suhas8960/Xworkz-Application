package com.xworkz.trainercustomer.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.trainercustomer.entity.CustomerEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CustomerRepositoryImpl implements CustomerRepository {
	@Autowired
	private EntityManagerFactory factory;

	@Override
	public Boolean saveCustomer(CustomerEntity entitys) {
		log.info("save method created in CustomerRepositoryImpl..." + entitys);
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(entitys);
			transaction.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return true;
	}
	
	@Override
	public Long findByMail(String email) {
		log.info("findByMail is Running...");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findBy");
			query.setParameter("mail", email);
			Object result = query.getSingleResult();
			log.info("result in DAO :" + result);
				Long emaill = (Long) result;
				return emaill;

		} finally {
			manager.close();
		}
	}
	
	@Override
	public List<CustomerEntity> findByAll() {
		log.info("findByAll is running in repository..");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByAlls");
			List<CustomerEntity> list = query.getResultList();
			return list;

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}
	
	@Override
	public Boolean update(CustomerEntity entity) {
		EntityManager manager =null;
		try {
			manager= this.factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.merge(entity);
			transaction.commit();
			return true;
		} finally {
			manager.close();
		}
	}
	
	@Override
	public void deleteById(Integer coustomerId) {
		EntityManager manager =null;
		try {
			manager= this.factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			CustomerEntity entity=manager.find(CustomerEntity.class, coustomerId);
			manager.remove(entity);
			transaction.commit();
		} finally {
			manager.close();
		}
		
	}
	
	
	@Override
	public CustomerEntity findById(Integer coustomerId) {
		log.info("findById is Running...");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findById");
			query.setParameter("ids", coustomerId);
			Object result = query.getSingleResult();
			log.info("result " +result);
			if (result != null) {
				CustomerEntity entity = (CustomerEntity) result;
				return entity;
			}

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

}
