package bookmarks.sampleentity;
import bookmarks.BaseObject;

import javax.persistence.*;


@Entity
@Table(name = "teammate")
public class Teammate extends BaseObject {

private Long id;
private String firstName;
private String lastName;
private String position;

    @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}
  public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}
  public String getLastName() {
return lastName;
}

public void setLastName(String lastName) {
this.lastName = lastName;
}
  public String getPosition() {
return position;
}

public void setPosition(String position) {
this.position = position;
}
}
