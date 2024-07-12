package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.FileUploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class FileUploadRepositoryImpl implements FileUploadRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void saveImageDetails(FileUploadDTO fileUploadDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(fileUploadDTO);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<FileUploadDTO> findByUserId(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<FileUploadDTO> query = entityManager.createQuery(
                    "SELECT i FROM FileUploadDTO i WHERE i.user.id = :userId", FileUploadDTO.class);
            query.setParameter("userId", userId);
            return query.getResultList().stream().findFirst();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateImageDetails(FileUploadDTO fileUploadDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(fileUploadDTO);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void setAllImagesInactiveForUser(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery(
                    "UPDATE FileUploadDTO SET status = 'Inactive' WHERE user.id = :userId");
            query.setParameter("userId", userId);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
