import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class EmployeeEmployeeIdExistsTestCase
{
public static void main(String gg[])
{
String employeeId=gg[0];
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO=new EmployeeDAO();
if(employeeDAO.employeeIdExists(employeeId)) System.out.println("Employee id exists "+employeeId);
else System.out.println("Employee id doesn't exists "+employeeId);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}
}
 