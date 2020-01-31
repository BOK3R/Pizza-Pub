package pl.pizza.pub.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Properties class of Employee
 */
public class EmployeesFx {

    private IntegerProperty idEmployee = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty surname = new SimpleStringProperty();
    private StringProperty login = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty role = new SimpleStringProperty();

    /**
     * Gets id value of EmployeeFx
     *
     * @return This EmployeeFx id value
     */
    public int getIdEmployee() {
        return idEmployee.get();
    }

    /**
     * Gets id property of EmployeeFx
     *
     * @return This EmployeeFx id property
     */
    public IntegerProperty idEmployeeProperty() {
        return idEmployee;
    }

    /**
     * Sets id value of EmployeeFx
     *
     * @param idEmployee idEmployee to set as value of idEmployee property
     */
    public void setIdEmployee(int idEmployee) {
        this.idEmployee.set(idEmployee);
    }

    /**
     * Gets name value of EmployeesFx
     *
     * @return This EmployeesFx id value
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets name property of EmployeesFx
     *
     * @return This EmployeesFx name property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Sets name value of EmployeesFx
     *
     * @param name name to set as value of name property
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets surname value of EmployeesFx
     *
     * @return This EmployeesFx surname value
     */
    public String getSurname() {
        return surname.get();
    }

    /**
     * Gets surname property of EmployeesFx
     *
     * @return This EmployeesFx surname property
     */
    public StringProperty surnameProperty() {
        return surname;
    }

    /**
     * Sets surname value of EmployeesFx
     *
     * @param surname surname to set as value of surname property
     */
    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    /**
     * Gets login value of EmployeesFx
     *
     * @return This EmployeesFx login value
     */
    public String getLogin() {
        return login.get();
    }

    /**
     * Gets login property of EmployeesFx
     *
     * @return This EmployeesFx login property
     */
    public StringProperty loginProperty() {
        return login;
    }

    /**
     * Sets login value of EmployeesFx
     *
     * @param login login to set as value of login property
     */
    public void setLogin(String login) {
        this.login.set(login);
    }

    /**
     * Gets password value of EmployeesFx
     *
     * @return This EmployeesFx password value
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * Gets password property of EmployeesFx
     *
     * @return This EmployeesFx password property
     */
    public StringProperty passwordProperty() {
        return password;
    }

    /**
     * Sets password value of EmployeesFx
     *
     * @param password password to set as value of password property
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * Gest role value of EmployeesFx
     *
     * @return This EmployeesFx role value
     */
    public String getRole() {
        return role.get();
    }

    /**
     * Gets role property of EmployeesFx
     *
     * @return This EmployeesFx role property
     */
    public StringProperty roleProperty() {
        return role;
    }

    /**
     * Sets role value of EmployeesFx
     *
     * @param role role to set as value of role name
     */
    public void setRole(String role) {
        this.role.set(role);
    }
}
