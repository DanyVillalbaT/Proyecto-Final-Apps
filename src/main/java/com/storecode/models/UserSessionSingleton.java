package com.storecode.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserSessionSingleton {
	 private static UserSessionSingleton INSTANCIA = new UserSessionSingleton();
	 
	 private User userSession;
	    
	    
	    private UserSessionSingleton() {
	        userSession= readUserSession();
	    }
	    
	    
	    public void writeUserSession(){
	        try{
	            FileOutputStream file = new FileOutputStream("userSession.dat");
	            ObjectOutputStream writer = new ObjectOutputStream(file);
	            writer.writeObject( getUserSession() );
	        }catch(FileNotFoundException ex){
	            ex.getStackTrace();
	        }catch(IOException ex){
	            ex.getStackTrace();
	        }
	    }

	    public User readUserSession(){
	    
	        try{
	            FileInputStream file = new FileInputStream("userSession.dat");
	            ObjectInputStream reader = new ObjectInputStream(file);
	            return (User) reader.readObject();
	        }catch(IOException | ClassNotFoundException ex){
	            ex.getStackTrace();
	            return new User();
	        }
	        
	    }
	    
	    public static UserSessionSingleton getINSTANCIA() {
	        return INSTANCIA;
	    }

	    
	    public User getUserSession() {
	        return userSession;
	    }

}
