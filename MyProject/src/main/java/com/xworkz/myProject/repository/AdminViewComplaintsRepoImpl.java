    package com.xworkz.myProject.repository;

    import com.xworkz.myProject.dto.ComplaintsDTO;
    import com.xworkz.myProject.dto.ComplaintsHistoryDTO;
    import com.xworkz.myProject.dto.DepartmentDTO;
    import com.xworkz.myProject.dto.SignUpDto;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Repository;

    import javax.persistence.EntityManager;
    import javax.persistence.EntityManagerFactory;
    import javax.persistence.TypedQuery;
    import java.util.List;

    @Repository
    public class AdminViewComplaintsRepoImpl implements AdminViewComplaintsRepo {

        @Autowired
        private EntityManagerFactory entityManagerFactory;

        @Override
        public List<ComplaintsDTO> findByType(String type) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                TypedQuery<ComplaintsDTO> query = entityManager.createQuery(
                        "SELECT c FROM ComplaintsDTO c WHERE c.type = :type", ComplaintsDTO.class);
                query.setParameter("type", type);
                return query.getResultList();
            } finally {
                entityManager.close();
            }
        }


        @Override
        public List<ComplaintsDTO> findByArea(String area) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                System.out.println("Querying database for area: " + area);
                TypedQuery<ComplaintsDTO> query = entityManager.createQuery(
                        "SELECT c FROM ComplaintsDTO c WHERE c.area = :area", ComplaintsDTO.class);
                query.setParameter("area", area);
                List<ComplaintsDTO> results = query.getResultList();
                System.out.println("Query results: " + results);
                return results;
            } finally {
                entityManager.close();
            }
        }

        @Override
        public List<ComplaintsDTO> findByTypeAndArea(String type, String area) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                TypedQuery<ComplaintsDTO> query = entityManager.createQuery(
                        "SELECT c FROM ComplaintsDTO c WHERE c.type = :type AND c.area = :area", ComplaintsDTO.class);
                query.setParameter("type", type);
                query.setParameter("area", area);
                return query.getResultList();
            } finally {
                entityManager.close();
            }
        }

        @Override
        public List<ComplaintsDTO> getAllComplaints() {
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            try {
                TypedQuery<ComplaintsDTO> query = entityManager.createQuery("SELECT c FROM ComplaintsDTO c ORDER BY createdAt DESC ", ComplaintsDTO.class);
                return query.getResultList();
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
        public ComplaintsDTO findById(int id) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                return entityManager.find(ComplaintsDTO.class, id);
            } finally {
                entityManager.close();
            }
        }
        @Override
        public void save(ComplaintsDTO complaint) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(complaint);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
        }
        @Override
        public void saveHistory(ComplaintsHistoryDTO complaintsHistoryDTO) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(complaintsHistoryDTO);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
        }
    }
