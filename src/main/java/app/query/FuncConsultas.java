package app.query;

import app.Factory.ManagerFactory;
import app.model.Funcionario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Arrays;
import java.util.List;

public class FuncConsultas {
    public static void main(String[] args) {
        EntityManager entityManager = ManagerFactory.getEntityManager();

        //buscarFuncionario(entityManager);
        //buscarFuncionarioByDP(entityManager);
        buscarFuncionarioByDPMult(entityManager);

        ManagerFactory.emClose();
        ManagerFactory.emfClose();
    }

    public static void buscarFuncionario(EntityManager entityManager) {

        Funcionario funcionario = entityManager.find(Funcionario.class, 1L);
        System.out.println(funcionario.getNome());
    }

    public static void buscarFuncionarioByDP(EntityManager entityManager) {
        // Consulta JPQL para buscar funcionários por departamento
        Query query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.departamento.id = :departamentoId");
        query.setParameter("departamentoId", 1L);

        List<Funcionario> funcionarios = query.getResultList();

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }
    }

    public static void buscarFuncionarioByDPMult(EntityManager entityManager) {

        Long departamentoId1 = 6L; // Substitua pelo ID real do primeiro departamento
        Long departamentoId2 = 1L;

        // Consulta JPQL para buscar funcionários por departamento
        Query query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.departamento.id IN (:departamentoIds)");
        query.setParameter("departamentoIds", Arrays.asList(departamentoId1, departamentoId2));

        List<Funcionario> funcionarios = query.getResultList();

        if (!funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println("Funcionário: " + funcionario.getNome() + " - Departamento: " + funcionario.getDepartamento().getNome());
            }
        } else {
            System.out.println("Nenhum funcionário encontrado para os departamentos com IDs: " + departamentoId1 + " e " + departamentoId2);
        }
    }
}
