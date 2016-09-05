package controllers.viewModels;


import java.util.Date;

public abstract class BaseDTO {

    private int id;
    private boolean isActive = true;
    private Date createdDate;
    private Date updatedDate;

    public int getId() {
        return id;
    }

    public BaseDTO setId(int id) {
        this.id = id;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public BaseDTO setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public BaseDTO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public BaseDTO setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }
}
