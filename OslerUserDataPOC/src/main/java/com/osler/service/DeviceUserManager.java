package com.osler.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import com.osler.entity.User;
import com.osler.entity.Status;
public class DeviceUserManager {
    
	 private Set<User> deviceUserListUpdated = new HashSet<User>();	
     private Set<User> portalUserList = new HashSet<User>();
     private Set<User> deviceUserList = new HashSet<User>();
     
	public Set<User> getDeviceUserListUpdated() {
		return deviceUserListUpdated;
	}

	public void setDeviceUserListUpdated(Set<User> deviceUserListUpdated) {
		this.deviceUserListUpdated = deviceUserListUpdated;
	}

	public Set<User> getPortalUserList() {
		return portalUserList;
	}

	public void setPortalUserList(Set<User> portalUserList) {
		this.portalUserList = portalUserList;
	}

	public Set<User> getDeviceUserList() {
		return deviceUserList;
	}

	public void setDeviceUserList(Set<User> deviceUserList) {
		this.deviceUserList = deviceUserList;
	}

	// Create a set list which is contains updated and non updated local users.
	public void updateUsers() {
		
		portalUserList.stream().filter(x-> deviceUserList.contains(x)).
		forEach(x->deviceUserListUpdated.add(x));
		deviceUserList.stream().forEach(x->deviceUserListUpdated.add(x));
	}
    
	// Read data from a tab delimited file.
	public Set<User> readTabDelimitedFile(String filePath,Set<User> array)throws FileNotFoundException{
	    Scanner scan = new Scanner(new File(filePath));
	   
	    while(scan.hasNext()){
	        User user = new User();
	        Status status = new Status();
	    	String curLine = scan.nextLine();
	        String[] splitted = curLine.split("\t");
	        user.setId(splitted[0].trim());
	        user.setDeviceId(splitted[1].trim());
	        Integer bit7 = Integer.parseInt(splitted[2].substring(2).trim(),16) & (1<<7);
	        if(bit7!=0) bit7=1; 
	        status.setAuthorisation(bit7.byteValue());
	        Integer bit6 = Integer.parseInt(splitted[2].substring(2).trim(),16) & (1<<6);
	        if(bit6!=0) bit6=1;
	        status.setTraining(bit6.byteValue());
	        Integer bit5 = Integer.parseInt(splitted[2].substring(2).trim(),16) & (1<<5);
	        if(bit5!=0) bit5=1;
	        status.setAdmin(bit5.byteValue());
	        user.setStatus(status);
	        array.add(user);
	    }
	    
	    scan.close();
	   
	    return array;
	}
       
	// Write data on a tab delimited file.
	public void writeTabDelimitedDataFile(String filePath) throws IOException{
		
		
	    try (PrintWriter writer = new PrintWriter(
	       Files.newBufferedWriter(Paths.get(filePath)))){
	            for (User user : deviceUserListUpdated) {
	                
	            	String auth;
	        		String adm;
	        		String train;
	        		
	        		if(user.getStatus().getAuthorisation()==1)  auth="Authorised";
	        		else auth="Disabled";
	        		if(user.getStatus().getAdmin()==1) adm="Admin";
	        		else adm="Operator";
	        		if(user.getStatus().getTraining()==1) train="Trained";
	        		else train="Untrained";
	            	
	            	writer.printf("%1$s\t%2$s\t\t%3$-20s\t\t%4$-20s",
	                        user.getId(), user.getDeviceId(), auth+adm, train);
	                writer.println();
	            }
	        }
	    }
	}


