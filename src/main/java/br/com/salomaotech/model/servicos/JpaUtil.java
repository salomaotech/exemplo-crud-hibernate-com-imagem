package br.com.salomaotech.model.servicos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static final EntityManagerFactory factory;

    static {

        factory = Persistence.createEntityManagerFactory("Conexao");

    }

    public EntityManager manager() {

        return factory.createEntityManager();

    }

    public void close() {

        factory.close();

    }

}
