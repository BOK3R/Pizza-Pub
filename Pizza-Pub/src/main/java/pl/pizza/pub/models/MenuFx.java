package pl.pizza.pub.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Properties class of Menu
 */
public class MenuFx {

    private IntegerProperty idProduct = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty price = new SimpleIntegerProperty();
    private StringProperty size = new SimpleStringProperty();

    /**
     * Gest id value of Product
     *
     * @return This Product id value
     */
    public int getIdProduct() {
        return idProduct.get();
    }

    /**
     * Gets id property of Product
     *
     * @return This Product id property
     */
    public IntegerProperty idProductProperty() {
        return idProduct;
    }

    /**
     * Sets id value of Product
     *
     * @param idProduct idProduct to set as value of idProduct property
     */
    public void setIdProduct(int idProduct) {
        this.idProduct.set(idProduct);
    }

    /**
     * Gets name value of Product
     *
     * @return This Product name value
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets name property of Product
     *
     * @return This Product name property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Sets name value of Product
     *
     * @param name name to set as value of name property
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Gets price value of Product
     *
     * @return This Product price value
     */
    public int getPrice() {
        return price.get();
    }

    /**
     * Gets price property of Product
     *
     * @return This Product price property
     */
    public IntegerProperty priceProperty() {
        return price;
    }

    /**
     * Sets price value of Product
     *
     * @param price price to set as value of price property
     */
    public void setPrice(int price) {
        this.price.set(price);
    }

    /**
     * Gets size value of Product
     *
     * @return This Product size value
     */
    public String getSize() {
        return size.get();
    }

    /**
     * Gets size property of Product
     *
     * @return This Product size property
     */
    public StringProperty sizeProperty() {
        return size;
    }

    /**
     * Sets size value of Product
     *
     * @param size size to sets as value of size property
     */
    public void setSize(String size) {
        this.size.set(size);
    }
}
