package com.luv2code.springboot.thy.thymeleafdemo.controller;

import com.luv2code.springboot.thy.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thy.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

//    @Autowired  //we can  not add annotation because we have only 1 controller
    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    //add mapping for "/list"

    @GetMapping("/list")
    public String listEmployyes(Model model){
        //get employees from db
        List<Employee> theEmployees = employeeService.findAll();

        //add to spring model
        model.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
        public String showFormForAdd(Model model){
            Employee employee = new Employee();

            model.addAttribute("employee", employee);

            return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){

        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model){
        Employee theEmployee = employeeService.findById(id);

        model.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){

        employeeService.deleteById(id);

        return "redirect:/employees/list";

    }

}
