package app.service;

import app.model.Funcionario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class GenericService {

    public static final String PERSISTENCE_UNIT_NAME = "crud-jpa";

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager manager = factory.createEntityManager();

    public void create(Funcionario entity) throws Exception {
        try {
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro ao criar a pessoa: " + e.getMessage());
        }
    }

    public void update(Funcionario entity) throws Exception {
        try {
            manager.getTransaction().begin();
            manager.merge(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar a pessoa: " + e.getMessage());
        }
    }

    public void delete(Funcionario entity) throws Exception {
        try {
            manager.getTransaction().begin();
            manager.remove(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro ao excluir a pessoa: " + e.getMessage());
        }
    }

    public Funcionario find(Funcionario entity) throws Exception {
        try {
            Funcionario funcionario = manager.find(Funcionario.class, entity.getId());
            return funcionario;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar a pessoa: " + e.getMessage());
        }
    }

    public List<Funcionario> findAll() throws Exception {
        try {
            List<Funcionario> funcionarios = manager.createQuery("SELECT p FROM Funcionario p", Funcionario.class).getResultList();
            return funcionarios;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar todas as pessoas: " + e.getMessage());
        }
    }

    public void close() {
        manager.close();
        factory.close();
    }
}