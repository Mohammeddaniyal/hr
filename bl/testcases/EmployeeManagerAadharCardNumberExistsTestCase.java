import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
String aadharCardNumber=gg[0];
try{
boolean exists=EmployeeManager.getEmployeeManager().employeeAadharCardNumberExists(aadharCardNumber);
if(exists)System.out.println("Exists");
else System.out.println("Not exists");
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