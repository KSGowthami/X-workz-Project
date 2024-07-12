package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.AdminDTO;
import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepositoryImpl implements AdminRepository{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<AdminDTO> findByEmail(String email) {

        EntityManager entityManager= this.entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findAdminMail");
            query.setParameter("email",email);
            AdminDTO adminDTO= (AdminDTO) query.getSingleResult();
            return Optional.ofNullable(adminDTO);

        }catch (PersistenceException e){
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<SignUpDto> getAllUsers() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try{
            TypedQuery<SignUpDto> query= entityManager.createQuery("select s from SignUpDto s",SignUpDto.class);
            System.out.println("User details: "+query);
            return query.getResultList();
        }
        finally {
            entityManager.close();
        }
    }
    public void updateAdmin(AdminDTO adminDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(adminDTO);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public int getDepartmentIdByName(String departmentName) {
      EntityManager entityManager=  this.entityManagerFactory.createEntityManager();
      try{
          TypedQuery<Integer> query=entityManager.createQuery("select d.id from DepartmentDTO d where d.departmentName=:departmentName", Integer.class);
          query.setParameter("departmentName",departmentName);
          return query.getSingleResult();
      }
       finally {
          entityManager.close();
      }
    }
}
