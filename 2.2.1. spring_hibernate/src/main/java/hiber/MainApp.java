package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
       AnnotationConfigApplicationContext context =
               new AnnotationConfigApplicationContext(AppConfig.class);

       UserService userService = context.getBean(UserService.class);

       User artyom = new User("Artyom", "Rasputin", "artyom@mail.ru");
       User sasha = new User("Sasha", "Petrov", "sasha@mail.ru");
       User nastyan = new User("Nastya ", "Dolina", "nastya@mail.ru");
       User masha = new User("Masha", "Korolyova", "masha@mail.ru");


       Car mercedes = new Car(235, "Mercedes");
       Car volga = new Car(562, "Volga");
       Car opel = new Car(254, "Opel");
       Car reno = new Car(965, "Reno");

       artyom.setCar(mercedes);
       mercedes.setUser(artyom);
       sasha.setCar(volga);
       volga.setUser(sasha);
       nastyan.setCar(opel);
       opel.setUser(nastyan);
       masha.setCar(reno);
       reno.setUser(masha);

       userService.add(artyom);
       userService.add(sasha);
       userService.add(nastyan);
       userService.add(masha);

       List<User> users = userService.listUsers();
       for (User user : users) {
           System.out.println("Id = " + user.getId());
           System.out.println("First Name = " + user.getFirstName());
           System.out.println("Last Name = " + user.getLastName());
           System.out.println("Email = " + user.getEmail());
           System.out.println("Car = " + user.getCar());
           System.out.println();
       }
       System.out.println(userService.getUserByCarId("Mercedes", 235).getFirstName());
       context.close();
   }}