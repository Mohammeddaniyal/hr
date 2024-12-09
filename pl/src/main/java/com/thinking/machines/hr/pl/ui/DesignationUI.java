package com.thinking.machines.hr.pl.ui;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import com.itextpdf.kernel.font.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;
import com.itextpdf.kernel.pdf.*;

import java.io.*;
public class DesignationUI extends JFrame
{
private JLabel titleLabel;
private JLabel searchLabel;
private JLabel searchErrorLabel;
private JTextField searchTextField;
private JButton clearSearchTextFieldButton;
private JTable designationTable;
private JScrollPane scrollPane;
private DesignationModel designationModel;
private DesignationPanel designationPanel;
private Container container;
private enum MODE{VIEW,ADD,EDIT,DELETE,EXPORT_TO_PDF};
private MODE mode;
public DesignationUI()
{
initComponents();
setAppereance();
addListeners();
setViewMode();
designationPanel.setViewMode();
}
private void initComponents()
{
titleLabel=new JLabel("Designations");
searchLabel=new JLabel("Search");
searchTextField=new JTextField();
clearSearchTextFieldButton=new JButton("X");
searchErrorLabel=new JLabel("");
designationModel=new DesignationModel();
designationTable=new JTable(designationModel);
scrollPane=new JScrollPane(designationTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
designationPanel=new DesignationPanel();
container=getContentPane();
}
private void setAppereance()
{
Font titleFont=new Font("Verdana",Font.BOLD,18);
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font searchErrorFont=new Font("Verdana",Font.BOLD,12);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
Font columnHeaderFont=new Font("Verdana",Font.BOLD,16);
titleLabel.setFont(titleFont);

searchLabel.setFont(captionFont);
searchTextField.setFont(dataFont);
searchErrorLabel.setFont(searchErrorFont);
searchErrorLabel.setForeground(Color.red);
designationTable.setFont(dataFont);


designationTable.setRowHeight(35);
designationTable.getColumnModel().getColumn(0).setPreferredWidth(20);
designationTable.getColumnModel().getColumn(1).setPreferredWidth(400);
JTableHeader header=designationTable.getTableHeader();
header.setFont(columnHeaderFont);
header.setReorderingAllowed(false);
header.setResizingAllowed(false);
designationTable.setRowSelectionAllowed(true);
designationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

container.setLayout(null);
int lm=0;
int tm=0;
titleLabel.setBounds(lm+10,tm+10,200,40);
searchErrorLabel.setBounds(lm+10+100+400+10-75,tm+10+20+10,100,20);
searchLabel.setBounds(lm+10,tm+10+40+10,100,30);
searchTextField.setBounds(lm+10+100+5,tm+10+40+10,400,30);
clearSearchTextFieldButton.setBounds(lm+10+100+400+5,tm+10+40+10,50,30);
scrollPane.setBounds(lm+10,tm+10+40+10+30+10,565,300);
designationPanel.setBounds(lm+10,tm+10+40+10+30+10+300+10,565,200);
container.add(titleLabel);
container.add(searchErrorLabel);
container.add(searchLabel);
container.add(searchTextField);
container.add(clearSearchTextFieldButton);
container.add(scrollPane);
container.add(designationPanel);
int w=600;
int h=660;
setSize(w,h);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2),(d.height/2)-(h/2));
}

private void addListeners()
{
searchTextField.getDocument().addDocumentListener(
new DocumentListener()
{
public void changedUpdate(DocumentEvent de)
{
searchDesignation();
}
public void removeUpdate(DocumentEvent de)
{
searchDesignation();
}
public void insertUpdate(DocumentEvent de)
{
searchDesignation();
}
});
clearSearchTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
designationTable.getSelectionModel().addListSelectionListener(
new ListSelectionListener()
{
public void valueChanged(ListSelectionEvent lse)
{
int selectedRowIndex=designationTable.getSelectedRow();
try
{
designationPanel.setDesignation(designationModel.getDesignationAt(selectedRowIndex));
}catch(BLException blException)
{
designationPanel.clearDesignation();
}
}
});
}

