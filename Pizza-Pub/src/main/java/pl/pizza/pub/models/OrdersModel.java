package pl.pizza.pub.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.pizza.pub.database.Menu;
import pl.pizza.pub.database.Orders;
import pl.pizza.pub.database.Status;
import pl.pizza.pub.utils.ApplicationException;
import pl.pizza.pub.utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

/**
 * Class used to perform tasks associated with the Orders table
 */
public class OrdersModel {

    private ObservableList<MenuFx> menuFxObservableActualOrderList = FXCollections.observableArrayList();
    private ObservableList<OrdersFx> ordersFxObservableList = FXCollections.observableArrayList();
    private ObservableList<MenuFx> menuFxObservableCurrentOrderList = FXCollections.observableArrayList();

    /**
     * Initializes list of records from Orders table
     */
    public void initOrdersList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> query = builder.createQuery(Orders.class);
        Root<Orders> root = query.from(Orders.class);
        query.select(root);
        Query<Orders> q = session.createQuery(query);
        List<Orders> ordersList = q.getResultList();
        ordersFxObservableList.clear();
        for (Orders order : ordersList){
            if (order.getStatus().getIdStatus() == 4 || order.getStatus().getIdStatus() == 5) continue;
            OrdersFx ordersFx = new OrdersFx();
            ordersFx.setIdOrder(order.getIdOrder());
            ordersFx.setName("Zamówienie nr " + order.getIdOrder());
            ordersFx.setStatus(order.getStatus().getStatus());
            ordersFxObservableList.add(ordersFx);
        }

        session.getTransaction().commit();
    }

    /**
     * Calculates sum of current order
     * @return sum of current order
     */
    public int sumPrice() {
        int sumPrice = 0;
        for (MenuFx menuFx : menuFxObservableActualOrderList) {
            sumPrice = sumPrice + menuFx.getPrice();
        }

        return sumPrice;
    }

    /**
     * Adds new record to Oders table
     * @throws ApplicationException Exception which method throws
     */
    public void addOrder() throws ApplicationException {
        Orders order = new Orders();

        Status status = new Status();
        status.setIdStatus(1);

        order.setStatus(status);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            for (MenuFx menuFx : menuFxObservableActualOrderList) {
                Menu product = (Menu) session.get(Menu.class, menuFx.getIdProduct());
                order.getMenu().add(product);
            }

            session.save(order);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        menuFxObservableActualOrderList.clear();
    }

    /**
     * Gets current chosed order from database
     * @param idOrder id of current order
     * @throws ApplicationException Exception which method throws
     */
    public void getCurrentOrder(int idOrder) throws ApplicationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Orders order = (Orders) session.get(Orders.class, idOrder);

            menuFxObservableCurrentOrderList.clear();
            for (Menu product : order.getMenu()) {
                MenuFx menuFx = new MenuFx();
                menuFx.setIdProduct(product.getIdProduct());
                menuFx.setName(product.getName());
                menuFx.setSize(product.getSize().getSize());
                menuFx.setPrice(product.getPrice());

                menuFxObservableCurrentOrderList.add(menuFx);
            }

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }
    }

    /**
     * Update status of current order to Realising
     * @param idOrder id of current order
     * @throws ApplicationException Exception which method throws
     */
    public void updateStatusToRealising(int idOrder) throws ApplicationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Orders order = (Orders) session.get(Orders.class, idOrder);

            Status status = new Status();
            status.setIdStatus(2);

            order.setStatus(status);

            session.update(order);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initOrdersList();
    }

    /**
     * Update status of current order to Ready
     * @param idOrder id of current order
     * @throws ApplicationException Exception which method throws
     */
    public void updateStatusToReady(int idOrder) throws ApplicationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Orders order = (Orders) session.get(Orders.class, idOrder);

            Status status = new Status();
            status.setIdStatus(3);

            order.setStatus(status);

            session.update(order);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        menuFxObservableCurrentOrderList.clear();
        initOrdersList();
    }

    /**
     * Update status of current order to Completed
     * @param idOrder id of current order
     * @throws ApplicationException Exception which method throws
     */
    public void updateStatusToCompleted(int idOrder) throws ApplicationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Orders order = (Orders) session.get(Orders.class, idOrder);

            Status status = new Status();
            status.setIdStatus(4);

            order.setStatus(status);

            session.update(order);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        menuFxObservableCurrentOrderList.clear();
        initOrdersList();
    }

    /**
     * Update status of current order to Canceled
     * @param idOrder id of current order
     * @throws ApplicationException Exception which method throws
     */
    public void updateStatusToCanceled(int idOrder) throws ApplicationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Orders order = (Orders) session.get(Orders.class, idOrder);

            Status status = new Status();
            status.setIdStatus(5);

            order.setStatus(status);

            session.update(order);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        menuFxObservableCurrentOrderList.clear();
        initOrdersList();
    }

    /**
     * Initializes list of records of realised orders
     */
    public void initRealisedOrderList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> query = builder.createQuery(Orders.class);
        Root<Orders> root = query.from(Orders.class);
        query.select(root);
        Query<Orders> q = session.createQuery(query);
        List<Orders> ordersList = q.getResultList();
        ordersFxObservableList.clear();
        for (Orders order : ordersList){
            if (order.getStatus().getIdStatus() != 4) continue;
            OrdersFx ordersFx = new OrdersFx();
            ordersFx.setIdOrder(order.getIdOrder());
            ordersFx.setName("Zamówienie nr " + order.getIdOrder());
            ordersFx.setStatus(order.getStatus().getStatus());
            ordersFxObservableList.add(ordersFx);
        }

        session.getTransaction().commit();
    }

    /**
     * Gets number of realised orders
     * @return number of realised orders
     */
    public String getNumberOfRealisedSales() {
        String sql = "SELECT COUNT(orders.id_status) FROM orders WHERE orders.id_status=4";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        String number = session.createNativeQuery(sql).getSingleResult().toString();
        session.getTransaction().commit();

        return number;
    }

    /**
     * Gets number of canceled orders
     * @return number of canceled orders
     */
    public String getNumberOfCanceledSales() {
        String sql = "SELECT COUNT(orders.id_status) FROM orders WHERE orders.id_status=5";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        String number = session.createNativeQuery(sql).getSingleResult().toString();
        session.getTransaction().commit();

        return number;
    }

    /**
     * Gets list of current order products
     * @return list of current order products
     */
    public ObservableList<MenuFx> getMenuFxObservableActualOrderList() {
        return menuFxObservableActualOrderList;
    }

    /**
     * Gets list of orders
     * @return list of orders
     */
    public ObservableList<OrdersFx> getOrdersFxObservableList() {
        return ordersFxObservableList;
    }

    /**
     * Gets list of current orders
     * @return list of current orders
     */
    public ObservableList<MenuFx> getMenuFxObservableCurrentOrderList() {
        return menuFxObservableCurrentOrderList;
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
