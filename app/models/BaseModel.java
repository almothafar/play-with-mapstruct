package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.avaje.ebean.annotation.WhoModified;
import org.joda.time.DateTime;
import play.Logger;
import play.api.Play;
import play.api.i18n.Lang;
import play.i18n.MessagesApi;
import utils.MsgKeys;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseModel extends Model {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @CreatedTimestamp
    private DateTime createdDate;

    @Column
    @UpdatedTimestamp
    private DateTime updatedDate;

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

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public BaseModel setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    public BaseModel setUpdatedDate(DateTime updatedDate) {
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
