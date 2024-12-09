import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
class DesignationManagerRemoveTestCase
{
public static void main(String gg[])
{try
{
DesignationManagerInterface designationManager=DesignationManager.getDesignationManager();
designationManager.removeDesignation(4);
designationManager.removeDesignation(5);
designationManager.removeDesignation(3);
designationManager.removeDesignation(7);
designationManager.removeDesignation(1);
designationManager.removeDesignation(2);

System.out.println("Designation deleted");
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
List<String> properties=blException.getProperties();
for(String property:properties)
{
System.out.println(blException.getException(property));
}
}
}
}