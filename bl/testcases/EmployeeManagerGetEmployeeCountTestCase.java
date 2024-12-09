import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.enums.*;
import java.util.*;
import java.math.*;
import java.text.*;
class EmployeeManagerGetEmployeeCountTestCase
{
public static void main(String gg[])
{
try{
System.out.println("Total records : "+EmployeeManager.getEmployeeManager().getEmployeeCount());
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