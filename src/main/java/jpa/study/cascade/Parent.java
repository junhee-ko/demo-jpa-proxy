package jpa.study.cascade;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

  @Id
  @Column(name = "PARENT_ID")
  private Long id;

  @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
  @JoinColumn(name = "PARENT_ID")
  private List<Child> children = new ArrayList<>();

  public Parent(Long id) {
    this.id = id;
  }

  public Parent() {
  }

  public List<Child> getChildren() {
    return children;
  }
}