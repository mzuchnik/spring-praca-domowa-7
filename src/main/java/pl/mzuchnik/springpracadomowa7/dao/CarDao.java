package pl.mzuchnik.springpracadomowa7.dao;

import pl.mzuchnik.springpracadomowa7.model.Car;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarDao {

    List<Car> findAll();

    int addCar(Car car);

    Optional<Car> getCarById(long id);

    int removeCarById(long id);

    void updateCar(long idCar, Car carUpdate);

    List<Car> findBetweenDates(LocalDate start, LocalDate end);

    List<Car> findByColor(String color);

    List<Car> findByMark(String mark);

    List<Car> findByDate(LocalDate date);
}
