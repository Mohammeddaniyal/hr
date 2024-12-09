import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class EmployeePANNumberExistsTestCase
{
public static void main(String gg[])
{
String panNumber=gg[0];
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO=new EmployeeDAO();
if(employeeDAO.panNumberExists(panNumber)) System.out.println("Pan number exists "+panNumber);
else System.out.println("Pan number doesn't exists "+panNumber);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}
}
 