private void searchDesignation()
{
searchErrorLabel.setText("");
String title=searchTextField.getText().trim();
if(title.length()==0) return;
int rowIndex;
try
{
rowIndex=designationModel.indexOfTitle(title,true);
}catch(BLException blException)
{
searchErrorLabel.setText("Not Found");
return;
}
designationTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=designationTable.getCellRect(rowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
}

private void setViewMode()
{
this.mode=MODE.VIEW;
if(designationModel.getRowCount()>0)
{
searchTextField.setEnabled(true);
clearSearchTextFieldButton.setEnabled(true);
designationTable.setEnabled(true);
}
else
{
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
}
private void setAddMode()
{
this.mode=MODE.ADD;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setEditMode()
{
this.mode=MODE.EDIT;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setDeleteMode()
{
this.mode=MODE.DELETE;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}
private void setExportToPDFMode()
{
this.mode=MODE.EXPORT_TO_PDF;
searchTextField.setEnabled(false);
clearSearchTextFieldButton.setEnabled(false);
designationTable.setEnabled(false);
}

//inner class starts here
public class DesignationPanel extends JPanel
{
private JLabel titleCaptionLabel;
private JLabel titleLabel;
private JTextField titleTextField;
private JButton clearTitleTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private JButton cancelButton;
private JPanel buttonsPanel;
private ImageIcon logoIcon;
private ImageIcon addIcon;
private ImageIcon editIcon;
private ImageIcon deleteIcon;
private ImageIcon exportToPDFIcon;
private ImageIcon saveIcon;
private ImageIcon cancelIcon;
private ImageIcon clearIcon;
private DesignationInterface designation;
DesignationPanel()
{
setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
initComponents();
setAppereance();
addListeners();
}
private void initComponents()
{
designation=null;
titleCaptionLabel=new JLabel("Designation");
titleLabel=new JLabel("");
titleTextField=new JTextField();

buttonsPanel=new JPanel();

//logoIcon=new ImageIcon("c:\javaprojects\hr\icon\logo.png"); // confused about front or back slash here
//logoIcon=new ImageIcon("c:"+File.separator+"javaprojects"+File.separator+"hr"+File.separator+"icon"+File.separator+"logo.png")


logoIcon=new ImageIcon(this.getClass().getResource("/icons/logo.png"));
setIconImage(logoIcon.getImage());
addIcon=new ImageIcon(this.getClass().getResource("/icons/add_icon.png"));
editIcon=new ImageIcon(this.getClass().getResource("/icons/edit_icon.png"));
deleteIcon=new ImageIcon(this.getClass().getResource("/icons/delete_icon_2.png"));
exportToPDFIcon=new ImageIcon(this.getClass().getResource("/icons/exportToPDF_icon.png"));
saveIcon=new ImageIcon(this.getClass().getResource("/icons/save_icon.png"));
cancelIcon=new ImageIcon(this.getClass().getResource("/icons/cancel_icon.png"));
clearIcon=new ImageIcon(this.getClass().getResource("/icons/cancel_icon.png"));

addButton=new JButton(addIcon);
editButton=new JButton(editIcon);
deleteButton=new JButton(deleteIcon);
exportToPDFButton=new JButton(exportToPDFIcon);
cancelButton=new JButton(cancelIcon);
clearTitleTextFieldButton=new JButton(clearIcon);
}
private void setAppereance()
{
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
titleCaptionLabel.setFont(captionFont);
titleLabel.setFont(dataFont);
titleTextField.setFont(dataFont);
setLayout(null);
int lm=0;
int tm=0;
titleCaptionLabel.setBounds(lm+10,tm+20,110,30);
titleLabel.setBounds(lm+110+5,tm+20,400,30);
titleTextField.setBounds(lm+110+5,tm+20,350,30);
clearTitleTextFieldButton.setBounds(lm+10+400+5+110-60,tm+20,50,30);


titleTextField.setVisible(false);


cancelButton.setEnabled(false);


buttonsPanel.setLayout(null);
buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
buttonsPanel.setBounds(lm+50,tm+20+30+30,465,75);

addButton.setBounds(lm+70,tm+12,50,50);

editButton.setBounds(lm+70+50+20,tm+12,50,50);
deleteButton.setBounds(lm+70+50+50+20+20,tm+12,50,50);
cancelButton.setBounds(lm+70+50+50+50+20+20+20,tm+12,50,50);
exportToPDFButton.setBounds(lm+70+50+50+50+50+20+20+20+20,tm+12,50,50);


addButton.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
editButton.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
deleteButton.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));
exportToPDFButton.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));

cancelButton.setBorder(BorderFactory.createLineBorder(new Color(150,150,150)));


buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(deleteButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(exportToPDFButton);


add(titleCaptionLabel);
add(titleLabel);
add(titleTextField);
add(clearTitleTextFieldButton);
add(buttonsPanel);
}
private void setDesignation(DesignationInterface designation)
{
this.designation=designation;
titleLabel.setText(designation.getTitle());
}
private void clearDesignation()
{
this.designation=null;
titleLabel.setText("");
}



private void setViewMode()
{
DesignationUI.this.setViewMode();
addButton.setIcon(addIcon);
editButton.setIcon(editIcon);
titleTextField.setVisible(false);
clearTitleTextFieldButton.setVisible(false);
titleLabel.setVisible(true);
addButton.setEnabled(true);
cancelButton.setEnabled(false);
if(designationModel.getRowCount()>0)
{
editButton.setEnabled(true);
deleteButton.setEnabled(true);
exportToPDFButton.setEnabled(true);
}
else
{
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}
}
private void setAddMode()
{
DesignationUI.this.setAddMode();
this.titleTextField.setText("");
addButton.setIcon(saveIcon);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
cancelButton.setEnabled(true);
titleLabel.setVisible(false);
titleTextField.setVisible(true);
clearTitleTextFieldButton.setVisible(true);
}

private void setEditMode()
{
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>=designationModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select designation to edit");
return;
}
titleTextField.setText(designation.getTitle());
DesignationUI.this.setEditMode();
addButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
cancelButton.setEnabled(true);
titleLabel.setVisible(false);
titleTextField.setVisible(true);
clearTitleTextFieldButton.setVisible(true);

}

private void setDeleteMode()
{
if(designationTable.getSelectedRow()<0 || designationTable.getSelectedRow()>=designationModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select designation to delete");
return;
}
DesignationUI.this.setDeleteMode();
addButton.setEnabled(false);
editButton.setEnabled(false);
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
cancelButton.setEnabled(false);
titleLabel.setVisible(true);
titleTextField.setVisible(false);
removeDesignation();
this.setViewMode();
}

private void setExportToPDFMode()
{
if(designationModel.getRowCount()<=0)
{
JOptionPane.showMessageDialog(this,"No Designation available to export");
return;
}
DesignationUI.this.setExportToPDFMode();
this.addButton.setEnabled(false);
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.exportToPDFButton.setEnabled(false);
this.titleLabel.setVisible(false);
this.titleTextField.setVisible(false);
JFileChooser jFileChooser=new JFileChooser();
jFileChooser.setCurrentDirectory(new File("."));
int selectedOption=jFileChooser.showSaveDialog(this);
if(selectedOption==jFileChooser.APPROVE_OPTION)
{
try
{
File selectedFile=jFileChooser.getSelectedFile();
String pdfFile=selectedFile.getAbsolutePath();
if(pdfFile.endsWith(".")) pdfFile+="pdf";
else if(pdfFile.endsWith(".pdf")==false) pdfFile+=".pdf";
File file=new File(pdfFile);
File parent=new File(file.getParent());
if(parent.exists()==false || parent.isDirectory()==false)
{
JOptionPane.showMessageDialog(this,"Incorrect Path : "+file.getAbsolutePath());
return;
}
designationModel.exportToPDF(file);
JOptionPane.showMessageDialog(this,"Data exported to PDF : "+file.getAbsolutePath());
}catch(BLException blException)
{
if(blException.hasGenericException()) JOptionPane.showMessageDialog(this,blException.getGenericException());
}catch(Exception exception)
{
System.out.println(exception);
}
}
}

private boolean addDesignation()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Designation required");
titleTextField.requestFocus();
return false;
}
DesignationInterface d;
d=new Designation();
d.setTitle(title);
try
{
designationModel.add(d);
int addedRowIndex=0;
try
{
addedRowIndex=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
//do nothing
}
designationTable.setRowSelectionInterval(addedRowIndex,addedRowIndex);
Rectangle rectangle=designationTable.getCellRect(addedRowIndex,0,true);
designationTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
titleTextField.requestFocus();
return false;
}

}

