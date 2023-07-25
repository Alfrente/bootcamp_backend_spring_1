package com.arroyo.crud.controllrs;

import com.arroyo.crud.modls.Employee;
import com.arroyo.crud.services.EmployeeService;
import com.arroyo.crud.utils.PageRender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("titulo", "Empleado");
        model.addAttribute("texto", "Bienvenido al proyecto de prueba");
        return "index";
    }

    @GetMapping({"/employees", "/empleados"})
    public String employees(Model model) {
        model.addAttribute("titulo", "Empleados");
        model.addAttribute("employees", service.findAll());
        return "lista";
    }

    @GetMapping({"/employees", "/empleados"})
    public String index(@RequestParam(name = "pagge", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);

        Page<Employee> employees= service.findAll(pageRequest);

        PageRender pageRender= new PageRender("/employee", employees);

        model.addAttribute("titulo", "Empleados");
        model.addAttribute("employees", service.findAll());

        model.addAttribute("employees", employees);
        model.addAttribute("page", pageRender);
        return "lista";
    }

    @GetMapping("/employee/create")
    public String crear(Model model) {
        model.addAttribute("titulo", "Crear");
        model.addAttribute("employee", new Employee());
        return "form";
    }

    @PostMapping("/employee/save")
    public String save(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors())
            return "redirect:/form";
        service.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employee/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("employee",service.findOne(id));
        return "form";
    }

    @GetMapping("/employee/{id}/delete")
    public String edit(@PathVariable Long id) {
       service.delete(id);
        return "redirect:/employees";
    }
}
