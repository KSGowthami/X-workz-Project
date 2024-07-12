package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.EmployeesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Optional;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<EmployeesDTO> findByEmail(String email) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT e FROM EmployeesDTO e WHERE e.email = :email");
            query.setParameter("email", email);
            EmployeesDTO employeesDTO = (EmployeesDTO) query.getSingleResult();
            return Optional.ofNullable(employeesDTO);
        } catch (NoResultException e) {
            // Handle no result found
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean save(EmployeesDTO employeesDTO) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(employeesDTO);
            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
            return false;
        } finally {
            entityManager.close();
        }
    }
    @Override
    public void updateOtp(String email, String otp) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery("UPDATE EmployeesDTO e SET e.otp = :otp WHERE e.email = :email");
            query.setParameter("otp", otp);
            query.setParameter("email", email);
            query.executeUpdate();
            entityTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
    }

}