private boolean updateDesignation()
{
String title=titleTextField.getText().trim();
if(title.length()==0)
{
JOptionPane.showMessageDialog(this,"Designation  required");
titleTextField.requestFocus();
return false;
}
try
{
int index=0;
DesignationInterface d=new Designation();
d.setCode(this.designation.getCode());
d.setTitle(title);
designationModel.update(d);
try
{
index=designationModel.indexOfDesignation(d);
}catch(BLException blException)
{
// do nothing
}
designationTable.setRowSelectionInterval(index,index);
Rectangle rectangle=designationTable.getCellRect(index,0,true);
designationTable.scrollRectToVisible(rectangle);
return true;
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
titleTextField.requestFocus();
return false;
}
}

private void removeDesignation()
{
int selectedOption=JOptionPane.showConfirmDialog(this,"Do you want to delete "+this.titleLabel.getText()+" ?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
if(selectedOption==JOptionPane.NO_OPTION) return;
try
{
String title=this.designation.getTitle();
designationModel.remove(designation.getCode());
JOptionPane.showMessageDialog(this,title+" deleted");
this.clearDesignation();
}catch(BLException blException)
{
if(blException.hasGenericException())
{
JOptionPane.showMessageDialog(this,blException.getGenericException());
}
if(blException.hasException("title"))
{
JOptionPane.showMessageDialog(this,blException.getException("title"));
}
if(blException.hasException("designation"))
{
JOptionPane.showMessageDialog(this,blException.getException("designation"));
}

}
}




public void addListeners()
{
addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
if(mode==MODE.VIEW)
{
setAddMode();
}
else
{
if(addDesignation())
{
setViewMode();
}
}
}
});


this.editButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent actionEvent)
{
if(mode==MODE.VIEW)
{
setEditMode();
}
else
{
if(updateDesignation())
{
setViewMode();
}
}
}
});


this.deleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
setDeleteMode();
}
});

this.cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
setViewMode();
}
});

exportToPDFButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent actionEvent)
{
setExportToPDFMode();
setViewMode();
}
});

clearTitleTextFieldButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent actionEvent)
{
titleTextField.setText("");
titleTextField.requestFocus();
}
});

}// addListeners ends here


}//inner class ends here
}