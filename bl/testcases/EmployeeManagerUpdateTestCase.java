import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
class EmployeeManagerUpdateTestCase
{
public static void main(String gg[])
{
DesignationInterface designation=new Designation();
designation.setCode(6);
EmployeeInterface employee;
employee=new Employee();
employee.setEmployeeId("A10000002");
employee.setName("Danish");
employee.setDesignation(designation);
employee.setDateOfBirth(new Date());
employee.setGender(GENDER.MALE);
employee.setIsIndian(true);
employee.setBasicSalary(new BigDecimal("100000"));
employee.setPANNumber("PAK1234");
employee.setAadharCardNumber("POK123456");
try{
EmployeeManager.getEmployeeManager().updateEmployee(employee);
System.out.println("Employee updated");
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