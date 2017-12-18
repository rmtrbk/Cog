package au.com.cognizant.userdata.databasetransactions;

import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * This class handles all database transaction with persistence layer.
 * 
 * @author theja
 * @param <T> Model object
 */
public class DatabaseTransactions<T> {

    /**
     * Creates a new record in the table.
     * 
     * @param objectToSave Model object that is being saved as a record in the table.
     * @return States whether the attempt was successful or failed.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    public boolean create(T objectToSave) throws NamingException, 
            NotSupportedException, SystemException, RollbackException, 
            HeuristicMixedException, HeuristicRollbackException {

        UserTransaction transaction = getUserTransaction();
        getUserTransaction().begin();

        getEntityManager().persist(objectToSave);

        transaction.commit();
        return true;
    }

    /** 
     * Retrieves a SINGLE RECORD based on the given criteria.
     * 
     * @param namedQuery Search criteria
     * @param parameters Parameters for the search criteria
     * @return The model object
     * @throws javax.naming.NamingException 
     */
    public T retrieve(String namedQuery, Map<String, Object> parameters) 
            throws NamingException, NoResultException {

        Query query = getEntityManager().createNamedQuery(namedQuery);
        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }

        return (T) query.getSingleResult();
    }

    /**
     * Updates the given record.
     * 
     * @param objectToUpdate Model object that is going to be updated.
     * @return States whether the attempt was successful or failed.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    public boolean update(T objectToUpdate) throws NamingException, 
            NotSupportedException, SystemException, RollbackException, 
            HeuristicMixedException, HeuristicRollbackException {

        UserTransaction transaction = getUserTransaction();
        transaction.begin();

        getEntityManager().merge(objectToUpdate);

        transaction.commit();
        return true;
    }

    /** 
     * Deletes the given record.
     * 
     * @param objectToRemove Model object that is going to be deleted.
     * @return States whether the attempt was successful or failed.
     * @throws javax.naming.NamingException
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
    public boolean delete(T objectToRemove) throws NamingException, 
            NotSupportedException, SystemException, RollbackException, 
            HeuristicMixedException, HeuristicRollbackException {

        UserTransaction transaction = getUserTransaction();
        transaction.begin();

        EntityManager entityManager = getEntityManager();
        if (!entityManager.contains(objectToRemove)) {
            objectToRemove = entityManager.merge(objectToRemove);
        }
        entityManager.remove(objectToRemove);

        transaction.commit();
        return true;
    }

    /** 
     * Finds the entity manager.
     * 
     */
    private EntityManager getEntityManager() throws NamingException {

        Context ctx = (Context) new InitialContext().lookup("java:comp/env");
        return (EntityManager) ctx.lookup("persistence/LogicalName");
    }

    /** 
     * Finds the user transaction.
     * 
     */
    private UserTransaction getUserTransaction() throws NamingException {

        return (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
    }
}