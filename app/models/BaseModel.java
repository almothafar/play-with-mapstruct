package models;


import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseModel extends Model {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @CreatedTimestamp
    private LocalDateTime createdDate;

    @Column
    @UpdatedTimestamp
    private LocalDateTime updatedDate;

    @Column
    @Version
    private long version = 0;

    @Column
    private boolean isActive = true;

    public Integer getId() {
        return id;
    }

    public BaseModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public BaseModel setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public BaseModel setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public long getVersion() {
        return version;
    }

    public BaseModel setVersion(long version) {
        this.version = version;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public BaseModel setActive(boolean active) {
        isActive = active;
        return this;
    }
}
