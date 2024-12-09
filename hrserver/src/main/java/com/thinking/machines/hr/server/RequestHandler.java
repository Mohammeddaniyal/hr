package com.thinking.machines.hr.server;
import com.thinking.machines.network.server.*;
import com.thinking.machines.network.common.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
public class RequestHandler implements RequestHandlerInterface
{
private DesignationManagerInterface designationManager;
public RequestHandler()
{
try
{
designationManager=DesignationManager.getDesignationManager();
}catch(BLException blExeption)
{
//do nothing
}
}
public Response process(Request request)
{
Response response=new Response();
String manager=request.getManager();
String action=request.getAction();
Object [] arguments=request.getArguments();
if(manager.equals("designationManager"))
{
if(designationManager==null)
{
//will implement later on
}
if(action.equals("getDesignations"))
{
Object result=designationManager.getDesignations();
response.setSuccess(true);
response.setResult(result);
response.setException(null);
}

if(action.equals("addDesignation"))
{
DesignationInterface designation=(DesignationInterface)arguments[0];
try
{
designationManager.addDesignation(designation);
response.setResult(designation);
response.setException(null);
response.setSuccess(true);
}catch(BLException blException)
{
response.setResult(null);
response.setSuccess(false);
response.setException(blException);
return response;
}
}


if(action.equals("updateDesignation"))
{
DesignationInterface designation=(DesignationInterface)arguments[0];
try
{
designationManager.updateDesignation(designation);
response.setResult(null);
response.setException(null);
response.setSuccess(true);
}catch(BLException blException)
{
response.setResult(null);
response.setSuccess(false);
response.setException(blException);
return response;
}
}
if(action.equals("removeDesignation"))
{
Integer code=(Integer)arguments[0];
try
{
designationManager.removeDesignation(code);
response.setResult(null);
response.setException(null);
response.setSuccess(true);
}catch(BLException blException)
{
response.setResult(null);
response.setSuccess(false);
response.setException(blException);
return response;
}
}


if(action.equals("getDesignationByCode"))
{
Integer code=(Integer)arguments[0];
try
{
DesignationInterface designation;
designation=designationManager.getDesignationByCode(code);
response.setResult(designation);
response.setException(null);
response.setSuccess(true);
}catch(BLException blException)
{
response.setResult(null);
response.setSuccess(false);
response.setException(blException);
return response;
}
}


if(action.equals("getDesignationByTitle"))
{
String title=(String)arguments[0];
try
{
DesignationInterface designation;
designation=designationManager.getDesignationByTitle(title);
response.setResult(designation);
response.setException(null);
response.setSuccess(true);
}catch(BLException blException)
{
response.setResult(null);
response.setSuccess(false);
response.setException(blException);
return response;
}
}

if(action.equals("getDesignationCount"))
{
int recordCount=designationManager.getDesignationCount();
response.setResult(recordCount);
response.setException(null);
response.setSuccess(true);
}

if(action.equals("designationCodeExists"))
{
Integer code=(Integer)arguments[0];
boolean exists;
exists=designationManager.designationCodeExists(code);
response.setResult(exists);
response.setException(null);
response.setSuccess(true);
}

if(action.equals("designationTitleExists"))
{
String title=(String)arguments[0];
boolean exists;
exists=designationManager.designationTitleExists(title);
response.setResult(exists);
response.setException(null);
response.setSuccess(true);
}


}//DesignationManager part ends here
return response;
}
}