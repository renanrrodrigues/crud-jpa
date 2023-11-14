package app.query;

import app.Factory.ManagerFactory;
import app.model.Funcionario;
import jakarta.persistence.EntityManager;

public class InsertFun {
    public static void main(String[] args) {
        EntityManager entityManager = ManagerFactory.getEntityManager();


        insertFun(entityManager);


        ManagerFactory.emClose();
        ManagerFactory.emfClose();
    }

    public static void insertFun(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("João");


        entityManager.persist(funcionario);
        entityManager.getTransaction().commit();
    }
}
