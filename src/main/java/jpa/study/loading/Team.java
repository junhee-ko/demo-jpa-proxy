package jpa.study.loading;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

  @Id
  @Column(name = "TEAM_ID")
  private Long id;

  private String name;

  @OneToMany(mappedBy = "team")
  private List<Member> members = new ArrayList<>();

  public Team(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Team() {
  }

  public String getName() {
    return name;
  }
}