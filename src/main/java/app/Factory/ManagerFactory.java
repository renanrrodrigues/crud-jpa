package app.Factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ManagerFactory {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static final String PERSISTENCE_UNIT_NAME = "crud-jpa";

    static { //  toda vez que a classe for carregada, o bloco static será executado
        // isso ocorre quando iniciamos a aplicação
        try{
            emf = Persistence
                    .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            // logar -> log4j
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        try {
            em = emf.createEntityManager();
            return em;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void emfClose() {
        emf.close();
    }

    public static void emClose() {
        em.close();
    }
}
