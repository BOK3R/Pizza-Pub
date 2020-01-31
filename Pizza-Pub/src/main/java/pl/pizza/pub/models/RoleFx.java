package pl.pizza.pub.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Properties class of Role
 */
public class RoleFx {

    private IntegerProperty idRole = new SimpleIntegerProperty();
    private StringProperty role = new SimpleStringProperty();

    /**
     * Gets id value of Role
     * @return This Role id value
     */
    public int getIdRole() {
        return idRole.get();
    }

    /**
     * Gets id property of Role
     * @return This Role id property
     */
    public IntegerProperty idRoleProperty() {
        return idRole;
    }

    /**
     * Sets id value of Role
     * @param idRole idRole to set as value of idRole property
     */
    public void setIdRole(int idRole) {
        this.idRole.set(idRole);
    }

    /**
     * Gets role value of Role
     * @return This Role role value
     */
    public String getRole() {
        return role.get();
    }

    /**
     * Gets role property of Role
     * @return This Role role property
     */
    public StringProperty roleProperty() {
        return role;
    }

    /**
     * Sets role value of Role
     * @param role role to set as value of role property
     */
    public void setRole(String role) {
        this.role.set(role);
    }

    /**
     * Override for gets role value of Role
     * @return This Role role value
     */
    @Override
    public String toString() {
        return role.getValue();
    }
}
