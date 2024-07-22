package com.ust.Employee_jpastreamer.controller;

import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody List<Employee> employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/groupByCity")
    public Map<String, List<Employee>> groupbyEmployeeByCity(){
        return employeeService.groupbyEmployeeByCity();
    }

    @GetMapping("/findall")
    public List<Employee> groupbyEmployeeByPaymentTier(){
        return employeeService.groupbyEmployeeByPaymentTier();
    }

    @GetMapping("/findByAgeRange/{value1},{value2}")
    public List<Employee> findByAgeRange(@PathVariable("value1") int value1, @PathVariable("value2") int value2){
        return employeeService.findByAgeRange(value1, value2);
    }

    @GetMapping("/countGender/{gender}")
    public Long countByGender(@PathVariable("gender") String gender){
        return employeeService.countByGender(gender);
    }

    @GetMapping("/employeesJoined/{year}")
    public List<Employee> findEmployeesJoinedInYear(@PathVariable("year") int year){
        return employeeService.findEmployeesJoinedInYear(year);
    }

    @GetMapping("/countGenderInAParticularYear/{gender},{year}")
    public Long countGenderInAParticularYear(@PathVariable("gender") String gender, @PathVariable("year") int year){
        return employeeService.countGenderInAParticularYear(gender, year);
    }

    @GetMapping("/groupByEducation")
    public Map<String, List<Employee>> groupByEducation(){
        return employeeService.groupByEducation();
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(@RequestParam(value = "Id", required = false) String id,
                                                          @RequestParam(value = "Education", required = false) String education,
                                                          @RequestParam(value = "joiningYear", required = false) String joiningYear,
                                                          @RequestParam(value = "city", required = false) String city,
                                                          @RequestParam(value = "paymentTier", required = false) String paymentTier,
                                                          @RequestParam(value = "age", required = false) String age,
                                                          @RequestParam(value = "gender", required = false) String gender,
                                                          @RequestParam(value = "everBenched", required = false) String everBenched,
                                                          @RequestParam(value = "experienceInCurrentDomain", required = false) String experienceInCurrentDomain){
        return employeeService.searchEmployees(id, education, joiningYear, city, paymentTier, age, gender, everBenched, experienceInCurrentDomain);


    }

}
