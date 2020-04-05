package pl.mzuchnik.springpracadomowa7.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.mzuchnik.springpracadomowa7.model.Car;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDaoImpl implements CarDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        RowMapper<Car> rowMapper = (resultSet, i) -> new Car(resultSet.getLong("id"),resultSet.getString("mark"),resultSet.getString("model"), resultSet.getString("color"), LocalDate.parse(resultSet.getString("date_production")));
        String sql = "SELECT * FROM cars";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public int addCar(Car car) {
        String sql = "INSERT INTO cars VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql, car.getId(), car.getMark(), car.getModel(), car.getColor(), Date.valueOf(car.getDateProduction()));
    }

    @Override
    public Optional<Car> getCarById(long id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        Car car = jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> new Car(resultSet.getLong("id"),resultSet.getString("mark"),resultSet.getString("model"), resultSet.getString("color"), LocalDate.parse(resultSet.getString("date_production"))),id);
        return Optional.ofNullable(car);
    }

    @Override
    public int removeCarById(long id) {
        String sql = "DELETE FROM cars WHERE id = ?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateCar(long idCar, Car carUpdate) {

    }

    @Override
    public List<Car> findBetweenDates(LocalDate start, LocalDate end) {

        String sql = "SELECT * FROM cars WHERE date_production BETWEEN ? AND ?";
        List<Car> cars = jdbcTemplate.query(sql, ((resultSet, i) -> new Car(resultSet.getLong("id"), resultSet.getString("mark"), resultSet.getString("model"), resultSet.getString("color"), resultSet.getDate("date_production").toLocalDate())), start, end);
        return cars;
    }

    @Override
    public List<Car> findByColor(String color) {
        return null;
    }

    @Override
    public List<Car> findByMark(String mark) {
        return null;
    }

    @Override
    public List<Car> findByDate(LocalDate date) {
        return null;
    }
}
