package com.thinking.machines.hr.pl.model;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import javax.swing.table.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;
import com.itextpdf.kernel.pdf.*;

import com.itextpdf.io.font.constants.*;
import com.itextpdf.io.image.*;
import com.itextpdf.layout.borders.*;
import java.io.*;
public class DesignationModel extends AbstractTableModel
{
private java.util.List<DesignationInterface> designations;
private String[] columnTitle;
private DesignationManagerInterface designationManager;
private void populateDataStructures()
{
columnTitle=new String[2];
columnTitle[0]="S.No.";
columnTitle[1]="Designation";
try
{
designationManager=DesignationManager.getDesignationManager();
}catch(BLException blException)
{
//?????? what to do here
}

Set<DesignationInterface> blDesignations;
blDesignations=designationManager.getDesignations();
this.designations=new LinkedList<>();
for(DesignationInterface designation:blDesignations)
{
this.designations.add(designation);
}
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
}
public int getRowCount()
{
return this.designations.size();
}
public int getColumnCount()
{
return this.columnTitle.length;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
return this.designations.get(rowIndex).getTitle();
}
public String getColumnName(int columnIndex)
{
return columnTitle[columnIndex];
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0) return Integer.class;
return String.class;
}
public DesignationModel()
{
populateDataStructures();
}

//application specific
public int indexOfDesignation(DesignationInterface designation) throws BLException
{
Iterator<DesignationInterface> iterator=this.designations.iterator();
int index=0;
DesignationInterface d;
while(iterator.hasNext())
{
d=iterator.next();
if(d.equals(designation))
{
return index;
}
index++;
}
BLException blException;
blException=new BLException();
blException.setGenericException("Invalid Designation");
throw blException;
}
public int indexOfTitle(String title,boolean partialLeftSearch) throws BLException
{

Iterator<DesignationInterface> iterator=this.designations.iterator();
int index=0;
DesignationInterface d;
while(iterator.hasNext())
{
d=iterator.next();
if(partialLeftSearch && d.getTitle().toUpperCase().startsWith(title.toUpperCase()))
{
return index;
}
else if(title.equalsIgnoreCase(d.getTitle()))
{
return index;
}
index++;
}
BLException blException;
blException=new BLException();
blException.setGenericException("Invalid title : "+title);
throw blException;
}
public void add(DesignationInterface designation) throws BLException
{
designationManager.addDesignation(designation);
this.designations.add(designation);
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}
public void update(DesignationInterface designation) throws BLException
{
designationManager.updateDesignation(designation);
this.designations.remove(indexOfDesignation(designation));
this.designations.add(designation);
Collections.sort(this.designations,new Comparator<DesignationInterface>(){
public int compare(DesignationInterface left,DesignationInterface right)
{
return left.getTitle().toUpperCase().compareTo(right.getTitle().toUpperCase());
}
});
fireTableDataChanged();
}
public void remove(int code) throws BLException
{
designationManager.removeDesignation(code);
Iterator<DesignationInterface> iterator=this.designations.iterator();
int index=0;
while(iterator.hasNext())
{
if(code==iterator.next().getCode())
{
break;
}
index++;
}
//NOTE: Only for precaution measure 
//if index wrong, bl layer will already raised the exception
//so no chance coming here(in case of exception)
if(index==this.designations.size())
{
BLException blException;
blException=new BLException();
blException.setGenericException("Invalid code : "+code);
throw blException;
}
this.designations.remove(index);
fireTableDataChanged();
}

