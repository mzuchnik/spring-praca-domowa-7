package pl.mzuchnik.springpracadomowa7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import pl.mzuchnik.springpracadomowa7.dao.CarDao;
import pl.mzuchnik.springpracadomowa7.model.Car;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarDao carDao;

    public CarController(CarDao carDao) {
        this.carDao = carDao;
    }

    @GetMapping
    public String showCars(Model model,
                           @RequestParam(required = false) String info,
                           @ModelAttribute("cars") ArrayList<Car> carBetweenDates) {
        if (carBetweenDates.isEmpty()) {
            model.addAttribute("cars", carDao.findAll());
        }else {
            model.addAttribute("cars", carBetweenDates);
        }
        model.addAttribute("info", info);
        model.addAttribute("newCar", new Car());
        model.addAttribute("id", 0);
        model.addAttribute("startDate", LocalDate.now());
        model.addAttribute("endDate", LocalDate.now());
        return "index";
    }

    @PostMapping("/addCar")
    public String addCar(@ModelAttribute Car car, RedirectAttributes ra) {
        if (carDao.addCar(car) == 1) {
            ra.addAttribute("info", "Dodano samochód");
        } else {
            ra.addAttribute("info", "Nie udało się dodać samochodu");
        }
        return "redirect:/cars";
    }

    @PostMapping("/removeCar")
    public String removeCar(@RequestParam long id, RedirectAttributes ra) {
        if (carDao.removeCarById(id) == 1) {
            ra.addAttribute("info", "Usunięto samochód o id: " + id);
        } else
            ra.addAttribute("info", "Nie udało się usunąc samochodu o id:" + id);
        return "redirect:/cars";
    }

    @PostMapping("/findBetweenDates")
    public String findBetweenDates(@RequestParam(name = "startDate") Date startDate,
                                   @RequestParam(name = "endDate") Date endDate,
                                   @ModelAttribute("cars") ArrayList<Car> cars,
                                   RedirectAttributes ra) {

        List<Car> betweenDates = carDao.findBetweenDates(startDate.toLocalDate(), endDate.toLocalDate());

        String info = "Samochody pomiędzy (" + startDate + " : " + endDate + ")";
        if (betweenDates.isEmpty())
            info += "<- Nieznaleziono";
        ra.addAttribute("info", info);
        ra.addFlashAttribute("cars", betweenDates);
        return "redirect:/cars";
    }

}
