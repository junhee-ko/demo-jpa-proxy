package jpa.study.loading;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Member {

  @Id
  @Column(name = "MEMBER_ID")
  private Long id;

  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID") // 외래키
  private Team team;

  public Member(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Member() {
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Team getTeam() {
    return team;
  }
}
