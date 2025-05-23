package com.thinking.machines.hr.bl.exceptions;
import java.util.*;
public class BLException extends Exception
{
private String genericException;
private Map<String,String> exceptions;
public BLException()
{
this.genericException=null;
this.exceptions=new HashMap<>();
}
public void setGenericException(String genericException)
{
this.genericException=genericException;
}
public String getGenericException()
{
if(this.genericException==null) return "";
return this.genericException;
}
public void addException(String property,String exception)
{
this.exceptions.put(property,exception);
}
public void removeException(String property)
{
this.exceptions.remove(property);
}
public int getExceptionCount()
{
if(this.genericException!=null) return this.exceptions.size()+1;
return this.exceptions.size();
}
public String getException(String property)
{
return this.exceptions.get(property);
}
public boolean hasExceptions()
{
return this.getExceptionCount()>0;
}
public List<String> getProperties()
{
List<String> properties;
properties=new ArrayList<>();
this.exceptions.forEach((k,v)->{
properties.add(k);
});
return properties;
}
public boolean hasException(String property)
{
return this.exceptions.containsKey(property);
}
public boolean hasGenericException()
{
return this.genericException!=null;
}
public String getMessage()
{
if(this.genericException==null) return "";
return this.genericException;
}
}