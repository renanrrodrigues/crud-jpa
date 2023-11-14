package app.query;

import app.Factory.ManagerFactory;
import app.model.Departamento;
import app.model.Funcionario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class AlocarFuncDp {
    public static void main(String[] args) {
        EntityManager entityManager = ManagerFactory.getEntityManager();


        //alocarFuncDp(entityManager);
        alocarFuncDpJPQL(entityManager);


        ManagerFactory.emClose();
        ManagerFactory.emfClose();
    }

    public static void alocarFuncDp(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        Funcionario funcionario = entityManager.find(Funcionario.class, 1L);
        Departamento departamento = entityManager.find(Departamento.class, 1L);

        funcionario.setDepartamento(departamento);

        entityManager.getTransaction().commit();

    }

    public static void alocarFuncDpJPQL(EntityManager entityManager) {
        entityManager.getTransaction().begin();


        Query query = entityManager.createQuery("UPDATE Funcionario f SET f.departamento = " +
                "(SELECT d FROM Departamento d " +
                "WHERE d.id = :departamentoId) " +
                "WHERE f.id = :funcionarioId");

        query.setParameter("departamentoId", 9L);
        query.setParameter("funcionarioId", 11L);

        int updatedEntities = query.executeUpdate();

        if (updatedEntities > 0) {
            System.out.println("Associação realizada com sucesso!");
        } else {
            System.out.println("Funcionário ou departamento não encontrado.");
        }

        entityManager.getTransaction().commit();


    }
}
