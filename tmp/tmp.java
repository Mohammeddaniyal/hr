import java.util.*;
class ddd implements Comparable<ddd>
{
public int code;
public String title;
public int hashCode()
{
return this.code;
}
public int compareTo(ddd d)
{
return this.code-d.code;
}
public boolean equals(Object other)
{
ddd d=(ddd)other;
return this.code==d.code;
}
}
class psp
{
public static void main(String gg[])
{
Set<ddd> set;
set=new TreeSet<>();
ddd d1=new ddd();
d1.code=1;
d1.title="Clerk";
ddd d2=new ddd();
d2.code=2;
d2.title="Manager";
ddd d3=new ddd();
d3.code=3;
d3.title="Faculty";
ddd d4=new ddd();
d4.code=4;
d4.title="Liftman";

set.add(d1);
set.add(d2);
set.add(d3);
set.add(d4);
for(ddd d:set)
{
System.out.println(d.code+" "+d.title);
}
ddd d5=new ddd();
d5.code=4;
d5.title="Hello";
set.remove(d5);
for(ddd d:set)
{
System.out.println(d.code+" "+d.title);
}
}
}