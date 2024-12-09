package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.enums.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.math.*;
public class EmployeeManager implements EmployeeManagerInterface
{
private Map<String,EmployeeInterface> employeeIdWiseEmployeesMap;
private Map<String,EmployeeInterface> panNumberWiseEmployeesMap;
private Map<String,EmployeeInterface> aadharCardNumberWiseEmployeesMap;
private Set<EmployeeInterface> employeesSet;
private Map<Integer,Set<EmployeeInterface>> designationCodeWiseEmployeesMap;
private static EmployeeManager employeeManager=null;
private EmployeeManager() throws BLException
{
populateDataStructures();
}
private void populateDataStructures() throws BLException
{
this.employeeIdWiseEmployeesMap=new HashMap<>();
this.panNumberWiseEmployeesMap=new HashMap<>();
this.aadharCardNumberWiseEmployeesMap=new HashMap<>();
this.employeesSet=new TreeSet<>();
this.designationCodeWiseEmployeesMap=new HashMap<>();
try
{
Set<EmployeeDTOInterface> employees=new EmployeeDAO().getAll();
DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
DesignationInterface dsDesignation;
EmployeeInterface employee;
Set<EmployeeInterface> ets;
for(EmployeeDTOInterface dlEmployee:employees)
{
employee=new Employee();
String employeeId=dlEmployee.getEmployeeId();
int designationCode=dlEmployee.getDesignationCode();
String panNumber=dlEmployee.getPANNumber();
String aadharCardNumber=dlEmployee.getAadharCardNumber();
employee.setEmployeeId(employeeId);
employee.setName(dlEmployee.getName());
employee.setDesignation(((DesignationManager)designationManager).getDSDesignationByCode(designationCode));
employee.setDateOfBirth((Date)dlEmployee.getDateOfBirth().clone());
employee.setGender((dlEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
employee.setIsIndian(dlEmployee.getIsIndian());
employee.setBasicSalary(dlEmployee.getBasicSalary());
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);

System.out.println("Adding in Employee Hash Map");
this.employeeIdWiseEmployeesMap.put(employeeId.toUpperCase(),employee);
System.out.println("Adding in PAN Number Hash Map");
this.panNumberWiseEmployeesMap.put(panNumber.toUpperCase(),employee);
System.out.println("Adding in Aadhar Card Number Hash Map");
this.aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase(),employee);
System.out.println("Adding in Employees Set");
this.employeesSet.add(employee);
System.out.println("Getting Set<EmployeeInterface> from Designation Code");
ets=this.designationCodeWiseEmployeesMap.get(designationCode);
if(ets==null)
{
ets=new TreeSet<EmployeeInterface>();
ets.add(employee);
System.out.println("Not found, created new and putting in HashMap of Designation Code");
this.designationCodeWiseEmployeesMap.put(designationCode,ets);
}
else
{
System.out.println("Found, add employee in Set which is value of Hash Map of Designation Code");
ets.add(employee);
}
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
}
}
public static EmployeeManager getEmployeeManager() throws BLException
{
if(employeeManager==null) employeeManager=new EmployeeManager();
return employeeManager;
}
public void addEmployee(EmployeeInterface employee) throws BLException
{
BLException blException=new BLException();
if(employee==null)
{
blException.setGenericException("Employee is null");
throw blException;
}
String employeeId=employee.getEmployeeId();
String name=employee.getName();
DesignationInterface designation=employee.getDesignation();
int designationCode=0;
Date dateOfBirth=employee.getDateOfBirth();
char gender=employee.getGender();
boolean isIndian=employee.getIsIndian();
BigDecimal basicSalary=employee.getBasicSalary();
String panNumber=employee.getPANNumber();
String aadharCardNumber=employee.getAadharCardNumber();

DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
if(employee!=null && employeeId.length()>0)
{
blException.addException("employeeId","Employee id should be empty");
}
if(name==null)
{
blException.addException("name","Name required");
}else
{
name=name.trim();
if(name.length()==0)
{
blException.addException("name","Name length is zero");
}
}
if(designation==null)
{
blException.addException("designation","Designation required");
}else
{
designationCode=designation.getCode(); 
if(designationCode<=0 || !(designationManager.designationCodeExists(designationCode)))
{
blException.addException("designationCode","Invalid Designation Code : "+designationCode);
}
else
{
designation=((DesignationManager)designationManager).getDSDesignationByCode(designationCode);
}
}


if(dateOfBirth==null)
{
blException.addException("dateOfBirth","Date of birth required");
}
if(gender==' ')
{
blException.addException("gender","Gender required Male/Female");
}
if(basicSalary==null)
{
blException.addException("basicSalary","Basic salary required");
}else
{
if(basicSalary.signum()<0)
{
blException.addException("basicSalary","Basic salary cannot be negative");
}
}
if(panNumber==null)
{
blException.addException("panNumber","Pan number required");
panNumber="";
}else
{
panNumber=panNumber.trim();
if(panNumber.length()==0)
{
blException.addException("panNumber","Pan number length is zero");
}
}

if(panNumber.length()>0)
{
if(this.panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase()))
{
blException.addException("panNumber","Pan number : "+panNumber+" exists");
}
}

if(aadharCardNumber==null)
{
blException.addException("aadharCardNumber","Aadhar card number required");
aadharCardNumber="";
}else
{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addException("aadharCardNumber","Aadhar card number length is zero");
}
}
if(aadharCardNumber.length()>0)
{
if(this.aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase()))
{
blException.addException("aadharCardNumber","Aadhar card number : "+aadharCardNumber+" exists.");
}
}
if(blException.hasExceptions())
{
throw blException;
}

