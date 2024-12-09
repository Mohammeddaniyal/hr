package com.thinking.machines.hr.bl.interfaces.pojo;
public interface DesignationInterface extends Comparable<DesignationInterface>,java.io.Serializable
{
public void setCode(int code);
public void setTitle(String title);
public int getCode();
public String getTitle();
}