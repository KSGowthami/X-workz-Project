package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class SignUpRepoImplementation implements SignUpRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(SignUpDto signUpDto) {
        System.out.println("running Signup Services");

        EntityManager entityManager=this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();

        try{
            entityTransaction.begin();
            entityManager.persist(signUpDto);
            entityTransaction.commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            entityTransaction.rollback();
            return false;
        }finally {
            entityManager.close();
        }
        return true;
    }

    @Override
    public long findEmail(String email) {
       EntityManager entityManager=this.entityManagerFactory.createEntityManager();

       try{
           Query query=entityManager.createNamedQuery("searchEmail");
           query.setParameter("email",email);
            return (long) query.getSingleResult();
       }catch (PersistenceException e){
           e.printStackTrace();
           return 0;
       }finally {
           entityManager.close();
       }

    }

    @Override
    public long findPhoneNumber(Long phoneNo) {

        EntityManager entityManager=this.entityManagerFactory.createEntityManager();

        try{
            Query query= entityManager.createNamedQuery("searchPhone");
            query.setParameter("phoneNo",phoneNo);
            return (long) query.getSingleResult();
        }catch (PersistenceException e){
            e.printStackTrace();
            return 0;
        }finally {
            entityManager.close();
        }

    }
}
