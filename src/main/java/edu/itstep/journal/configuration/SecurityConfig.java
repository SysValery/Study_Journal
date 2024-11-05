package edu.itstep.journal.configuration;

import edu.itstep.journal.repository.StudentRepository;
import edu.itstep.journal.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login").permitAll() // Дозвіл на доступ до кореневого маршруту та сторінки авторизації
                    .antMatchers("/teachers/**").hasRole("TEACHER")
                    .antMatchers("/students/**").hasRole("STUDENT")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")                   // Налаштування сторінки авторизації
                    .failureUrl("/login?error=true")
                    .successHandler(authenticationSuccessHandler())
                    .permitAll()
                .and()
                    .logout()
                        .logoutUrl("/logout")                 // URL для запиту на вихід (можна залишити стандартний /logout)
                        .logoutSuccessUrl("/login?logout=true") // Перенаправлення після виходу
                        .invalidateHttpSession(true)           // Інвалідація сесії
                        .deleteCookies("JSESSIONID")
                        .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            String role = authentication.getAuthorities().iterator().next().getAuthority();
            String username = authentication.getName();

            if (role.equals("ROLE_STUDENT")) {
                Long studentId = studentRepository.getStudentIdByUsername(username);
                response.sendRedirect("/students/" + studentId);
            } else if (role.equals("ROLE_TEACHER")) {
                Long teacherId = teacherRepository.getTeacherIdByUsername(username);
                response.sendRedirect("/teachers/" + teacherId);
            } else {
                response.sendRedirect("/login"); // Перенаправлення на сторінку за замовчуванням
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .passwordEncoder(passwordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, isActive from users where username = ?")
                .authoritiesByUsernameQuery("select username, role from users where username = ?");
    }
}
