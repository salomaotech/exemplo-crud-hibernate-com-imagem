package br.com.salomaotech.model.repositorios;

import br.com.salomaotech.model.entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ClienteRepository {

    private final EntityManager manager;

    public ClienteRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void persiste(Cliente cliente) {

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(cliente);
        tx.commit();
        manager.close();

    }

    public void merge(Cliente cliente) {

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.merge(cliente);
        tx.commit();
        manager.close();

    }

    public void remove(long id) {

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.remove(manager.find(Cliente.class, id));
        tx.commit();
        manager.close();

    }

    public List findAll() {

        Query query = manager.createQuery("SELECT OBJ FROM " + Cliente.class.getSimpleName() + " OBJ");
        List resultados = query.getResultList();
        manager.close();
        return resultados;

    }

    public Cliente findById(long id) {

        Cliente cliente = manager.find(Cliente.class, id);
        manager.close();
        return cliente;

    }

}
