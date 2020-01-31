package pl.pizza.pub.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.pizza.pub.database.Size;
import pl.pizza.pub.utils.ApplicationException;
import pl.pizza.pub.utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

/**
 * Class used to perform tasks associated with the Size table
 */
public class SizeModel {

    private ObservableList<SizeFx> sizeFxObservableList = FXCollections.observableArrayList();

    /**
     * Initializes list of records from Size table
     */
    public void initSizeList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Size> query = builder.createQuery(Size.class);
        Root<Size> root = query.from(Size.class);
        query.select(root);
        Query<Size> q = session.createQuery(query);
        List<Size> sizeList = q.getResultList();
        sizeFxObservableList.clear();
        for (Size size : sizeList){
            SizeFx sizeFx = new SizeFx();
            sizeFx.setIdSize(size.getIdSize());
            sizeFx.setSize(size.getSize());
            sizeFxObservableList.add(sizeFx);
        }

        session.getTransaction().commit();
    }

    /**
     * Adds new record to Size table
     * @param newSize value of new size
     * @throws ApplicationException Exception which method throws
     */
    public void addSize(String newSize) throws ApplicationException {
        Size size = new Size();
        size.setSize(newSize);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            session.save(size);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initSizeList();
    }

    /**
     * Gets list of records from Size table
     * @return sizeFxObservableList
     */
    public ObservableList<SizeFx> getSizeFxObservableList() {
        return sizeFxObservableList;
    }

    /**
     * Sets list of records from Size table
     * @param sizeFxObservableList sizeFxObservableList to set
     */
    public void setSizeFxObservableList(ObservableList<SizeFx> sizeFxObservableList) {
        this.sizeFxObservableList = sizeFxObservableList;
    }

    static <E extends Throwable> E getCauseOfClass(RuntimeException e, Class<E> exceptionClass) {
        Throwable t = e;
        do {
            if (exceptionClass.isAssignableFrom(t.getClass())) {
                return (E) t;
            }
        } while ((t = t.getCause()) != null);
        throw e;
    }
}
