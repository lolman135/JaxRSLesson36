package repository.user;

import config.HibernateConfig;
import dto.user.request.UserDtoRequest;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void save(UserDtoRequest request) {

        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "INSERT INTO User (firstName, lastName, email) " +
                    "VALUES (:firstName, :lastName, :email)";

            MutationQuery mutationQuery = session.createMutationQuery(hql);
            mutationQuery.setParameter("firstName", request.firstName());
            mutationQuery.setParameter("lastName", request.lastName());
            mutationQuery.setParameter("email", request.email());
            mutationQuery.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<List<User>> getAll() {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).getResultList();
            transaction.commit();
            return Optional.of(users);

        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getById(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);

            if (user == null){
                transaction.rollback();
                return Optional.empty();
            }
            transaction.commit();
            return Optional.of(user);

        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return Optional.empty();
    }

    @Override
    public User update(Long id, UserDtoRequest request) {
        Transaction transaction = null;
        User updatedUser = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            String hql = "UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.email = :email " +
                    "WHERE u.id = :id";

            MutationQuery mutationQuery = session.createMutationQuery(hql);
            mutationQuery.setParameter("firstName", request.firstName());
            mutationQuery.setParameter("lastName", request.lastName());
            mutationQuery.setParameter("email", request.email());
            mutationQuery.setParameter("id", id);
            mutationQuery.executeUpdate();
            int rowsAffected = mutationQuery.executeUpdate();

            if (rowsAffected > 0) {
                updatedUser = session.get(User.class, id);
            } else {
                throw new IllegalArgumentException("User not found with id: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update user", e);
        }

        return updatedUser;
    }

    @Override
    public boolean deleteById(Long id) {
        Transaction transaction = null;
        boolean flag = false;
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null){
                session.remove(user);
                flag = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return flag;
    }

    @Override
    public Optional<User> getLastEntity() {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<User> query = session.createQuery("FROM User ORDER BY id DESC", User.class);
            query.setMaxResults(1);
            User user = query.uniqueResult();

            transaction.commit();
            return Optional.of(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return Optional.empty();
        }
    }

    @Override
    public List<User> fetchByFirstName(String firstName) {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User WHERE firstName = :firstName";

            Query<User> query = session.createQuery(hql);
            query.setParameter("firstName", firstName);

            users = query.getResultList();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return users;
    }

    @Override
    public List<User> fetchByLastName(String lastName) {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User WHERE lastName = :lastName";

            Query<User> query = session.createQuery(hql);
            query.setParameter("lastName", lastName);

            users = query.getResultList();
            transaction.commit();
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return users;
    }
}
