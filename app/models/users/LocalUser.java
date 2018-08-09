package models.users;

import models.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Local")
public class LocalUser extends User {

}
