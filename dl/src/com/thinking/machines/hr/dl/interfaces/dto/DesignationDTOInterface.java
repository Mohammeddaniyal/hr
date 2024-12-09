package com.thinking.machines.hr.dl.interfaces.dto;
public interface DesignationDTOInterface extends Comparable<DesignationDTOInterface>,java.io.Serializable
{
public void setCode(int code);
public void setTitle(String title);
public int getCode();
public String getTitle();
}