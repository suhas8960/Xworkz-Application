package com.xworkz.trainer.repository;

import java.time.LocalTime;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.trainer.entity.TrainerEntity;

import lombok.extern.slf4j.Slf4j;



@Repository
@Slf4j
public class TrainerRepositoryImpl implements TrainerRepository {

	@Autowired
	private EntityManagerFactory factory;

	@Override
	public boolean save(TrainerEntity entity) {
		System.out.println("entity " + entity);
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
	public Long findByEmail(String email) {
		log.info("findByEmail is Running...");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByEmail");
			query.setParameter("emai", email);
			Object result = query.getSingleResult();
			log.info("result in DAO :" + result);
			Long emaill = (Long) result;
			return emaill;

		} finally {
			manager.close();
		}
	}

	@Override
	public Boolean updateOtpAndOtpTimeByEmail(Integer otp, LocalTime otpTime, String email) {
		log.info("updateOtpByEmail in DAO....");
		EntityManager manager = null;
		try {
			manager = this.factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateOtpAndOtpTimeByEmail");
			query.setParameter("otp", otp);
			query.setParameter("otpTime", otpTime);
			query.setParameter("emails", email);
			int executeUpdate = query.executeUpdate();
			log.info("execute " + executeUpdate);
			transaction.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return true;
	}

	@Override
	public Optional<TrainerEntity> findByEmails(String email) {
		log.info("findByEmails is Running...");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			Query query = manager.createNamedQuery("findByEmails");
			query.setParameter("gmails", email);
			Object result = query.getSingleResult();
			log.info("result in DAO :" + result);
			TrainerEntity entity = (TrainerEntity) result;
			return Optional.ofNullable(entity);
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			manager.close();
		}
		return null;
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
			query.setParameter("gmail", email);
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
	public Boolean updateStatusByEmail(String status, String email) {
		log.info("Running updateStatusByEmail DAO....");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updateStatusByEmail");
			query.setParameter("status", status);
			query.setParameter("mail", email);
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
	public Boolean updatePasswordAndOtpTimeByEmail(String password, LocalTime otpTime, String email) {
		log.info("updatePasswordByEmail in DAO..");
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("updatePasswordByEmail");
			query.setParameter("passs", password);
			query.setParameter("otpT", otpTime);
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
	public Boolean update(TrainerEntity entity) {
		EntityManager em = this.factory.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.merge(entity);
			et.commit();
			return true;
		} finally {
			em.close();
		}
	}

}
