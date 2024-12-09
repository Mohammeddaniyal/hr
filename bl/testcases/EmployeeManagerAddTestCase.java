import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
class EmployeeManagerAddTestCase
{
public static void main(String gg[])
{
DesignationInterface designation=new Designation();
designation.setCode(7);
EmployeeInterface employee;
employee=new Employee();
employee.setName("Steven");
employee.setDesignation(designation);
employee.setDateOfBirth(new Date());
employee.setGender(GENDER.MALE);
employee.setIsIndian(false);
employee.setBasicSalary(new BigDecimal("510000"));
employee.setPANNumber("PUK123456");
employee.setAadharCardNumber("UKP123456");
try{
EmployeeManager.getEmployeeManager().addEmployee(employee);
System.out.println("Employee added with employee id : "+employee.getEmployeeId());
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
List<String> properties=blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
/*
properties.forEach((property)->
{
System.out.println(blException.getException(property));
});
*/
}
}
}