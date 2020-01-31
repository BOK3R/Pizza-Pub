package pl.pizza.pub.database;

import javax.persistence.*;

/**
 * Mapping size table from database
 */
@Entity
@Table(name = "size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_size")
    private int idSize;

    @Column(name = "size")
    private String size;

    public Size() {
    }

    public Size(int idSize, String size) {
        this.idSize = idSize;
        this.size = size;
    }

    /**
     * Gets ID of Size
     *
     * @return This Size id
     */
    public int getIdSize() {
        return idSize;
    }

    /**
     * Sets ID of Size
     *
     * @param idSize idSize to set
     */
    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    /**
     * Gets size of Size
     *
     * @return This Size size
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets size of Size
     *
     * @param size size to set
     */
    public void setSize(String size) {
        this.size = size;
    }
}
