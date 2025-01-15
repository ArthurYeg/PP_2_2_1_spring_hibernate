package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityGraph;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "SELECT DISTINCT user FROM User user LEFT JOIN FETCH user.car", User.class);
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCarId(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "SELECT user FROM User user LEFT JOIN FETCH user.car WHERE user.car.model = :model AND user.car.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }
}