import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
public class DesignationGetAllTestCase
{
public static void main(String gg[])
{
try
{
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
Set<DesignationDTOInterface> designations;
designations=designationDAO.getAll();
designations.forEach((designation)->{
System.out.println("Code : "+designation.getCode()+" Title : "+designation.getTitle());
});

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}


}
}
 