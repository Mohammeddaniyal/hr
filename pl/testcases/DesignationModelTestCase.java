import com.thinking.machines.hr.pl.model.*;
import javax.swing.*;
import java.awt.*;
class DesignationModelTestCase extends JFrame
{
private JTable tb;
private JScrollPane jsp;
private DesignationModel designationModel;
private Container container;
public DesignationModelTestCase()
{
designationModel=new DesignationModel();
tb=new JTable(designationModel);
jsp=new JScrollPane(tb,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
container.setLayout(new BorderLayout());
container.add(jsp);
setLocation(100,100);
setSize(500,300);
setVisible(true);
}

public static void main(String gg[])
{
DesignationModelTestCase dmtc=new DesignationModelTestCase();
}
}