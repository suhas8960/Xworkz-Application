package com.xworkz.butterfly.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.butterfly.model.LifeInsurenceEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class LifeInsurenceDAOImpl implements LifeInsurenceDAO {

	@Autowired
	private EntityManagerFactory factory;

	@Override
	public Boolean save(LifeInsurenceEntity entity) {
		log.info("save method created in LifeInsurenceDAOImpl..." + entity);
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(entity);
			transaction.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public LifeInsurenceEntity findById(Integer id) {
		log.info("findById is Running...");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findById");
			query.setParameter("id", id);
			Object result = query.getSingleResult();
			log.info("result " +result);
			if (result != null) {
				LifeInsurenceEntity entity = (LifeInsurenceEntity) result;
				return entity;
			}

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public List<LifeInsurenceEntity> findByName(String name) {
		log.info("findByName is Running...");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByName");
			query.setParameter("name", name);
			List<LifeInsurenceEntity> list = query.getResultList();
			log.info("list "+list);
			if (list != null) {
				List<LifeInsurenceEntity> entity = (List<LifeInsurenceEntity>) list;
				return entity;
			}

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public LifeInsurenceEntity findByEmail(String email) {
		log.info("findByEmail is Running...");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByEmail");
			query.setParameter("email", email);
			Object result = query.getSingleResult();
			log.info("result in DAO :" + result);
			if (result != null) {
				LifeInsurenceEntity entity = (LifeInsurenceEntity) result;
				return entity;
			}

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public LifeInsurenceEntity findByPasswordAndEmail(String email, String password) {
		log.info("Running findByPasswordAndEmail DAO...");
		EntityManager manager = null;

		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByPasswordAndEmail");
			query.setParameter("pass", password);
			query.setParameter("ema", email);
			log.info("query for dao find by passwordandemail " + query);
			log.info("query for dao find by passwordandemail " + email + password);

			Object result = query.getSingleResult();
			if (result != null) {
				log.info("Data is there");
				return (LifeInsurenceEntity) result;
			} else {
				log.info("Data is not there");
			}
		}

		catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		finally {
			manager.close();
		}
		return new LifeInsurenceEntity();

	}

	@Override
	public List<LifeInsurenceEntity> findByAll() {
		log.info("findByAll is running in repository..");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByAll");
			List<LifeInsurenceEntity> list = query.getResultList();
			return list;

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
	}

	@Override
	public Boolean updateStatusByEmail(String status, String email) {
		log.info("Running updateStatusByEmail DAO....");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateStatusByEmail");
			query.setParameter("status", status);
			query.setParameter("email", email);
			query.executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return true;
	}

	@Override
	public Boolean updateCountByEmail(Integer count, String email) {
		log.info("Running updateCountByEmail DAO....");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateCountByEmail");
			query.setParameter("count", count);
			query.setParameter("emai", email);
			query.executeUpdate();
			transaction.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}

		return true;
	}

	@Override
	public Boolean updateOtpByEmail(Integer otp, String email) {
		log.info("updateOtpByEmail in DAO....");
		EntityManager manager = null;
		try {
			manager = this.factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateOtpByEmail");
			query.setParameter("otp", otp);
			query.setParameter("emails", email);
			int executeUpdate = query.executeUpdate();
			log.info("execute "+executeUpdate);
			transaction.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Boolean resetPasswordByEmail(String password, String email) {
		log.info("resetPasswordByEmail in DAO...");
		EntityManager manager = null;
		try {
			manager = this.factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("resetPasswordByEmail");
			query.setParameter("passw", password);
			query.setParameter("emil", email);
			query.executeUpdate();
			transaction.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Boolean updatePasswordByEmail(String password, String email) {
		log.info("updatePasswordByEmail in DAO..");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updatePasswordByEmail");
			query.setParameter("passs", password);
			query.setParameter("emmai", email);
			query.executeUpdate();
			transaction.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Boolean updateDetails(LifeInsurenceEntity entity) {
		log.info("updateDetails in DAO.....");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			LifeInsurenceEntity merge = manager.merge(entity);
			log.info("Merge"+merge);
			transaction.commit();
			transaction.rollback();
			return true;
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (PersistenceException e) {
			e.printStackTrace();

		} finally {
			manager.close();
		}
		return true;
	}

}
