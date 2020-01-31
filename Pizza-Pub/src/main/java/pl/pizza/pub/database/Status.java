package pl.pizza.pub.database;

import javax.persistence.*;

/**
 * Mapping status table from database
 */
@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private int idStatus;

    @Column(name = "status")
    private String status;

    public Status() {
    }

    public Status(int idStatus, String status) {
        this.idStatus = idStatus;
        this.status = status;
    }

    /**
     * Gets ID of Status
     *
     * @return This Status id
     */
    public int getIdStatus() {
        return idStatus;
    }

    /**
     * Sets ID of Size
     *
     * @param idStatus idStatus to set
     */
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    /**
     * Gets status of Status
     *
     * @return This Status status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status of Status
     *
     * @param status status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
