import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import java.util.*;
class DesignationManagerGetDesignationsTestCase
{
public static void main(String gg[])
{
try
{
//DesignationManagerInterface
Set<DesignationInterface> d=DesignationManager.getDesignationManager().getDesignations();
d.forEach((ds)->{
System.out.println(ds.getCode()+" ,"+ds.getTitle());
});
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
List<String> props;
props=blException.getProperties();
props.forEach((prop)->
{
System.out.println(blException.getException(prop));
});
}
}
}