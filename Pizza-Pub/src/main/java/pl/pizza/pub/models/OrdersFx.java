package pl.pizza.pub.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Properties class of Orders
 */
public class OrdersFx {

    private IntegerProperty idOrder = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    /**
     * Gets id value of Order
     * @return This Order id value
     */
    public int getIdOrder() {
        return idOrder.get();
    }

    /**
     * Gets id property of Order
     * @return This Order id property
     */
    public IntegerProperty idOrderProperty() {
        return idOrder;
    }

    /**
     * Sets id value of Order
     * @param idOrder idOrder to set as value of idOrder property
     */
    public void setIdOrder(int idOrder) {
        this.idOrder.set(idOrder);
    }

    /**
     * Gets name value of Order
     * @return This Order name value
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets name property of Order
     * @return This Order name property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Sets name value of Order
     * @param name name to set as value of name property
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets status value of Orders
     * @return This Orders stastus value
     */
    public String getStatus() {
        return status.get();
    }

    /**
     * Gets status property of Orders
     * @return This Orders status property
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     * Sets status value of Orders
     * @param status status to set as value of status property
     */
    public void setStatus(String status) {
        this.status.set(status);
    }
}
