package app.service;

import app.model.Pessoa;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PessoaService {

    public static final String PERSISTENCE_UNIT_NAME = "crud-jpa";

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager manager = factory.createEntityManager();

    public void create(Pessoa entity) {
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
    }

    public void update(Pessoa entity) {
        manager.getTransaction().begin();
        manager.merge(entity);
        manager.getTransaction().commit();
    }

    public void delete(Pessoa entity) {
        manager.getTransaction().begin();
        manager.remove(entity);
        manager.getTransaction().commit();
    }

    public Pessoa find(Pessoa entity) {
        Pessoa pessoa = manager.find(Pessoa.class, entity.getId());
        return pessoa;
    }

    public List<Pessoa> findAll() {
        List<Pessoa> pessoas = manager.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
        return pessoas;
    }

    public void close() {
        manager.close();
        factory.close();
    }

}
