package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.SignUpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
@Slf4j
public class SignUpRepoImplementation implements SignUpRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(SignUpDto signUpDto) {

       log.info("Save Method");
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(signUpDto);
            entityTransaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public long findEmail(String email) {

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createNamedQuery("searchEmail");
            query.setParameter("email", email);
            return (long) query.getSingleResult();

        } catch (PersistenceException e) {
            e.printStackTrace();
            return 0;
        } finally {
            entityManager.close();
        }

    }

    @Override
    public long findByPhone(Long phoneNo) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("searchPhoneNo");
            query.setParameter("phoneNo", phoneNo);

            return (long) query.getSingleResult();
        } catch (NoResultException e) {
            return 0;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<SignUpDto> findById(int id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            SignUpDto signUpDto = entityManager.find(SignUpDto.class, id);
            return Optional.ofNullable(signUpDto);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    //find by email for count
    @Override
    public Optional<SignUpDto> findByEmail(String email) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            TypedQuery<SignUpDto> query = entityManager.createQuery("SELECT s FROM SignUpDto s WHERE s.email = :email", SignUpDto.class);
            query.setParameter("email", email);
            SignUpDto result = query.getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public SignUpDto merge(SignUpDto signUpDto) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        log.info("repo password reset");
        try {
            entityTransaction.begin();
            SignUpDto mergedSignUpDto = entityManager.merge(signUpDto);

            log.info("merged DTO" + mergedSignUpDto);
            entityTransaction.commit();
            return mergedSignUpDto;
        } catch (Exception e) {
            log.info("Error " + e.getMessage());
            e.printStackTrace();
            entityTransaction.rollback();
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updatePassword(int id, String newPassword) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        log.info("Update password");
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery("UPDATE SignUpDto s SET s.password = :newPassword WHERE s.id = :id");
            query.setParameter("newPassword", newPassword);
            query.setParameter("id", id);
            query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void resetLoginCount(int id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        log.info("Reset login count");
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery("UPDATE SignUpDto s SET s.loginCount = 0 WHERE s.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public SignUpDto updateUser(SignUpDto signUpDto) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        log.info("Update user");
        try {
            entityTransaction.begin();
            entityManager.merge(signUpDto);
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return signUpDto;
    }
}
