package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.DepartmentAdminDTO;
import com.xworkz.myProject.dto.DepartmentDTO;
import com.xworkz.myProject.dto.EmployeesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class DepartmentAdminRepoImpl implements DepartmentAdminRepo {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<DepartmentAdminDTO> findByEmail(String email) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findDepartmentAdminMail");
            query.setParameter("email", email);
            DepartmentAdminDTO departmentAdminDTO = (DepartmentAdminDTO) query.getSingleResult();
            return Optional.ofNullable(departmentAdminDTO);
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Optional.empty();
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
    public List<DepartmentDTO> getAllDepartments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<DepartmentDTO> query = entityManager.createQuery("SELECT d FROM DepartmentDTO d", DepartmentDTO.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public long findEmployeeEmail(String email) {

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        try {
            Query query = entityManager.createNamedQuery("searchEmployeeMail");
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
    public long findEmployeePhone(Long phoneNo) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("searchEmployeePhone");
            query.setParameter("phoneNo", phoneNo);

            return (long) query.getSingleResult();
        } catch (NoResultException e) {
            return 0;
        } finally {
            entityManager.close();
        }
    }



}