public void exportToPDF(File file) throws BLException
{
try
{
if(file.exists()) file.delete();
PdfWriter pdfWriter=new PdfWriter(file);
PdfDocument pdfDocument=new PdfDocument(pdfWriter);
Document document=new Document(pdfDocument);
Image logo=new Image(ImageDataFactory.create(this.getClass().getResource("/icons/logo.png")));
Paragraph logoPara=new Paragraph();
logoPara.add(logo);
Paragraph companyNamePara=new Paragraph();
companyNamePara.add("ABCD Corporation");
PdfFont companyNameFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
companyNamePara.setFont(companyNameFont);
companyNamePara.setFontSize(18);

Paragraph reportTitlePara=new Paragraph("List of Designations");
PdfFont reportTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
reportTitlePara.setFont(reportTitleFont);
reportTitlePara.setFontSize(15);

PdfFont pageNumberFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont columnTitleFont=PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
PdfFont dataFont=PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
Paragraph columnTitle1=new Paragraph(getColumnName(0));
columnTitle1.setFont(columnTitleFont);
columnTitle1.setFontSize(14);
Paragraph columnTitle2=new Paragraph(getColumnName(1));
columnTitle2.setFont(columnTitleFont);
columnTitle2.setFontSize(14);



Paragraph pageNumberPara;
Paragraph dataParagraph;
float topTableColumnWidths[]={1,5};
float dataTableColumnWidths[]={1,5};
Table topTable;
Table pageNumberTable;
Table dataTable=null;
int sno=0;
int x=0;
int pageNumber=0;
boolean newPage=true;
int pageSize=25;
Cell cell;
int numberOfPages=this.designations.size()/pageSize;
if((this.designations.size()%pageSize)!=0) numberOfPages++;

DesignationInterface designation;
while(x<this.designations.size())
{
if(newPage==true)
{
//create new page header
pageNumber++;
topTable=new Table(UnitValue.createPercentArray(topTableColumnWidths));
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(logoPara);
topTable.addCell(cell);
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(companyNamePara);
cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
topTable.addCell(cell);
document.add(topTable);
pageNumberPara=new Paragraph("Page : "+pageNumber+"/"+numberOfPages);
pageNumberPara.setFont(pageNumberFont);
pageNumberPara.setFontSize(13);
pageNumberTable=new Table(1);
pageNumberTable.setWidth(UnitValue.createPercentValue(100));
cell=new Cell();
cell.setBorder(Border.NO_BORDER);
cell.add(pageNumberPara);
cell.setTextAlignment(TextAlignment.RIGHT);
pageNumberTable.addCell(cell);
document.add(pageNumberTable);
dataTable=new Table(UnitValue.createPercentArray(dataTableColumnWidths));
dataTable.setWidth(UnitValue.createPercentValue(100));
cell=new Cell(1,2);
cell.add(reportTitlePara);
cell.setTextAlignment(TextAlignment.CENTER);
dataTable.addHeaderCell(cell);
dataTable.addHeaderCell(columnTitle1);
dataTable.addHeaderCell(columnTitle2);
newPage=false;
}
designation=this.designations.get(sno);
//add row to table
sno++;
cell=new Cell();
dataParagraph=new Paragraph(String.valueOf(sno));
dataParagraph.setFont(dataFont);
dataParagraph.setFontSize(14);
cell.add(dataParagraph);
cell.setTextAlignment(TextAlignment.RIGHT);
dataTable.addCell(cell);

cell=new Cell();
dataParagraph=new Paragraph(designation.getTitle());
dataParagraph.setFont(dataFont);
dataParagraph.setFontSize(14);
cell.add(dataParagraph);
dataTable.addCell(cell);
x++;
if(sno%pageSize==0 || x==this.designations.size())
{
// create footer
document.add(dataTable);
document.add(new Paragraph("Software by : Thinking Machines"));
if(x<this.designations.size())
{
//add new page to document
document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
newPage=true;
}
}
}


document.close();
}catch(IOException ioException)
{
BLException blException;
blException=new BLException();
blException.setGenericException(ioException.getMessage());
throw blException;
}
}

public DesignationInterface getDesignationAt(int index) throws BLException
{
if(index<0 || index>=this.designations.size()) 
{
BLException blException;
blException=new BLException();
blException.setGenericException("Invalid index : "+index);
throw blException;
}
return this.designations.get(index);
}
}