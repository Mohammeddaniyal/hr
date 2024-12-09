import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class DesignationTitleExistsTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try
{
DesignationDTOInterface designationDTO;
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
if(designationDAO.titleExists(title)) System.out.println("Title Exists");
else System.out.println("Title does not exists");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}
}
 