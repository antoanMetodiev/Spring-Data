import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                 Persistence.createEntityManagerFactory("code_first");
//
//        EntityManager entityManager =
//                entityManagerFactory.createEntityManager();
//
//        entityManager.persist(new Wizard_Deposits());

    }
}
