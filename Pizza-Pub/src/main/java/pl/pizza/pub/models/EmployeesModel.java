package pl.pizza.pub.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.pizza.pub.database.Employees;
import pl.pizza.pub.database.Role;
import pl.pizza.pub.utils.ApplicationException;
import pl.pizza.pub.utils.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

/**
 * Class used to perform tasks associated with the Employees table
 */
public class EmployeesModel {

    private ObservableList<EmployeesFx> employeesFxObservableList = FXCollections.observableArrayList();
    private ObjectProperty<EmployeesFx> employeesFxObjectPropertyEdit = new SimpleObjectProperty<>(new EmployeesFx());

    /**
     * Checks which users exist
     * @param login login of user
     * @param password password of user
     * @return idRole of user or 0 if don't exist
     */
    public int login(String login, String password) {
        String sql = "SELECT * FROM employees WHERE login='" + login + "' AND password='" + password + "'";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Employees employees;
        try {
            session.beginTransaction();

            employees = session.createNativeQuery(sql, Employees.class).getSingleResult();

            session.getTransaction().commit();
        } catch (NoResultException e) {
            session.getTransaction().rollback();
            return -1;
        }

        if (employees.getRole().getIdRole() == 1) return 1;
        if (employees.getRole().getIdRole() == 2) return 2;
        if (employees.getRole().getIdRole() == 3) return 3;
        if (employees.getRole().getIdRole() == 4) return 4;

        return 0;
    }

    /**
     * Initializes list of records from Employee table
     */
    public void initEmployeesList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employees> query = builder.createQuery(Employees.class);
        Root<Employees> root = query.from(Employees.class);
        query.select(root);
        Query<Employees> q = session.createQuery(query);
        List<Employees> employeesList = q.getResultList();
        employeesFxObservableList.clear();
        for (Employees employee : employeesList){
            EmployeesFx employeesFx = new EmployeesFx();
            employeesFx.setIdEmployee(employee.getIdEmployee());
            employeesFx.setName(employee.getName());
            employeesFx.setSurname(employee.getSurname());
            employeesFx.setLogin(employee.getLogin());
            employeesFx.setPassword(employee.getPassword());
            employeesFx.setRole(employee.getRole().getRole());
            employeesFxObservableList.add(employeesFx);
        }

        session.getTransaction().commit();
    }

    /**
     * Adds new record to Employee table
     * @param name name of new employee
     * @param surname surname of new employee
     * @param login login of new employee
     * @param password password of new employee
     * @param roleFx property which contains role of new user
     * @throws ApplicationException Exception which method throws
     */
    public void addEmployee(String name, String surname, String login, String password, RoleFx roleFx) throws ApplicationException {
        Role role = new Role();
        role.setIdRole(roleFx.getIdRole());

        Employees employee = new Employees();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setLogin(login);
        employee.setPassword(password);
        employee.setRole(role);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initEmployeesList();
    }

    /**
     * Updates record from Menu table
     * @throws ApplicationException Exception which method throws
     */
    public void updateEmployee() throws ApplicationException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Employees updateEmployee = (Employees) session.get(Employees.class, employeesFxObjectPropertyEdit.getValue().getIdEmployee());
            updateEmployee.setName(employeesFxObjectPropertyEdit.getValue().getName());
            updateEmployee.setSurname(employeesFxObjectPropertyEdit.getValue().getSurname());
            updateEmployee.setLogin(employeesFxObjectPropertyEdit.getValue().getLogin());
            updateEmployee.setPassword(employeesFxObjectPropertyEdit.getValue().getPassword());

            session.update(updateEmployee);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initEmployeesList();
    }

    /**
     * Updates role of record from Employee table
     * @param roleFx property model role of record to update
     * @throws ApplicationException Exception which method throws
     */
    public void updateRoleEmployee(RoleFx roleFx) throws ApplicationException {
        Role role = new Role();
        role.setIdRole(roleFx.getIdRole());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            Employees updateEmployee = (Employees) session.get(Employees.class, employeesFxObjectPropertyEdit.getValue().getIdEmployee());
            updateEmployee.setRole(role);

            session.update(updateEmployee);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initEmployeesList();
    }

    /**
     * Deletes record from Employee table
     * @throws ApplicationException Exception which method throws
     */
    public void deleteEmployee() throws ApplicationException {
        Employees deleteEmpoyee = new Employees();
        deleteEmpoyee.setIdEmployee(employeesFxObjectPropertyEdit.getValue().getIdEmployee());

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(deleteEmpoyee);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            SQLException sqlException = getCauseOfClass(e, SQLException.class);
            throw new ApplicationException(sqlException.getMessage());
        }

        initEmployeesList();
    }

    /**
     * Gets employeesFxObservableList
     * @return observableArrayList
     */
    public ObservableList<EmployeesFx> getEmployeesFxObservableList() {
        return employeesFxObservableList;
    }

    /**
     * Gets employeesFxObjectPropertyEdit value
     * @return employeesFxObjectPropertyEdit value
     */
    public EmployeesFx getEmployeesFxObjectPropertyEdit() {
        return employeesFxObjectPropertyEdit.get();
    }

    /**
     * Sets employeesFxObjectPropertyEdit
     * @param employeesFxObjectPropertyEdit employeesFxObjectPropertyEdit to set
     */
    public void setEmployeesFxObjectPropertyEdit(EmployeesFx employeesFxObjectPropertyEdit) {
        this.employeesFxObjectPropertyEdit.set(employeesFxObjectPropertyEdit);
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
