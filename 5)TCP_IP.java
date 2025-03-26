/* Using TCP/IP sockets, write a client – server program to make the  
client send the file name and to make the server send back the contents  
of the requested file if present*/ 
/*SERVER program*/ 
import java.net.*; 
import java.io.*; 
public class TCPS { 
public static void main(String[] args) throws Exception { 
ServerSocket sersock = new ServerSocket(4000); 
System.out.println("Server ready for connection"); 
Socket sock = sersock.accept(); 
System.out.println("Connection Is successful and waiting for the client request"); 
InputStream istream = sock.getInputStream(); 
BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream)); 
String fname = fileRead.readLine(); 
BufferedReader ContentRead = new BufferedReader(new FileReader(fname)); 
OutputStream ostream = sock.getOutputStream(); 
PrintWriter pwrite = new PrintWriter(ostream, true); 
String str; 
while ((str = ContentRead.readLine()) != null) { 
pwrite.println(str); 
} 
sock.close(); 
sersock.close(); 
pwrite.close(); 
fileRead.close(); 
ContentRead.close(); 
} 
} 
/*first the server port should be established before it accepts any request from the client*/   
/*Client program*/ 
import java.net.*; 
import java.io.*; 
public class TCPC { 
public static void main(String[] args) throws Exception { 
Socket sock = new Socket("127.0.0.1", 4000); 
System.out.println("Enter the filename"); 
BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in)); 
String fname = keyRead.readLine(); 
OutputStream ostream = sock.getOutputStream(); 
PrintWriter pwrite = new PrintWriter(ostream, true); 
pwrite.println(fname); 
InputStream istream = sock.getInputStream(); 
BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream)); 
String str; 
while ((str = socketRead.readLine()) != null) { 
System.out.println(str); 
} 
pwrite.close(); 
socketRead.close(); 
keyRead.close(); 
} 
} 
/*First run the server program then in new terminal window run the client program and input the  
file name the server will return the file content if it exists 
output:- 
SERVER PROGRAM 
javac TCPS.java 
java TCPS 
Server ready for connection 
Connection Is successful and waiting for the client request 
CLIENT program 
in new terminal window 
javac TCPC.java 
java TCPC 
Enter the filename 
text.txt 
This the file client requested from the server.... 
*/ 
