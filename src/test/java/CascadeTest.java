import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpa.study.cascade.Child;
import jpa.study.cascade.Parent;
import jpa.study.loading.Member;
import jpa.study.loading.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CascadeTest {

  @Test
  void test() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutoring-persistence-unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();

    // 부모 저장
    Parent parent = new Parent(1L);
    entityManager.persist(parent);

    // 자식01 저장
    Child child01 = new Child(1L);
    child01.setParent(parent);
    parent.getChildren().add(child01);
    entityManager.persist(child01);

    // 자식02 저장
    Child child02 = new Child(2L);
    child02.setParent(parent);
    parent.getChildren().add(child02);
    entityManager.persist(child02);

    transaction.commit();
    entityManager.clear();

    assertNotNull(entityManager.find(Parent.class, 1L));
    assertNotNull(entityManager.find(Child.class, 1L));
    assertNotNull(entityManager.find(Child.class, 2L));
  }

  @Test
  void saveWithCascade() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutoring-persistence-unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();

    // 부모 저장
    Parent parent = new Parent(1L);
    entityManager.persist(parent);

    // 자식01 저장
    Child child01 = new Child(1L);
    child01.setParent(parent);
    parent.getChildren().add(child01);
//    entityManager.persist(child01);

    // 자식02 저장
    Child child02 = new Child(2L);
    child02.setParent(parent);
    parent.getChildren().add(child02);
//    entityManager.persist(child02);

    transaction.commit();
    entityManager.clear();

    assertNotNull(entityManager.find(Parent.class, 1L));
    assertNotNull(entityManager.find(Child.class, 1L));
    assertNotNull(entityManager.find(Child.class, 2L));
  }

  @Test
  void removeWithOrphanRemoval() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tutoring-persistence-unit");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    transaction.begin();

    // 부모 저장
    Parent parent = new Parent(1L);
    entityManager.persist(parent);

    // 자식01 저장
    Child child01 = new Child(1L);
    child01.setParent(parent);
    parent.getChildren().add(child01);
//    entityManager.persist(child01);

    // 자식02 저장
    Child child02 = new Child(2L);
    child02.setParent(parent);
    parent.getChildren().add(child02);
//    entityManager.persist(child02);

    transaction.commit();
    entityManager.clear();

    transaction.begin();

    Parent selectedParent = entityManager.find(Parent.class, 1L);
    selectedParent.getChildren().remove(0);                         // 자식 엔티티를 컬렉션에서 제거
    transaction.commit();
    entityManager.clear();

    assertNotNull(entityManager.find(Parent.class, 1L));
    assertNull(entityManager.find(Child.class, 1L));
    assertNotNull(entityManager.find(Child.class, 2L));
  }
}
