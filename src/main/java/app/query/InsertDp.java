package app.query;

import app.Factory.ManagerFactory;
import app.model.Departamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class InsertDp {
    public static void main(String[] args) {
        EntityManager entityManager = ManagerFactory.getEntityManager();


        insertDp1(entityManager);


        ManagerFactory.emClose();
        ManagerFactory.emfClose();

    }

    public static void insertDp1(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Departamento departamento = new Departamento();
        departamento.setNome("Departamento de TI");

        entityManager.persist(departamento);
        entityManager.getTransaction().commit();
    }

}
