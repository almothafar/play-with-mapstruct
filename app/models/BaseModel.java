package models;


import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;
import play.Logger;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@SuppressWarnings("unchecked")
public abstract class BaseModel<T extends BaseModel> extends Model {
    private final Logger.ALogger logger = Logger.of(this.getClass());
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

    @Override
    public boolean delete() {
        logger.info("Deleting a record from database done by user id {} with account id {}", " internal ", null);
        return super.delete();
    }

    public Integer getId() {
        return id;
    }

    public T setId(Integer id) {
        this.id = id;
        return (T) this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public T setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return (T) this;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public T setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
        return (T) this;
    }

    public long getVersion() {
        return version;
    }

    public T setVersion(long version) {
        this.version = version;
        return (T) this;
    }

    public boolean isActive() {
        return isActive;
    }

    public T setActive(boolean active) {
        isActive = active;
        return (T) this;
    }
}
