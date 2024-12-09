import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
import java.text.*;
public class EmployeeGetAllTestCase
{
public static void main(String gg[])
{
try
{
EmployeeDAOInterface employeeDAO;
employeeDAO=new EmployeeDAO();
Set<EmployeeDTOInterface> employees;
employees=employeeDAO.getAll();
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeDTOInterface employeeDTO:employees)
{
System.out.println("Employee id : "+employeeDTO.getEmployeeId());
System.out.println("Name : "+employeeDTO.getName());
System.out.println("Designation code : "+employeeDTO.getDesignationCode());
System.out.println("Date of birth : "+simpleDateFormat.format(employeeDTO.getDateOfBirth()));
System.out.println("Gender : "+employeeDTO.getGender());
System.out.println("Is Indian : "+employeeDTO.getIsIndian());
System.out.println("Basic Salary : "+employeeDTO.getBasicSalary().toPlainString());
System.out.println("Pan Number : "+employeeDTO.getPANNumber());
System.out.println("Aadhar card number : "+employeeDTO.getAadharCardNumber());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}
}
 