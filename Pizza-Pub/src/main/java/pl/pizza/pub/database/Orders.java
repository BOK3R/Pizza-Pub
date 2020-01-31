package pl.pizza.pub.database;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Mapping orders table from database
 */
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int idOrder;

    @ManyToOne
    @JoinColumn(name = "id_status", foreignKey = @ForeignKey(name = "status_ibfk_1"))
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_menu",
        joinColumns = { @JoinColumn(name = "id_order")},
        inverseJoinColumns = { @JoinColumn(name = "id_product")})
    private List<Menu> menu = new ArrayList<>();

    public Orders() {
    }

    public Orders(int idOrder, Status status) {
        this.idOrder = idOrder;
        this.status = status;
    }

    /**
     * Gets ID of Order
     *
     * @return This Order id
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     * Sets ID of Order
     *
     * @param idOrder idOrder to set
     */
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * Gets status of Order
     *
     * @return This Order status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status of Order
     *
     * @param status status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets List of Products from Order
     *
     * @return This Order product list
     */
    public List<Menu> getMenu() {
        return menu;
    }

    /**
     * Sets List of Product of Order
     *
     * @param menu menu to set
     */
    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

}