try
{
Set<EmployeeInterface> ets;
EmployeeDTOInterface dlEmployee;
dlEmployee=new EmployeeDTO();
dlEmployee.setName(name);
dlEmployee.setDesignationCode(designationCode);
dlEmployee.setDateOfBirth(dateOfBirth);
dlEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dlEmployee.setIsIndian(isIndian);
dlEmployee.setBasicSalary(basicSalary);
dlEmployee.setPANNumber(panNumber);
dlEmployee.setAadharCardNumber(aadharCardNumber);
new EmployeeDAO().add(dlEmployee);
employeeId=dlEmployee.getEmployeeId();
employee.setEmployeeId(employeeId);
EmployeeInterface dsEmployee=new Employee();
dsEmployee.setEmployeeId(employeeId);
dsEmployee.setName(name);
dsEmployee.setDesignation(designation);
dsEmployee.setDateOfBirth((Date)dateOfBirth.clone());
dsEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dsEmployee.setIsIndian(isIndian);
dsEmployee.setBasicSalary(basicSalary);
dsEmployee.setPANNumber(panNumber);
dsEmployee.setAadharCardNumber(aadharCardNumber);
this.employeeIdWiseEmployeesMap.put(employeeId.toUpperCase(),dsEmployee);
this.panNumberWiseEmployeesMap.put(panNumber.toUpperCase(),dsEmployee);
this.aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase(),dsEmployee);
this.employeesSet.add(dsEmployee);

ets=this.designationCodeWiseEmployeesMap.get(designationCode);
if(ets==null)
{
ets=new TreeSet<>();
ets.add(dsEmployee);
this.designationCodeWiseEmployeesMap.put(designationCode,ets);
}
else
{
ets.add(dsEmployee);
}
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}
}
public void updateEmployee(EmployeeInterface employee) throws BLException
{
BLException blException=new BLException();
String employeeId=employee.getEmployeeId();
String name=employee.getName();
DesignationInterface designation=employee.getDesignation();
int designationCode=0;
Date dateOfBirth=employee.getDateOfBirth();
char gender=employee.getGender();
boolean isIndian=employee.getIsIndian();
BigDecimal basicSalary=employee.getBasicSalary();
String panNumber=employee.getPANNumber();
String aadharCardNumber=employee.getAadharCardNumber();

DesignationManagerInterface designationManager;
designationManager=DesignationManager.getDesignationManager();
if(employeeId==null)
{
blException.addException("employeeId","Employee id required");
}
else
{
employeeId=employeeId.trim();
if(employeeId.length()==0)
{
blException.addException("employeeId","Employee id length is zero");
throw blException;
}
else
{
EmployeeInterface ee=this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
if(ee==null)
{
blException.addException("employeeId","Invalid employee id "+employeeId);
throw blException;
}
}
}

if(name==null)
{
blException.addException("name","Name required");
}else
{
name=name.trim();
if(name.length()==0)
{
blException.addException("name","Name length is zero");
}
}
if(designation==null)
{
blException.addException("designation","Designation required");
}else
{
designationCode=designation.getCode(); 
if(designationCode<=0 || !(designationManager.designationCodeExists(designationCode)))
{
blException.addException("designationCode","Invalid Designation Code : "+designationCode);
}
else
{
designation=((DesignationManager)designationManager).getDSDesignationByCode(designationCode);
}
}


if(dateOfBirth==null)
{
blException.addException("dateOfBirth","Date of birth required");
}
if(gender==' ')
{
blException.addException("gender","Gender required Male/Female");
}
if(basicSalary==null)
{
blException.addException("basicSalary","Basic salary required");
}else
{
if(basicSalary.signum()<0)
{
blException.addException("basicSalary","Basic salary cannot be negative");
}
}
if(panNumber==null)
{
blException.addException("panNumber","Pan number required");
panNumber="";
}else
{
panNumber=panNumber.trim();
if(panNumber.length()==0)
{
blException.addException("panNumber","Pan number length is zero");
}
}
if(aadharCardNumber==null)
{
blException.addException("aadharCardNumber","Aadhar card number required");
aadharCardNumber="";
}else
{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addException("aadharCardNumber","Aadhar card number length is zero");
}
}
if(panNumber.length()>0)
{
EmployeeInterface emp;
emp=this.panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
if(emp!=null && emp.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
blException.addException("panNumber","Pan number : "+panNumber+" exists");
}
}
if(aadharCardNumber.length()>0)
{
EmployeeInterface emp;
emp=this.aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
if(emp!=null && emp.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
blException.addException("aadharCardNumber","Aadhar card number : "+aadharCardNumber+" exists.");
}
}
if(blException.hasExceptions())
{
throw blException;
}

