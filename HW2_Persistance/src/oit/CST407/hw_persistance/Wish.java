package oit.CST407.hw_persistance;

import java.io.Serializable;

public class Wish implements Serializable{
	private boolean used;
	   
	   private Wish(){}
	 
	   public Wish(boolean used) {
	      this.used = used;
	   }
	 
	   public boolean getIsUsed() {
	      return used;
	   }
	   
	   public void setIsUsed( boolean used){
		   this.used = used;
	   }
}
