package jpa.study.cascade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Child {

  @Id
  @Column(name = "CHILD_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "PARENT_ID")
  private Parent parent;

  public Child(Long id) {
    this.id = id;
  }

  public Child() {
  }

  public void setParent(Parent parent) {
    this.parent = parent;
  }
}
