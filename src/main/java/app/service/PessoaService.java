package app.service;

import app.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PessoaService {

    public static final String PERSISTENCE_UNIT_NAME = "crud-jpa";

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager manager = factory.createEntityManager();

    public void create(Pessoa entity) throws Exception {
        try {
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro ao criar a pessoa: " + e.getMessage());
        }
    }

    public void update(Pessoa entity) throws Exception {
        try {
            manager.getTransaction().begin();
            manager.merge(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar a pessoa: " + e.getMessage());
        }
    }

    public void delete(Pessoa entity) throws Exception {
        try {
            manager.getTransaction().begin();
            manager.remove(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro ao excluir a pessoa: " + e.getMessage());
        }
    }

    public Pessoa find(Pessoa entity) throws Exception {
        try {
            Pessoa pessoa = manager.find(Pessoa.class, entity.getId());
            return pessoa;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar a pessoa: " + e.getMessage());
        }
    }

    public List<Pessoa> findAll() throws Exception {
        try {
            List<Pessoa> pessoas = manager.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
            return pessoas;
        } catch (Exception e) {
            throw new Exception("Erro ao buscar todas as pessoas: " + e.getMessage());
        }
    }

    public void close() {
        manager.close();
        factory.close();
    }
}