import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class All_Tasks {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager =
                entityManagerFactory.createEntityManager();

        // Tasks:
//        task_2(entityManager);
//        task_3(entityManager);
//        task_4(entityManager);
//        task_5(entityManager);
//        task_6(entityManager);
//        task_7(entityManager);
//        task_8(entityManager);
//        task_9(entityManager);
    }

    private static void task_9(EntityManager entityManager) {
        List<Project> projects =
                entityManager.createQuery("FROM Project ORDER BY name", Project.class)
                        .setMaxResults(10)
                        .getResultList();

        projects.forEach(project -> {
            System.out.printf("Project name: %s%n Project Description: %s%n " +
                                    "Project Start Date: %s%n Project End Date: %s%n",
                    project.getName(), project.getDescription(),
                    project.getStartDate(), project.getEndDate())
                    .append(System.lineSeparator());
        });
    }

    private static void task_8(EntityManager entityManager) throws IOException {
        int inputId = Integer.parseInt(READER.readLine());

        List<Employee> resultList = entityManager.createQuery("FROM Employee WHERE id = :inputId", Employee.class)
                .setParameter("inputId", inputId)
                .getResultList();


        resultList.forEach(emp -> {
            System.out.printf("%s %s - %s", emp.getFirstName(), emp.getLastName(), emp.getJobTitle())
                    .append(System.lineSeparator());

            TreeSet<String> projects = new TreeSet<>();
            emp.getProjects().forEach(e -> {
                projects.add(e.getName());
            });

            projects.forEach(projectName -> {
                System.out.printf("%s", projectName).append(System.lineSeparator());
            });
        });
    }

    private static void task_7(EntityManager entityManager) {
        List<Address> resultList = entityManager.createQuery("FROM Address ORDER BY employees.size DESC", Address.class).setMaxResults(10)
                .getResultList();

        resultList.forEach(address -> {
            System.out.printf("%s %s, - %d employees", address.getText(), address.getTown().getName()
                    , address.getEmployees().size()).append(System.lineSeparator());
        });

        System.out.println();
    }

    private static void task_6(EntityManager entityManager) throws IOException {
        entityManager.getTransaction().begin();

        try {
            Town town = entityManager.find(Town.class, 32);
            if (town == null) {
                System.out.println("The town is not here...");
                return;
            }

            Address address = new Address();
            address.setText("Vitoshka 15");
            address.setTown(town);
            entityManager.persist(address);

            String inputName = READER.readLine();
            List<Employee> resultList = entityManager.createQuery(
                            "FROM Employee WHERE lastName = :last_name", Employee.class)
                    .setParameter("last_name", inputName)
                    .getResultList();

            if (!resultList.isEmpty()) {
                resultList.forEach(employee -> {
                    employee.setAddress(address);
                });
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void task_5(EntityManager entityManager) {
        entityManager.createQuery(
                        "SELECT CONCAT(e.firstName, ' ', e.lastName) AS fullName," +
                                " d.name, e.salary " +
                                "FROM Employee e " +
                                "JOIN e.department d " +
                                "WHERE d.name = 'Research and Development' " +
                                "ORDER BY e.salary, e.department.id", Object[].class)
                .getResultList().forEach(employee -> {
                    System.out.printf("%s for %s - %.2f", employee[0], employee[1], employee[2])
                            .append(System.lineSeparator());
                });
    }

    private static void task_4(EntityManager entityManager) {
        entityManager.createQuery("SELECT firstName FROM Employee WHERE salary > 50000")
                .getResultList().forEach(System.out::println);
    }

    private static void task_2(EntityManager entityManager) {
        int value = entityManager.createQuery("UPDATE Town\n" +
                "SET name = UPPER(name)\n" +
                "WHERE LENGTH(name) > 5").executeUpdate();

        System.out.println(value);
    }

    private static void task_3(EntityManager entityManager) throws IOException {
        String[] input = READER.readLine().split("\\s+");

        List resultList = entityManager.createQuery("FROM Employee WHERE firstName = :first_name AND lastName = :last_name")
                .setParameter("first_name", input[0])
                .setParameter("last_name", input[1])
                .getResultList();

        System.out.println(resultList.size() > 0 ? "YES!" : "NO!");
    }
}
