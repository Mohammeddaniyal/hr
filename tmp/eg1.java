class Employee
{
enum GENDER{MALE,FEMALE};
private char gender;
public void setGender(GENDER gender)
{
if(gender==GENDER.MALE) this.gender='M';
else this.gender='F';
}
public char getGender()
{
return this.gender;
}
}
class psp
{
public static void main(String gg[])
{
Employee e;
e=new Employee();
e.setGender(Employee.GENDER.FEMALE);
char gender;
gender=e.getGender();
Employee e1;
e1=new Employee();
e1.setGender((gender=='M')?Employee.GENDER.MALE:Employee.GENDER.FEMALE);
System.out.println(e1.getGender());
}
}