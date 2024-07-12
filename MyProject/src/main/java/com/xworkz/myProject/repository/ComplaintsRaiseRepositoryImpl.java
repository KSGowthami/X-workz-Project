package com.xworkz.myProject.repository;

import com.xworkz.myProject.dto.ComplaintsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ComplaintsRaiseRepositoryImpl implements ComplaintsRaiseRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(ComplaintsDTO complaintsDTO) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean isSaved = false;
        complaintsDTO.setStatus("Active");

        try {
            entityTransaction.begin();
            entityManager.persist(complaintsDTO);
            entityTransaction.commit();
            isSaved = true;
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return isSaved;
    }


    @Override
    public List<ComplaintsDTO> getAllComplaints(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ComplaintsDTO> query = entityManager.createQuery("SELECT c FROM ComplaintsDTO c WHERE c.userId = :userId", ComplaintsDTO.class);
            query.setParameter("userId", userId);
            List<ComplaintsDTO> complaintsList = query.getResultList();
            System.out.println("User complaints: " + complaintsList);
            return complaintsList;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<ComplaintsDTO> findById(int complaintId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            ComplaintsDTO complaint = entityManager.find(ComplaintsDTO.class, complaintId);
            return Optional.ofNullable(complaint);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean updateComplaint(ComplaintsDTO complaintsDTO) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        boolean isUpdated = false;

        try {
            entityTransaction.begin();
            ComplaintsDTO existingComplaint = entityManager.find(ComplaintsDTO.class, complaintsDTO.getId());
            if (existingComplaint != null) {
                existingComplaint.setStatus(complaintsDTO.getStatus());
                existingComplaint.setDepartmentId(complaintsDTO.getDepartmentId());
                existingComplaint.setModifiedBy(complaintsDTO.getModifiedBy());
                existingComplaint.setModifiedAt(complaintsDTO.getModifiedAt());
                entityManager.merge(existingComplaint);
                entityTransaction.commit();
                isUpdated = true;
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return isUpdated;
    }

}
