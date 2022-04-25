package com.osler.startapp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.osler.service.DeviceUserManager;

/*
 * input files path can be dynamic and intialize in different ways. 
 * output file is generating in resources directory with the name of DeviceUserListUpdated.txt
 * assuming that output user data will be only of those users, which need to update from portal user list to device user list.
 * @Author Heerendra
 */


@SpringBootApplication
@ComponentScan(basePackages = "com.osler.*")
public class OslerUserDataPocApplication {

	public static void main(String[] args) throws Exception{
		 DeviceUserManager manager = new DeviceUserManager();
		 manager.readTabDelimitedFile("C:\\Users\\Apolis\\Desktop\\rough\\OslerUserDataPOC\\src\\main\\resources\\PortalUserList.txt", manager.getPortalUserList());
    	 manager.readTabDelimitedFile("C:\\Users\\Apolis\\Desktop\\rough\\OslerUserDataPOC\\src\\main\\resources\\DeviceUserList.txt", manager.getDeviceUserList());
    	 manager.updateUsers();
    	 manager.writeTabDelimitedDataFile("C:\\Users\\Apolis\\Desktop\\rough\\OslerUserDataPOC\\src\\main\\resources\\DeviceUserListUpdated.txt");
	}
}
