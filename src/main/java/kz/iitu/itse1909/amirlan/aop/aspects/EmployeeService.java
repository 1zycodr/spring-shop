package kz.iitu.itse1909.amirlan.aop.aspects;

public class EmployeeService {

    private Employee employee;

    public Employee getEmployee(){
        return this.employee;
    }

    public void setEmployee(Employee e){
        this.employee=e;
    }
}
