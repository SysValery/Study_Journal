package edu.itstep.journal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_TEACHER")) {
                    // Замість {teacherId}, отримайте фактичний ID викладача з об'єкта аутентифікації або через сервіс
                    Integer teacherId = getTeacherId(authentication); // замінити цим методом
                    return "redirect:/teachers/" + teacherId;
                } else if (authority.getAuthority().equals("ROLE_STUDENT")) {
                    // Замість {studentId}, отримайте фактичний ID студента з об'єкта аутентифікації або через сервіс
                    Integer studentId = getStudentId(authentication); // замінити цим методом
                    return "redirect:/students/" + studentId;
                }
            }
        }

        return "redirect:/login";  // Перенаправляємо на логін, якщо користувач не аутентифікований або немає відповідних прав
    }

    // Методи для отримання ID викладача або студента з Authentication (повинні бути реалізовані)
    private Integer getTeacherId(Authentication authentication) {
        // Логіка отримання ID викладача
        // Наприклад, виклик сервісу викладача
        return 1; // Тимчасове значення
    }

    private Integer getStudentId(Authentication authentication) {
        // Логіка отримання ID студента
        // Наприклад, виклик сервісу студента
        return 1; // Тимчасове значення
    }
}
