package pl.pizza.pub.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Properties class of Size
 */
public class SizeFx {

    private IntegerProperty idSize = new SimpleIntegerProperty();
    private StringProperty size = new SimpleStringProperty();

    /**
     * Gets id value of Size
     * @return This Size id value
     */
    public int getIdSize() {
        return idSize.get();
    }

    /**
     * Gets id property of Size
     * @return This Size id property
     */
    public IntegerProperty idSizeProperty() {
        return idSize;
    }

    /**
     * Sets id value of Size
     * @param idSize idsize to set as value of idsize property
     */
    public void setIdSize(int idSize) {
        this.idSize.set(idSize);
    }

    /**
     * Gets size value of Size
     * @return This Size size value
     */
    public String getSize() {
        return size.get();
    }

    /**
     * Gets size property of Size
     * @return This Size size property
     */
    public StringProperty sizeProperty() {
        return size;
    }

    /**
     * Sets size value of Size
     * @param size size to set as value of size property
     */
    public void setSize(String size) {
        this.size.set(size);
    }

    /**
     * Override for gets size value of Size
     * @return This Size size value
     */
    @Override
    public String toString() {
        return size.getValue();
    }
}
