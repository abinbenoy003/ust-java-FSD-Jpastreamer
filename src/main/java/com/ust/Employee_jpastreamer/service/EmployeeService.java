package com.ust.Employee_jpastreamer.service;



import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.Employee_jpastreamer.model.Employee;
import com.ust.Employee_jpastreamer.repository.Employeerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private Employeerepo employeeRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public EmployeeService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }


    public Map<String, List<Employee>> groupbyEmployeeByCity() {
        return jpaStreamer.stream(Employee.class)
                .collect(Collectors.groupingBy(Employee::getCity));
    }

    public List<Employee> groupbyEmployeeByPaymentTier() {
        return employeeRepository.findAll() ;
    }

    public List<Employee> saveEmployee(List<Employee> employee) {
        return employeeRepository.saveAll(employee);
    }

    public List<Employee> findByAgeRange(int value1, int value2) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getAge() >= value1 && employee.getAge() <= value2)
                .toList();
    }


    public Long countByGender(String gender) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .count();
    }

    public List<Employee> findEmployeesJoinedInYear(int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getJoiningYear() == year)
                .toList();
    }

    public Long countGenderInAParticularYear(String gender, int year) {
        return jpaStreamer.stream(Employee.class)
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender) && employee.getJoiningYear() == year)
                .count();
    }

    public Map<String, List<Employee>> groupByEducation() {
        return jpaStreamer.stream(Employee.class)
               .collect(Collectors.groupingBy(Employee::getEducation));
    }

    public List<Employee> searchEmployees(String id,String education,String joiningYear,String city,String paymentTier,String age,String gender,String everBenched,String experienceInCurrentDomain) {
        return jpaStreamer.stream(Employee.class).
                filter(employee-> id==null || id.equalsIgnoreCase(String.valueOf(employee.getId())) &&
                        education==null || education.equalsIgnoreCase(employee.getEducation()) &&
                        joiningYear==null || joiningYear.equalsIgnoreCase(String.valueOf(employee.getJoiningYear())) &&
                        city==null || city.equalsIgnoreCase(employee.getCity()) &&
                        paymentTier==null || paymentTier.equalsIgnoreCase(String.valueOf(employee.getPaymentTier())) &&
                        age==null || age.equalsIgnoreCase(String.valueOf(employee.getAge())) &&
                        gender==null || gender.equalsIgnoreCase(employee.getGender()) &&
                        everBenched==null || everBenched.equalsIgnoreCase(String.valueOf(employee.getEverBenched())) &&
                        experienceInCurrentDomain==null || experienceInCurrentDomain.equalsIgnoreCase(String.valueOf(employee.getExperienceInCurrentDomain())))
                .toList();
    }
}
