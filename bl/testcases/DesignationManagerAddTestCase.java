import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
class DesignationManagerAddTestCase
{
public static void main(String gg[])
{
DesignationInterface designation=new Designation();
designation.setTitle("Clerk");
try
{
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setCode(0);
designation.setTitle("Teacher");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setCode(0);
designation.setTitle("Nurse");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setCode(0);
designation.setTitle("Carpenter");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setCode(0);
designation.setTitle("Liftman");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setCode(0);
designation.setTitle("Faculty");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setCode(0);
designation.setTitle("Electrician");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setCode(0);
designation.setTitle("Manager");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());
designation.setTitle("Electrician");
designationManager.addDesignation(designation);
System.out.println("Designation added with code : "+designation.getCode());


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