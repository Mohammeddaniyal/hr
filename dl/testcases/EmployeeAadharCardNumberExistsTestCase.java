import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class EmployeeAadharCardNumberExistsTestCase
{
public static void main(String gg[])
{
String aadharCardNumber=gg[0];
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO=new EmployeeDAO();
if(employeeDAO.aadharCardNumberExists(aadharCardNumber)) System.out.println("Aadhar card number exists "+aadharCardNumber);
else System.out.println("Aadhar card number doesn't exists "+aadharCardNumber);
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}
}
 