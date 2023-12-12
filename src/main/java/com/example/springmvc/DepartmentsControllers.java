package com.example.springmvc;

import com.example.springmvc.dao.DepartmentEntity;
import com.example.springmvc.dao.DepartmentRepository;
import com.example.springmvc.exception.UserNotFoundException;
import com.example.springmvc.model.Department;
import com.example.springmvc.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.temptable.StandardTemporaryTableExporter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentsControllers {
    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;
    private final String baseUrl = "departments";
    private String titlePage    = "Управление отделами";
    private String descriptionPage = "На данной странице вы можете добавить, отредактировать поля или удалить отделлы списка";

    @GetMapping
    public String index(Model model){
        model.addAttribute("titlePage", titlePage);
        model.addAttribute("descriptionPage", descriptionPage);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("departments", departmentService.getAllDepartmentsNotDel());
        return "departments_list";
    }

    /**
     *  Открытие формы для создания отдела
     */
    @GetMapping("/add")
    public String addDepartment(Model model){
        model.addAttribute("titlePage", "Добавить отдел");
        model.addAttribute("descriptionPage", "На данной странице вы можете добавить новый одел");
        model.addAttribute("baseUrl", baseUrl);
        DepartmentEntity departmentEntity = new DepartmentEntity();
        model.addAttribute("department", departmentEntity);
        return "add_department";
    }

    /**
     * Сохранение данных с формы
     */
    @PostMapping("/add_item")
    public String saveDepartment(@ModelAttribute Department department){
        departmentService.saveDepartment(department);
        return "redirect:/" + baseUrl;
    }

    @GetMapping("/edit/{id}")
    public String editDepartment(@PathVariable("id") Long id, Model model){
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("titlePage", "Отредактировать отдел: " + department.getName());
        model.addAttribute("descriptionPage", "На данной странице вы можете отредактировать одел");
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("department", department);
        return "edit_department";
    }

    /**
     * Обновление записи
     * @param department
     * @return
     */
    @PostMapping("/edit_item")
    public String updateDepartment(@ModelAttribute Department department){
        if (!departmentRepository.existsById(department.getId()))
            throw new UserNotFoundException("Department not found: id = " + department.getId());
        departmentService.updateDepartment(department);
        return "redirect:/" + baseUrl;
    }

    @GetMapping("/del/{id}")
    public String delDepartment(@PathVariable("id") Long id){
        if (!departmentRepository.existsById(id))
            throw new UserNotFoundException("Department not found: id = " + id);
        Department department = departmentService.getDepartmentById(id);
        departmentService.delDepartment(department);
        return "redirect:/" + baseUrl;
    }

}
