package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
public class DesignationManager implements DesignationManagerInterface
{
private Map<Integer,DesignationInterface> codeWiseDesignationsMap;
private Map<String,DesignationInterface> titleWiseDesignationsMap;
private Set<DesignationInterface> designationsSet;
private static DesignationManagerInterface designationManager=null;
private DesignationManager() throws BLException
{
populateDataStructures();
}
private void populateDataStructures() throws BLException
{
this.codeWiseDesignationsMap=new HashMap<>();
this.titleWiseDesignationsMap=new HashMap<>();
this.designationsSet=new TreeSet<>();
try
{
Set<DesignationDTOInterface> dlDesignations;
dlDesignations=new DesignationDAO().getAll();
DesignationInterface dsDesignation;
for(DesignationDTOInterface dlDesignation:dlDesignations)
{
dsDesignation=new Designation();
dsDesignation.setCode(dlDesignation.getCode());
dsDesignation.setTitle(dlDesignation.getTitle());
this.codeWiseDesignationsMap.put(dlDesignation.getCode(),dsDesignation);
this.titleWiseDesignationsMap.put(dlDesignation.getTitle().toUpperCase(),dsDesignation);
this.designationsSet.add(dsDesignation);
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public static DesignationManagerInterface getDesignationManager() throws BLException
{
if(designationManager==null) designationManager=new DesignationManager();
return designationManager;
}
public void addDesignation(DesignationInterface designation) throws BLException
{
BLException blException=new BLException();
if(designation==null)
{
blException.setGenericException("Designation is null");
throw blException;
}
int code=designation.getCode();
String title=designation.getTitle();
if(code!=0)
{
blException.addException("code","Code must be zero");
}
if(title==null)
{
blException.addException("title","Title is null");
title="";
}
else
{
title=title.trim();
if(title.length()==0) blException.addException("title","Title length is zero");
}
if(title.length()>0)
{
if(this.titleWiseDesignationsMap.containsKey(title.toUpperCase())) blException.addException("title","Designation title : "+title+" exists.");
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
DesignationDTOInterface designationDTO;
designationDTO=new DesignationDTO();
designationDTO.setTitle(title);
DesignationInterface dsDesignation;
dsDesignation=new Designation();
dsDesignation.setTitle(title);
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
designationDAO.add(designationDTO);
designation.setCode(designationDTO.getCode());
dsDesignation.setCode(designationDTO.getCode());
this.codeWiseDesignationsMap.put(dsDesignation.getCode(),dsDesignation);
this.titleWiseDesignationsMap.put(dsDesignation.getTitle().toUpperCase(),dsDesignation);
this.designationsSet.add(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void updateDesignation(DesignationInterface designation) throws BLException
{
BLException blException=new BLException();
if(designation==null)
{
blException.setGenericException("Designation is null");
throw blException;
}
int code=designation.getCode();
String title=designation.getTitle();
if(code<=0)
{
blException.addException("code","Invalid code : "+code);
}
if(code>0 && (!this.codeWiseDesignationsMap.containsKey(code)))
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
if(title==null)
{
blException.addException("title","Title is null");
title="";
}
else
{
title=title.trim();
if(title.length()==0)
{
blException.addException("title","Title length is zero");
}
}
if(title.length()>0)
{
DesignationInterface d=this.titleWiseDesignationsMap.get(title.toUpperCase());
if(d!=null && code!=d.getCode())
{
blException.addException("title","Desigantion title : "+title+" exists.");
}
}
if(blException.hasExceptions())
{
throw blException;
}
try
{
DesignationDTOInterface dlDesignation;
dlDesignation=new DesignationDTO();
dlDesignation.setCode(code);
dlDesignation.setTitle(title);
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
designationDAO.update(dlDesignation);
DesignationInterface dsDesignation=this.codeWiseDesignationsMap.get(code);
this.codeWiseDesignationsMap.remove(code);
this.titleWiseDesignationsMap.remove(dsDesignation.getTitle().toUpperCase());
this.designationsSet.remove(dsDesignation);
dsDesignation.setCode(code);
dsDesignation.setTitle(title);
this.codeWiseDesignationsMap.put(code,dsDesignation);
this.titleWiseDesignationsMap.put(title.toUpperCase(),dsDesignation);
this.designationsSet.add(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void removeDesignation(int code) throws BLException
{
BLException blException=new BLException();
if(code<=0)
{
blException.addException("code","Code is zero");
}
else
{
if(!this.codeWiseDesignationsMap.containsKey(code)) 
{
blException.addException("code","Invalid code : "+code);
}
}
if(EmployeeManager.getEmployeeManager().isDesignationAlloted(code))
{
System.out.println("Designation alloted");
blException.addException("designation","Designation is alloted cannot remove");
}
if(blException.hasExceptions())
{
throw blException;
}

try
{
DesignationDAOInterface designationDAO;
designationDAO=new DesignationDAO();
designationDAO.delete(code);
DesignationInterface dsDesignation=this.codeWiseDesignationsMap.get(code);
this.codeWiseDesignationsMap.remove(code);
this.titleWiseDesignationsMap.remove(dsDesignation.getTitle().toUpperCase());
this.designationsSet.remove(dsDesignation);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
}
}
public Set<DesignationInterface> getDesignations()
{
Set<DesignationInterface> designations;
designations=new TreeSet<>();
this.designationsSet.forEach((d)->{
DesignationInterface designation;
designation=new Designation();
designation.setCode(d.getCode());
designation.setTitle(d.getTitle());
designations.add(designation);
});
return designations;
}
public DesignationInterface getDesignationByCode(int code) throws BLException
{
BLException blException=new BLException();
if(code<=0)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
DesignationInterface d=this.codeWiseDesignationsMap.get(code);
if(d==null)
{
blException.addException("code","Invalid code : "+code);
throw blException;
}
DesignationInterface designation=new Designation();
designation.setCode(d.getCode());
designation.setTitle(d.getTitle());
return designation;
}
DesignationInterface getDSDesignationByCode(int code)
{
DesignationInterface designation=this.codeWiseDesignationsMap.get(code);
return designation;
}
public DesignationInterface getDesignationByTitle(String title) throws BLException
{
BLException blException=new BLException();
if(title==null)
{
blException.addException("title","Invalid Designation : "+title);
throw blException;
}
else
{
title=title.trim();
if(title.length()==0)
{
blException.addException("title","Title length is zero");
throw blException;
}
}
DesignationInterface d=this.titleWiseDesignationsMap.get(title.toUpperCase());
if(d==null)
{
blException.addException("title","Invalid Designation : "+title);
throw blException;
}
DesignationInterface designation=new Designation();
designation.setCode(d.getCode());
designation.setTitle(d.getTitle());
return designation;
}
public boolean designationCodeExists(int code) 
{
return this.codeWiseDesignationsMap.containsKey(code);
}
public boolean designationTitleExists(String title)
{
return this.titleWiseDesignationsMap.containsKey(title.toUpperCase());
}
public int getDesignationCount()
{
return this.designationsSet.size();
}
}