try
{
Set<EmployeeInterface> ets;
EmployeeInterface dsEmployee=this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
int oldDesignationCode=dsEmployee.getDesignation().getCode();
String oldPANNumber=dsEmployee.getPANNumber();
String oldAadharCardNumber=dsEmployee.getAadharCardNumber();

EmployeeDAOInterface employeeDAO;
employeeDAO=new EmployeeDAO();
EmployeeDTOInterface dlEmployee;
dlEmployee=new EmployeeDTO();
dlEmployee.setEmployeeId(dsEmployee.getEmployeeId());
dlEmployee.setName(name);
dlEmployee.setDesignationCode(designationCode);
dlEmployee.setDateOfBirth(dateOfBirth);
dlEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dlEmployee.setIsIndian(isIndian);
dlEmployee.setBasicSalary(basicSalary);
dlEmployee.setPANNumber(panNumber);
dlEmployee.setAadharCardNumber(aadharCardNumber);
employeeDAO.update(dlEmployee);

dsEmployee=new Employee();
dsEmployee.setEmployeeId(employeeId);
dsEmployee.setName(name);
dsEmployee.setDesignation(designation);
dsEmployee.setDateOfBirth((Date)dateOfBirth.clone());
dsEmployee.setGender((gender=='M')?GENDER.MALE:GENDER.FEMALE);
dsEmployee.setIsIndian(isIndian);
dsEmployee.setBasicSalary(basicSalary);
dsEmployee.setPANNumber(panNumber);
dsEmployee.setAadharCardNumber(aadharCardNumber);

this.employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase(),dsEmployee);
this.panNumberWiseEmployeesMap.remove(oldPANNumber.toUpperCase(),dsEmployee);
this.aadharCardNumberWiseEmployeesMap.remove(oldAadharCardNumber.toUpperCase(),dsEmployee);
this.employeesSet.remove(dsEmployee);
ets=this.designationCodeWiseEmployeesMap.get(oldDesignationCode);
ets.remove(dsEmployee);
this.employeeIdWiseEmployeesMap.put(employeeId.toUpperCase(),dsEmployee);
this.panNumberWiseEmployeesMap.put(panNumber.toUpperCase(),dsEmployee);
this.aadharCardNumberWiseEmployeesMap.put(aadharCardNumber.toUpperCase(),dsEmployee);
this.employeesSet.add(dsEmployee);
ets.add(dsEmployee);
}catch(DAOException daoException)
{
}
}
public void removeEmployee(String employeeId) throws BLException
{
BLException blException=new BLException();
if(employeeId==null)
{
blException.addException("employeeId","Employee id required");
throw blException;
}
employeeId=employeeId.trim();
if(employeeId.length()==0)
{
blException.addException("employeeId","Employee id length is zero");
throw blException;
}
EmployeeInterface employee=this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
if(employee==null)
{
blException.addException("employeeId","Invalid employee id "+employeeId);
throw blException;
}
try
{
Set<EmployeeInterface> ets;
new EmployeeDAO().delete(employeeId);
this.employeeIdWiseEmployeesMap.remove(employeeId.toUpperCase());
this.panNumberWiseEmployeesMap.remove(employee.getPANNumber().toUpperCase());
this.aadharCardNumberWiseEmployeesMap.remove(employee.getAadharCardNumber().toUpperCase());
this.employeesSet.remove(employee);
ets=this.designationCodeWiseEmployeesMap.get(employee.getDesignation().getCode());
ets.remove(employee);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public Set<EmployeeInterface> getEmployees() throws BLException
{
Set<EmployeeInterface> employees;
employees=new TreeSet<>();
for(EmployeeInterface employee:this.employeesSet)
{
EmployeeInterface dsEmployee;
dsEmployee=new Employee();
dsEmployee.setEmployeeId(employee.getEmployeeId());
dsEmployee.setName(employee.getName());
dsEmployee.setDesignation(employee.getDesignation());
dsEmployee.setDateOfBirth(employee.getDateOfBirth());
dsEmployee.setGender((employee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
dsEmployee.setIsIndian(employee.getIsIndian());
dsEmployee.setBasicSalary(employee.getBasicSalary());
dsEmployee.setPANNumber(employee.getPANNumber());
dsEmployee.setAadharCardNumber(employee.getAadharCardNumber());
employees.add(dsEmployee);
}
return employees;
}
public Set<EmployeeInterface> getEmployeesByDesignationCode(int designationCode) throws BLException
{
BLException blException=new BLException();
if(designationCode<=0 || (DesignationManager.getDesignationManager().designationCodeExists(designationCode)==false))
{
blException.addException("designationCode","Invalid designation code "+designationCode);
throw blException;
}
Set<EmployeeInterface> employees;
employees=new TreeSet<>();
EmployeeInterface dsEmployee;
Set<EmployeeInterface> ets=this.designationCodeWiseEmployeesMap.get(designationCode);
if(ets==null)
{
return employees;
}
for(EmployeeInterface employee:ets)
{
dsEmployee=new Employee();
dsEmployee.setEmployeeId(employee.getEmployeeId());
dsEmployee.setName(employee.getName());
dsEmployee.setDesignation(employee.getDesignation());
dsEmployee.setDateOfBirth(employee.getDateOfBirth());
dsEmployee.setGender((employee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
dsEmployee.setIsIndian(employee.getIsIndian());
dsEmployee.setBasicSalary(employee.getBasicSalary());
dsEmployee.setPANNumber(employee.getPANNumber());
dsEmployee.setAadharCardNumber(employee.getAadharCardNumber());
employees.add(dsEmployee);
}
return employees;
}
public boolean isDesignationAlloted(int designationCode) throws BLException
{
System.out.println("EmployeeManager code : "+designationCode);
BLException blException=new BLException();
if(designationCode<=0 || !(DesignationManager.getDesignationManager().designationCodeExists(designationCode)))
{
blException.addException("code","Invalid designation code "+designationCode);
throw blException;
}
return this.designationCodeWiseEmployeesMap.containsKey(designationCode);
}
public EmployeeInterface getEmployeeByEmployeeId(String employeeId) throws BLException
{
BLException blException=new BLException();
if(employeeId==null)
{
blException.addException("employeeId","Employee id required");
throw blException;
}
employeeId=employeeId.trim();
if(employeeId.length()==0)
{
blException.addException("employeeId","Employee id length is zero");
throw blException;
}
EmployeeInterface dsEmployee=this.employeeIdWiseEmployeesMap.get(employeeId.toUpperCase());
if(dsEmployee==null)
{
blException.addException("employeeId","Invalid employee id "+employeeId);
throw blException;
}

DesignationInterface dsDesignation=dsEmployee.getDesignation();
DesignationInterface designation=new Designation();
EmployeeInterface employee;
employee=new Employee();
designation.setCode(dsDesignation.getCode());
designation.setTitle(dsDesignation.getTitle());
employee.setEmployeeId(dsEmployee.getEmployeeId());
employee.setName(dsEmployee.getName());
employee.setDesignation(designation);
employee.setDateOfBirth(dsEmployee.getDateOfBirth());
employee.setGender((dsEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
employee.setIsIndian(dsEmployee.getIsIndian());
employee.setBasicSalary(dsEmployee.getBasicSalary());
employee.setPANNumber(dsEmployee.getPANNumber());
employee.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return employee;
}
public EmployeeInterface getEmployeeByPANNumber(String panNumber) throws BLException
{
BLException blException=new BLException();
if(panNumber==null)
{
blException.addException("panNumber","Pan number required");
throw blException;
}
panNumber=panNumber.trim();
if(panNumber.length()==0)
{
blException.addException("panNumber","Pan number length is zero");
throw blException;
}
EmployeeInterface dsEmployee=this.panNumberWiseEmployeesMap.get(panNumber.toUpperCase());
if(dsEmployee==null)
{
blException.addException("panNumber","Invalid pan number "+panNumber);
throw blException;
}
DesignationInterface dsDesignation=dsEmployee.getDesignation();
DesignationInterface designation=new Designation();
EmployeeInterface employee;
employee=new Employee();
designation.setCode(dsDesignation.getCode());
designation.setTitle(dsDesignation.getTitle());
employee.setEmployeeId(dsEmployee.getEmployeeId());
employee.setName(dsEmployee.getName());
employee.setDesignation(designation);
employee.setDateOfBirth(dsEmployee.getDateOfBirth());
employee.setGender((dsEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
employee.setIsIndian(dsEmployee.getIsIndian());
employee.setBasicSalary(dsEmployee.getBasicSalary());
employee.setPANNumber(dsEmployee.getPANNumber());
employee.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return employee;
}
public EmployeeInterface getEmployeeByAadharCardNumber(String aadharCardNumber) throws BLException
{
BLException blException=new BLException();
if(aadharCardNumber==null)
{
blException.addException("aadharCardNumber","Aadhar card number required");
throw blException;
}
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addException("aadharCardNumber","Aadhar card number length is zero");
throw blException;
}
EmployeeInterface dsEmployee=this.aadharCardNumberWiseEmployeesMap.get(aadharCardNumber.toUpperCase());
if(dsEmployee==null)
{
blException.addException("aadharCardNumber","Invalid aadhar card number "+aadharCardNumber);
throw blException;
}
DesignationInterface dsDesignation=dsEmployee.getDesignation();
DesignationInterface designation=new Designation();
EmployeeInterface employee;
employee=new Employee();
designation.setCode(dsDesignation.getCode());
designation.setTitle(dsDesignation.getTitle());
employee.setEmployeeId(dsEmployee.getEmployeeId());
employee.setDateOfBirth(dsEmployee.getDateOfBirth());
employee.setDesignation(designation);
employee.setGender((dsEmployee.getGender()=='M')?GENDER.MALE:GENDER.FEMALE);
employee.setIsIndian(dsEmployee.getIsIndian());
employee.setBasicSalary(dsEmployee.getBasicSalary());
employee.setPANNumber(dsEmployee.getPANNumber());
employee.setAadharCardNumber(dsEmployee.getAadharCardNumber());
return employee;
}
public boolean employeeEmployeeIdExists(String employeeId) throws BLException
{
BLException blException=new BLException();
if(employeeId==null)
{
blException.addException("employeeId","Employee id required");
throw blException;
}
employeeId=employeeId.trim();
if(employeeId.length()==0)
{
blException.addException("employeeId","Employee id length is zero");
throw blException;
}
return this.employeeIdWiseEmployeesMap.containsKey(employeeId.toUpperCase());
}
public boolean employeePANNumberExists(String panNumber) throws BLException
{
BLException blException=new BLException();
if(panNumber==null)
{
blException.addException("panNumber","Pan number required");
throw blException;
}
panNumber=panNumber.trim();
if(panNumber.length()==0)
{
blException.addException("panNumber","Pan number length is zero");
throw blException;
}
return this.panNumberWiseEmployeesMap.containsKey(panNumber.toUpperCase());
}
public boolean employeeAadharCardNumberExists(String aadharCardNumber) throws BLException
{
BLException blException=new BLException();
if(aadharCardNumber==null)
{
blException.addException("aadharCardNumber","Aadhar card number required");
throw blException;
}
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addException("aadharCardNumber","Aadhar card number length is zero");
throw blException;
}
return this.aadharCardNumberWiseEmployeesMap.containsKey(aadharCardNumber.toUpperCase());
}
public int getEmployeeCount() throws BLException
{
return this.employeesSet.size();
}
public int getEmployeeCountByDesignationCode(int designationCode) throws BLException
{
BLException blException=new BLException();
if(designationCode<=0 || !(DesignationManager.getDesignationManager().designationCodeExists(designationCode)))
{
blException.addException("code","Invalid designation code "+designationCode);
throw blException;
}
Set<EmployeeInterface> ets;
ets=this.designationCodeWiseEmployeesMap.get(designationCode);
if(ets==null) return 0;
return ets.size();
}
}