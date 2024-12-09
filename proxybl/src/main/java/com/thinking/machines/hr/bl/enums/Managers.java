package com.thinking.machines.hr.bl.enums;
public class Managers
{
public enum Manager{DESIGNATION,EMPLOYEE};
public enum Designation{ADD,UPDATE,DELETE,GETALL,GETBYCODE,GETBYTITLE,CODEEXISTS,TITLEEXISTS,GETCOUNT};
public static String getManagerType(Manager manager)
{
if(manager==Manager.DESIGNATION)
{
return "DesignationManager";
}
return "";
}
public static String getAction(Designation designation)
{
if(designation==Designation.ADD)
{
return "add";
}
if(designation==Designation.GETALL)
{
return "getAll";
}
if(designation==Designation.UPDATE)
{
return "update";
}
if(designation==Designation.DELETE)
{
return "delete";
}
if(designation==Designation.GETBYCODE)
{
return "getByCode";
}
if(designation==Designation.GETBYTITLE)
{
return "getByTitle";
}
if(designation==Designation.CODEEXISTS)
{
return "codeExists";
}
if(designation==Designation.TITLEEXISTS)
{
return "titleExists";
}
if(designation==Designation.GETCOUNT)
{
return "getCount";
}

return "";
}
}