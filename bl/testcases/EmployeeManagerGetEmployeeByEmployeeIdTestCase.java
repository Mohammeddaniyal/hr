import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerGetEmployeeByEmployeeIdTestCase
{
public static void main(String gg[])
{
String employeeId=gg[0];
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
EmployeeInterface employee;
try{
employee=EmployeeManager.getEmployeeManager().getEmployeeByEmployeeId(employeeId);
System.out.println("Employee id : "+employee.getEmployeeId());
System.out.println("Name : "+employee.getName());
System.out.println("Designation code : "+employee.getDesignation().getCode());
System.out.println("Date : "+simpleDateFormat.format(employee.getDateOfBirth()));
System.out.println("Gender : "+employee.getGender());
System.out.println("Is indian : "+employee.getIsIndian());
System.out.println("Pan number : "+employee.getPANNumber());
System.out.println("Aadhar card number : "+employee.getAadharCardNumber());

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