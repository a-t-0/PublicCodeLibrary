0. Create a first.java file in folder C:/runnableJava named First.java with the following file content:

import javax.swing.*;    
public class First{    
First(){    
JFrame f=new JFrame("simple swing");    
                    
JButton b=new JButton("click");    
b.setBounds(130,100,100, 40);    
        
f.add(b);    
            
f.setSize(300,400);    
f.setLayout(null);    
f.setVisible(true);    
            
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
}    
public static void main(String[] args) {    
    new First();    
}    
}    

1. Create a manifest file named manifest.mf in C:/runnableJava, indicating the name of your class that contains your main, followed by an empty line:
Main-Class: First

2. Open cmd, browse to C:/runnableJava and compile the javafile:
javac First.java

3. Next create the executable .jar file with cmd command:
jar -cvmf myfile.mf myjar.jar First.class  

3.b You can convert a more complex java project with multiple .class files to jar using: (this adds all the .class files in the folder cmd is currently in when you execute the following command)
jar -cvmf myfile.mf myjar.jar *.class  

4. Run the .jar file with:
java -jar hello.jar