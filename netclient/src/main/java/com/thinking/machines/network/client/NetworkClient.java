package com.thinking.machines.network.client;
import com.thinking.machines.network.common.*;
import com.thinking.machines.network.common.exceptions.*;
import java.net.*;
import java.io.*;
public class NetworkClient
{
public Response send(Request request) throws NetworkException
{
/*
1) Serialize Request object
2) Connect to server
3) Send header and then the serialized form of Request in chunks as 
    we did earlier in network programming example
4) Recieve back header and then the serialized form of response
     and deserialized it
5)  Return the refrence of Response Object
*/
try
{
ByteArrayOutputStream baos=new ByteArrayOutputStream();
ObjectOutputStream oos=new ObjectOutputStream(baos);
oos.writeObject(request);
oos.flush();
byte objectBytes[];
objectBytes=baos.toByteArray();
int requestLength=objectBytes.length;
byte header[]=new byte[1024];
int x=requestLength;
int i=1023;
while(x>0)
{
header[i]=(byte)(x%10);
x=x/10;
i--;
}


Socket socket=new Socket(Configuration.getHost(),Configuration.getPort());
OutputStream os=socket.getOutputStream();
os.write(header,0,1024);
os.flush();

System.out.println("Header sent");

InputStream is;
is=socket.getInputStream();
byte ack[];
ack=new byte[1];
int bytesReadCount;
while(true)
{
bytesReadCount=is.read(ack);
if(bytesReadCount==-1)continue;
break;
}
System.out.println("Acknowledgement recieved");
int bytesToSend=requestLength;
int j=0;
int chunkSize=1024;
while(j<bytesToSend)
{
if((bytesToSend-j)<chunkSize) chunkSize=bytesToSend-j;
os.write(objectBytes,j,chunkSize);
os.flush();
j=j+chunkSize;
}
System.out.println("Data Sent");
int bytesToReceive=1024;
byte tmp[]=new byte[1024];
int k;
j=0;
i=0;
while(j<bytesToReceive)
{
bytesReadCount=is.read(tmp);
if(bytesReadCount==-1) continue;
for(k=0;k<bytesReadCount;k++)
{
header[i]=tmp[k];
i++;
}
j=j+bytesReadCount;
}

System.out.println("Header recieved");
x=1023;
i=1;
int responseLength=0;
while(x>=0)
{
responseLength=responseLength+(header[x]*i);
i=i*10;
x--;
}
System.out.println("Response Length : "+responseLength);
ack[0]=1;
os.write(ack,0,1);
os.flush();
System.out.println("Acknowledgement Sent");
byte response[]=new byte[responseLength];
int bytesToRecieve=responseLength;
j=0;
i=0;
while(j<bytesToRecieve)
{
bytesReadCount=is.read(tmp);
if(bytesReadCount==-1) continue;
for(k=0;k<bytesReadCount;k++)
{
response[i]=tmp[k];
i++;
}
j=j+bytesReadCount;
}
System.out.println("Response Recieved");
os.write(ack,0,1);
os.flush();
socket.close();
ByteArrayInputStream bais=new ByteArrayInputStream(response);
ObjectInputStream ois=new ObjectInputStream(bais);
Response responseObject=(Response)ois.readObject();
return responseObject;
}catch(Exception e)
{
throw new NetworkException(e.getMessage());
}
}
}
