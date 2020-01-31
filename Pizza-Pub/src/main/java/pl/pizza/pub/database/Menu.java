package pl.pizza.pub.database;

import javax.persistence.*;

/**
 * Mapping manu table from database
 */
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_size", foreignKey = @ForeignKey(name = "size_ibfk_1"))
    private Size size;

    public Menu() {
    }

    public Menu(int idProduct, String name, int price, Size size) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    /**
     * Gets ID of Product
     *
     * @return This Product id
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * Sets ID of Product
     *
     * @param idProduct idProduct to set
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Gets Name of Product
     *
     * @return This Product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Name of Product
     *
     * @param name name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets Price of Product
     *
     * @return This Product price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets Price of Product
     *
     * @param price price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets Size of Product
     *
     * @return This Product size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets Size of Product
     *
     * @param size size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }
}
