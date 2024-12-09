package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.network.common.*;
import com.thinking.machines.network.client.*;
import com.thinking.machines.network.common.exceptions.*;
import java.util.*;
public class DesignationManager implements DesignationManagerInterface
{
private static DesignationManagerInterface designationManager=null;
private DesignationManager() throws BLException
{
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
if(blException.hasExceptions())
{
throw blException;
}
Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.ADD_DESIGNATION));
request.setArguments(designation);
NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
if(response.hasException())
{
blException=(BLException)response.getException();
throw blException;
}
DesignationInterface d=(DesignationInterface)response.getResult();
designation.setCode(d.getCode());
}catch(NetworkException networkException)
{
blException.setGenericException(networkException.getMessage());
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
if(blException.hasExceptions())
{
throw blException;
}

Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.UPDATE_DESIGNATION));
request.setArguments(designation);

NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
if(response.hasException())
{
blException=(BLException)response.getException();
throw blException;
}
}catch(NetworkException networkException)
{
blException.setGenericException(networkException.getMessage());
throw blException;
}
}

public void removeDesignation(int code) throws BLException
{
if(code<=0)
{
BLException blException=new BLException();
blException.addException("code","Invalid code : "+code);
throw blException;
}

/*
will done this part in future inshaallah
if(EmployeeManager.getEmployeeManager().isDesignationAlloted(code))
{
blException.addException("designation","Designation is alloted cannot remove");
}
*/
Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.REMOVE_DESIGNATION));
request.setArguments(code);
NetworkClient client=new NetworkClient();

try
{
Response response=client.send(request);
if(response.hasException())
{
BLException blException=new BLException();
blException=(BLException)response.getException();
throw blException;
}
}catch(NetworkException networkException)
{
BLException blException=new BLException();
blException.setGenericException(networkException.getMessage());
throw blException;
}

}
public Set<DesignationInterface> getDesignations() 
{
Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.GET_DESIGNATIONS));
NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
Set<DesignationInterface> designations=(Set<DesignationInterface>)response.getResult();
if(designations==null) System.out.println("Designations is null");
return designations;
}catch(NetworkException networkException)
{
throw new RuntimeException(networkException.getMessage());
}
}
public DesignationInterface getDesignationByCode(int code) throws BLException
{
if(code<=0)
{
BLException blException=new BLException();
blException.addException("code","Invalid code : "+code);
throw blException;
}
Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.GET_DESIGNATION_BY_CODE));
request.setArguments(new Integer(code));
NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
if(response.hasException())
{
BLException blException=new BLException();
blException=(BLException)response.getException();
throw blException;
}
DesignationInterface designation;
designation=(DesignationInterface)response.getResult();
return designation;
}catch(NetworkException networkException)
{
BLException blException=new BLException();
blException.setGenericException(networkException.getMessage());
throw blException;
}
}
public DesignationInterface getDesignationByTitle(String title) throws BLException
{
if(title==null)
{
BLException blException=new BLException();
blException.addException("title","Invalid Designation : "+title);
throw blException;
}
title=title.trim();
if(title.length()==0)
{
BLException blException=new BLException();
blException.addException("title","Title length is zero");
throw blException;
}

Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.GET_DESIGNATION_BY_TITLE));
request.setArguments(title);
NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
if(response.hasException())
{
BLException blException=new BLException();
blException=(BLException)response.getException();
throw blException;
}
return (DesignationInterface)response.getResult();
}catch(NetworkException networkException)
{
BLException blException;
blException=new BLException();
blException.setGenericException(networkException.getMessage());
throw blException;
}
}

public boolean designationCodeExists(int code) 
{
if(code<=0)
{
return false;
}
Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.DESIGNATION_CODE_EXISTS));
request.setArguments(code);
NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
Boolean exists=(Boolean)response.getResult();
return exists;
}catch(NetworkException networkException)
{
throw new RuntimeException(networkException.getMessage());
}
}
public boolean designationTitleExists(String title)
{
if(title==null)
{
return false;
}
title.trim();
if(title.length()==0)
{
return false;
}
Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.DESIGNATION_TITLE_EXISTS));
request.setArguments(title);
NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
Boolean exists=(Boolean)response.getResult();
return exists;
}catch(NetworkException networkException)
{
throw new RuntimeException(networkException.getMessage());
}
}
public int getDesignationCount()
{
Request request=new Request();
request.setManager(Managers.getManagerType(Managers.DESIGNATION));
request.setAction(Managers.getAction(Managers.Designation.GET_DESIGNATION_COUNT));
NetworkClient client=new NetworkClient();
try
{
Response response=client.send(request);
Integer count=(Integer)response.getResult();
return count.intValue();//no need to intValue, unboxing will be done implicitly
}catch(NetworkException networkException)
{
throw new RuntimeException(networkException.getMessage());
}
}
}