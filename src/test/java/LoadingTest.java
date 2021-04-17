import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpa.study.loading.Member;
import jpa.study.loading.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoadingTest {

  @Test
  void test() {
    // Given
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutoring-persistence-unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();

    Team team01 = new Team(1L, "team01");
    entityManager.persist(team01);

    Member member01 = new Member(1L, "member01");
    member01.setTeam(team01);
    entityManager.persist(member01);

    transaction.commit();   // flush : INSERT 2개
    entityManager.clear();  // 영속성 컨텍스트 초기화

    // When
    Member member = entityManager.find(Member.class, 1L);
    Team team = member.getTeam(); // Proxy

    // Then
    assertNotNull(member);
    assertNotNull(team);
  }
}
