package pl.pizza.pub.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.pizza.pub.database.Menu;
import pl.pizza.pub.database.Size;
import pl.pizza.pub.utils.ApplicationException;
import pl.pizza.pub.utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

/**
 * Class used to perform tasks associated with the Menu table
 */
public class MenuModel {

    private ObservableList<MenuFx> menuFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<MenuFx> menuFxObjectPropertyEdit = new SimpleObjectProperty<>(new MenuFx());
    private ObservableList<SalesFx> salesFxObservableList = FXCollections.observableArrayList();

    /**
     * Initializes list of records from menu table
     */
    public void initMenuList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Menu> query = builder.createQuery(Menu.class);
        Root<Menu> root = query.from(Menu.class);
        query.select(root);
        Query<Menu> q = session.createQuery(query);
        List<Menu> menuList = q.getResultList();
        menuFxObservableList.clear();
        for (Menu product : menuList){
            MenuFx menuFx = new MenuFx();
            menuFx.setIdProduct(product.getIdProduct());
            menuFx.setName(product.getName());
            menuFx.setPrice(product.getPrice());
            menuFx.setSize(product.getSize().getSize());
            menuFxObservableList.add(menuFx);
        }

        session.getTransaction().commit();
    }

    /**
     * Adds new record to Menu table
     * @param name name of new Product
     * @param price price of new Product
     * @param sizeFx property model size of new Product
     * @throws ApplicationException Exception which method throws
     */
    public void addProduct(String name, int price, SizeFx sizeFx) throws ApplicationException {
        Size size = new Size();
        size.setIdSize(sizeFx.getIdSize());

        Menu product = new Menu();
        product.setName(name);
        product.setPrice(price);
        product.setSize(size);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initMenuList();
    }

    /**
     * Updates record from Menu table
     * @throws ApplicationException Exception which method throws
     */
    public void updateProduct() throws ApplicationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            Menu updateProduct = (Menu) session.get(Menu.class, menuFxObjectPropertyEdit.getValue().getIdProduct());

            updateProduct.setName(menuFxObjectPropertyEdit.getValue().getName());
            updateProduct.setPrice(menuFxObjectPropertyEdit.getValue().getPrice());

            session.update(updateProduct);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initMenuList();
    }

    /**
     * Updates size of record from Menu table
     * @param sizeFx property model size of record to update
     * @throws ApplicationException Exception which method throws
     */
    public void updateSizeProduct(SizeFx sizeFx) throws ApplicationException {
        Size size = new Size();
        size.setIdSize(sizeFx.getIdSize());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Menu updateProduct = (Menu) session.get(Menu.class, menuFxObjectPropertyEdit.getValue().getIdProduct());
            updateProduct.setSize(size);

            session.update(updateProduct);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initMenuList();
    }

    /**
     * Deletes record from Menu table
     * @throws ApplicationException Exception which method throws
     */
    public void deleteProduct() throws ApplicationException {
        Menu deleteProduct = new Menu();
        deleteProduct.setIdProduct(menuFxObjectPropertyEdit.getValue().getIdProduct());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            session.delete(deleteProduct);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initMenuList();
    }

    /**
     * Initializes list of realized orders
     */
    public void initSalesList() {

        String sql = "SELECT menu.id_product, menu.name, menu.price,size.size, COUNT(orders_menu.id_order) "
        + "FROM menu, orders_menu, orders, size WHERE menu.id_product=orders_menu.id_product "
        + "AND orders.id_order=orders_menu.id_order AND orders.id_status=4 "
        + "AND size.id_size=menu.id_size GROUP BY menu.id_product";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();
        List<Object[]> empDepts = session.createNativeQuery(sql).list();

        salesFxObservableList.clear();
        for (Object[] objects : empDepts) {
            SalesFx salesFx = new SalesFx();
            salesFx.setIdProduct((Integer) objects[0]);
            salesFx.setName((String) objects[1]);
            salesFx.setPrice((Integer) objects[2]);
            salesFx.setSize((String) objects[3]);
            BigInteger bigInteger = (BigInteger) objects[4];
            salesFx.setSale(bigInteger.intValue());
            salesFxObservableList.add(salesFx);
        }

        session.getTransaction().commit();
    }

    /**
     * Gets list of menu table records
     * @return menuFxObservableList
     */
    public ObservableList<MenuFx> getMenuFxObservableList() {
        return menuFxObservableList;
    }

    /**
     * Gets getMenuFxObjectPropertyEdit value
     * @return getMenuFxObjectPropertyEdit value
     */
    public MenuFx getMenuFxObjectPropertyEdit() {
        return menuFxObjectPropertyEdit.get();
    }

    /**
     * Gets getMenuFxObjectPropertyEdit property
     * @return getMenuFxObjectPropertyEdit property
     */
    public ObjectProperty<MenuFx> menuFxObjectPropertyPropertyEdit() {
        return menuFxObjectPropertyEdit;
    }

    /**
     * Sets menuFxObjectPropertyEdit property
     * @param menuFxObjectProperty menuFxObjectProperty to set
     */
    public void setMenuFxObjectPropertyEdit(MenuFx menuFxObjectProperty) {
        this.menuFxObjectPropertyEdit.set(menuFxObjectProperty);
    }

    /**
     * Gets getSalesFxObservableList
     * @return getSalesFxObservableList
     */
    public ObservableList<SalesFx> getSalesFxObservableList() {
        return salesFxObservableList;
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
