package pl.mzuchnik.springpracadomowa7.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Car {

  private long id;
  private String mark;
  private String model;
  private String color;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateProduction;

  public Car() {
  }

  public Car(long id, String mark, String model, String color, LocalDate dateProduction) {
    this.id = id;
    this.mark = mark;
    this.model = model;
    this.color = color;
    this.dateProduction = dateProduction;
  }

  public Car(String mark, String model, String color, LocalDate dateProduction) {
    this.mark = mark;
    this.model = model;
    this.color = color;
    this.dateProduction = dateProduction;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public LocalDate getDateProduction() {
    return dateProduction;
  }

  public void setDateProduction(LocalDate dateProduction) {
    this.dateProduction = dateProduction;
  }

  @Override
  public String toString() {
    return "Car{" +
            "id=" + id +
            ", mark='" + mark + '\'' +
            ", model='" + model + '\'' +
            ", color='" + color + '\'' +
            ", dateProduction=" + dateProduction +
            '}';
  }
}
