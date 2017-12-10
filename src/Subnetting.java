import java.util.ArrayList;
import java.util.Scanner;

public class Subnetting {
	
	private String origNA;
	private String subnetMask;
	private int numOfSubnets;
	private String newNetMask;
	private String newHostsPerNet;
	private ArrayList<String> subnets = new ArrayList<String>();
	
	/**
	 * This is the constructor of the Subnetting class. It is required to build the object.
	 * 
	 * @param origNA network address derived from the input of the user
	 * @param subnetMask subnet mask from the input of the user
	 * @param numOfSubnets number of subnets required by the user
	 */
	public Subnetting(String origNA, String subnetMask, int numOfSubnets){
		this.origNA=origNA;
		
		if(subnetMask.substring(0,1).equals("/")){
			this.subnetMask=this.numOf1ToDecimal(Integer.parseInt(subnetMask.substring(1,subnetMask.length())));
		}
		else{
			this.subnetMask=subnetMask;
		}
		this.numOfSubnets=numOfSubnets;
	}
	
	/**
	 * This method checks whether the network address is valid
	 * 
	 * @param networkAddress String containing the network address
	 * @return returns true if network address is valid, otherwise returns false
	 */
	public boolean checkNetworkAddress(String networkAddress){
		int octet1=Integer.parseInt(networkAddress.substring(0, networkAddress.indexOf(".")));
		networkAddress=networkAddress.substring(networkAddress.indexOf(".")+1);
		int octet2=Integer.parseInt(networkAddress.substring(0, networkAddress.indexOf(".")));
		networkAddress=networkAddress.substring(networkAddress.indexOf(".")+1);
		int octet3=Integer.parseInt(networkAddress.substring(0, networkAddress.indexOf(".")));
		networkAddress=networkAddress.substring(networkAddress.indexOf(".")+1);
		int octet4=Integer.parseInt(networkAddress);
		if(octet1<10 || octet1>255){
			return false;
		}
		else if(octet2>255||octet3>255||octet4>255){
			return false;
		}
		return true;
	}
	
	/**
	 * This method checks whether the subnet mask is valid
	 * 
	 * @param subnetMask String containing the subnet mask
	 * @return returns true if subnet mask is valid, otherwise returns false
	 */
	public boolean checkSubnetMask(String subnetMask){
		int sm;
		
			int octet1=Integer.parseInt(subnetMask.substring(0, subnetMask.indexOf(".")));
			subnetMask=subnetMask.substring(subnetMask.indexOf(".")+1);
			int octet2=Integer.parseInt(subnetMask.substring(0, subnetMask.indexOf(".")));
			subnetMask=subnetMask.substring(subnetMask.indexOf(".")+1);
			int octet3=Integer.parseInt(subnetMask.substring(0, subnetMask.indexOf(".")));
			subnetMask=subnetMask.substring(subnetMask.indexOf(".")+1);
			int octet4=Integer.parseInt(subnetMask);
			
			octet1=this.numOf1(Integer.toBinaryString(octet1));
			octet2=this.numOf1(Integer.toBinaryString(octet2));
			octet3=this.numOf1(Integer.toBinaryString(octet3));
			octet4=this.numOf1(Integer.toBinaryString(octet4));
			
			sm=octet1+octet2+octet3+octet4;
		
		
		if(sm<8||sm>30){
			return false;
		}
		return true;
	}
	
	/**
	 * This method counts how many times the number 1 appears in a String
	 * 
	 * @param n String value
	 * @return Integer value containing how many times the number 1 occurred
	 */
	public int numOf1(String n){
		int count=0;
		for(int i=0; i<n.length(); i++){
			if(n.charAt(i)=='1'){
				count++;
			}
		}
		    return count;
	}
	
	
	/**
	 * This returns the value of the new Netmask
	 * 
	 * @return String of the new Netmask
	 */
	public String getNewNetMask() {
		return newNetMask;
	}
	
	/**
	 * This returns the value of the Hosts per subnet
	 * 
	 * @return String representing the number of hosts per subnet
	 */
	public String getNewHostsPerNet() {
		return newHostsPerNet;
	}

	/**
	 * This method computes the subnet and returns an arraylist of strings containing the 
	 * Newtwork Address, First Usable Address, Last Usable Address, and the Broadcast Address
	 * of each subnet
	 * 
	 * @return an Arraylist of String containing all the Subnets
	 */
	public ArrayList<String>getSubnets(){
		
		subnets.clear();
		
		int NAoct1, NAoct2, NAoct3, NAoct4;
		int subOct1, subOct2, subOct3, subOct4;
		int netMask1, netMask2, netMask3, netMask4;
		int origSubnet, origSubnetMask, bitsToAdd, newSubnetMask;
		String strbOrigNA, strbOrigSubMask;
		String bNAoct1,bNAoct2, bNAoct3, bNAoct4;
		String bSubOct1, bSubOct2, bSubOct3, bSubOct4;
		
		origSubnet=numOfSubnets;
		
		if(this.checkNetworkAddress(this.origNA)==true&&this.checkSubnetMask(this.subnetMask)==true){
			NAoct1=Integer.parseInt(origNA.substring(0, origNA.indexOf(".")));
			origNA=origNA.substring(origNA.indexOf(".")+1);
			NAoct2=Integer.parseInt(origNA.substring(0, origNA.indexOf(".")));
			origNA=origNA.substring(origNA.indexOf(".")+1);
			NAoct3=Integer.parseInt(origNA.substring(0, origNA.indexOf(".")));
			origNA=origNA.substring(origNA.indexOf(".")+1);
			NAoct4=Integer.parseInt(origNA);
			
			//network address in binary per octet
			bNAoct1=decimalToBinary(NAoct1);
			bNAoct2=decimalToBinary(NAoct2);
			bNAoct3=decimalToBinary(NAoct3);
			bNAoct4=decimalToBinary(NAoct4);
			
			//separate subnet mask into 4 octets without dots
			subOct1=Integer.parseInt(subnetMask.substring(0, subnetMask.indexOf(".")));
			subnetMask=subnetMask.substring(subnetMask.indexOf(".")+1);
			subOct2=Integer.parseInt(subnetMask.substring(0, subnetMask.indexOf(".")));
			subnetMask=subnetMask.substring(subnetMask.indexOf(".")+1);
			subOct3=Integer.parseInt(subnetMask.substring(0, subnetMask.indexOf(".")));
			subnetMask=subnetMask.substring(subnetMask.indexOf(".")+1);
			subOct4=Integer.parseInt(subnetMask);
			
			//subnet mask in binary per octet
			bSubOct1=decimalToBinary(subOct1);
			bSubOct2=decimalToBinary(subOct2);
			bSubOct3=decimalToBinary(subOct3);
			bSubOct4=decimalToBinary(subOct4);
			
			strbOrigNA=binaryFormNoPeriod(NAoct1,NAoct2,NAoct3,NAoct4);
			strbOrigSubMask=binaryFormNoPeriod(subOct1, subOct2, subOct3, subOct4);
			
			//subnet mask of original network address
			netMask1=netmask(Integer.parseInt(bSubOct1));
			netMask2=netmask(Integer.parseInt(bSubOct2));
			netMask3=netmask(Integer.parseInt(bSubOct3));
			netMask4=netmask(Integer.parseInt(bSubOct4));
			
			//print subnet mask of original NA
			origSubnetMask=netMask1+netMask2+netMask3+netMask4;
			
			//get number of bits to add
			bitsToAdd=bitsToAdd(numOfSubnets);
			newSubnetMask=origSubnetMask+bitsToAdd;
			
			//print new netmask with added bits to borrow
			newNetMask=(""+numOf1ToDecimal(newSubnetMask)+" = "+newSubnetMask);
			
			//print hosts per network
			newHostsPerNet=(""+hostsPerNet(32-newSubnetMask));
			
			//NETWORK PORTION
			StringBuilder revOrigNA=new StringBuilder();
			StringBuilder revOrigSubMsk= new StringBuilder();
			
			revOrigNA.append(strbOrigNA.substring(0,origSubnetMask));
			revOrigSubMsk.append(strbOrigSubMask.substring(0,origSubnetMask));
			revOrigNA.reverse();
			revOrigSubMsk.reverse();
			
			//ANDing
			String NAportion="";
			for(int i=revOrigNA.length(); i>0 ;i--){
				if(revOrigNA.substring(i-1, i).equals("1")&&revOrigSubMsk.substring(i-1,i).equals("1")){
					NAportion+="1";
				}
				else{
					NAportion+="0";
				}
			}
			String strNAportion=NAportion;
			
			//HOST PORTION Broadcast address
			int hostBits=32-newSubnetMask;
			String HostPortionBA="";
			while (hostBits>0){
			    HostPortionBA=HostPortionBA+"1";
			    hostBits-=1;
			}
			
			String strHostPortionBA=HostPortionBA+"";
			hostBits=32-newSubnetMask;
			
			//NETWORK PORTION Network Address
			int hostBitsCopy=hostBits;
			String strHostPortionNA="";
			while (hostBits>0){
			    strHostPortionNA+="0";
			    hostBits-=1;
			}
			
			//SUBNETTING
			int borrowedBits=0;
			int x=0;
			String revstrBorrowedBits="";
			while (x<Math.pow(2, this.bitsToAdd(origSubnet))){
				subnets.add("");
				subnets.add("Subnet "+x);
			    int bBorrowedBits= Integer.parseInt(decimalToBinary(borrowedBits));
			    
			    String strBorrowedBits="";
			    int j=bitsToAdd;
			    while (j>0){
			        strBorrowedBits+=(bBorrowedBits%10)+"";
			        j-=1;
			        bBorrowedBits/=10;
			    }
			    
			    StringBuilder revstrBorrowedBits1= new StringBuilder();
			    revstrBorrowedBits1.append(strBorrowedBits);
			    revstrBorrowedBits1.reverse();
			    revstrBorrowedBits=revstrBorrowedBits1.toString();
			   
			    borrowedBits+=1;
			    x+=1;
			
				//print
			    String strNA=strNAportion + revstrBorrowedBits + strHostPortionNA;
			    String bNA= strNA;
			    String strBA=strNAportion + revstrBorrowedBits + strHostPortionBA;
			    String bBA=strBA;
			   
			    String strHostFA=strHostPortionNA.substring(0, strHostPortionNA.length()-1);
			    strHostFA+="1";
			    
				 String strHostLA=(strHostPortionBA.substring(0,strHostPortionBA.length()-1));
				 strHostLA+="0";
				 
			    String bFA=strNAportion + revstrBorrowedBits + strHostFA;
			    String bLA=strNAportion + revstrBorrowedBits + strHostLA;
	
			    
			    String NA, FA, LA, BA;
			    
			    NA=IPadd(bNA);
			    FA=IPadd(bFA);
			    LA=IPadd(bLA);
			    BA=IPadd(bBA);
			    
			    subnets.add("Network Address:               "+NA+" /"+newSubnetMask);
			    subnets.add("First Usable Address:        "+FA);
			    subnets.add("Last Usable Address:        "+LA);
			    subnets.add("Broadcast Address:        "+ "   "+BA);
				}
		}
			
			else{
				subnets.add("Invalid Input");
			}
			return subnets;
		
	}
	/**
	 * This method converts a binary value to a decimal number
	 * @param binaryN the binary value of number being converted
	 * @return an Integer containing the decimal value
	 */
	public int binaryToDecimal(int binaryN){
		int powerOfTwo=1, deciConvert=0, binary;
		while(binaryN>0){
			binary=binaryN%10;
			binaryN/=10;
			deciConvert+=binary*powerOfTwo;
			powerOfTwo*=2;
		}
		return deciConvert;
	}
	
	/**
	 * This method converts a decimal number to a binary value
	 * @param decimalN the decimal number of the binary being converted
	 * @return a String containing the binary value
	 */
	public String decimalToBinary(int decimalN){
		int b128, b64, b32, b16, b8, b4, b2, b1;
		String converted="";
				b128= decimalN/128;
			    decimalN= decimalN%128;
			    b64= decimalN/64;
			    decimalN= decimalN%64;
			    b32= decimalN/32;
			    decimalN= decimalN%32;
			    b16= decimalN/16;
			    decimalN= decimalN%16;
			    b8= decimalN/8;
			    decimalN= decimalN%8;
			    b4= decimalN/4;
			    decimalN= decimalN%4;
			    b2= decimalN/2;
			    decimalN= decimalN%2;
			    b1=decimalN;
			    
			    converted= converted+b128+""+b64+""+b32+""+b16+""+b8+""+b4+""+b2+""+b1;
			    return converted;
	}
	
	/**
	 * This combines four octets and places a dot after each octet
	 * @param n1 the first octet
	 * @param n2 the second octet
	 * @param n3 the third octet
	 * @param n4 the fourth octet
	 * @return String containing the merged octets with dot as separator
	 */
	public String binaryFormWithPeriod(int n1,int n2,int n3, int n4){
		String boct1=this.decimalToBinary(n1);
		String boct2=this.decimalToBinary(n2);
		String boct3=this.decimalToBinary(n3);
		String boct4=this.decimalToBinary(n4);
		
		String boct5=boct1+"."+boct2+"."+boct3+"."+boct4;
		return boct5;
	}
	
	/**
	 * This combines four octets into a single String value
	 * @param n1 the first octet
	 * @param n2 the second octet
	 * @param n3 the third octet
	 * @param n4 the fourth octet
	 * @return String containing the merged octets without dots in between each octet
	 */
	public String binaryFormNoPeriod(int n1,int n2,int n3, int n4){
		String boct1=this.decimalToBinary(n1);
		String boct2=this.decimalToBinary(n2);
		String boct3=this.decimalToBinary(n3);
		String boct4=this.decimalToBinary(n4);
		
		String boct5=boct1+boct2+boct3+boct4;
		return boct5;
}

	/**
	 * This method computes the netmask
	 * @param n a binary value
	 * @return Integer value containing the new Netmask
	 */
	public int netmask(int n){
		int rev=0, netmask=0, digit;
		 while (n > 0){
		        rev= rev*10 + n%10;
		        n=n/10;
		 }
		    while (rev > 0){
		        digit= rev%10;
		        rev=rev/10;
		        if (digit==1)
		            netmask+=1;
		    }
		    return netmask;
	}

	/**
	 * This method computes how many bits are needed to be borrowed
	 * 
	 * @param numOfSubnets the number of subnets specified by the user
	 * @return Integer value containing how many bits are borrowed
	 */
	public int bitsToAdd(int numOfSubnets){
		double toAdd=0, counter=0;
		for(int i=0; counter<numOfSubnets; i++){
			counter=Math.pow(2, i);
			toAdd=i;
		} 
		if(Math.pow(2, toAdd)>=numOfSubnets){
		}
		else{
			toAdd++;
		}
		return (int)toAdd;
	}
	
	/**
	 * This computes how many hosts per net are available per subnet
	 * 
	 * @param numOfSubnets the number of subnets specified by the user
	 * @return Integer value containing the number of hosts per subnet
	 */
	public int hostsPerNet(int numOfSubnets){
		return (int)Math.pow(2, (double)numOfSubnets)-2;
	}
	
	/**
	 * This method converts an integer value into a binary containing 
	 * several 1's and converting it back to a decimal value with dots 
	 * separting each octet
	 * 
	 * @param n the Integer to be converted
	 * @return String value containing the decimal value
	 */
	public String numOf1ToDecimal(int n){
		String binary="", decimal="";
		for(int i=0;i<4;i++){
			for(int j=0; j<8; j++){
				if(n>0){
					binary+=1;
					n--;
				}
				else binary+=0;
			}decimal=decimal+""+Integer.parseInt(binary, 2)+".";
			binary=""; 
		}
		
		return decimal.substring(0,decimal.length()-1);
	}
	
	/**
	 * This method converts an integer value into a binary containing 
	 * several 1's with periods
	 * 
	 * @param n the Integer to be converted
	 * @return String value containing the binary value
	 */
	public String numOf1ToBinary(int n){
		String binary="";
		for(int i=0;i<4;i++){
			for(int j=0; j<8; j++){
				if(n>0){
					binary+=1;
					n--;
				}
				else binary+=0;
			}binary+=".";
		}
		
		return binary.substring(0,binary.length()-1);
	}

	/**
	 * This method converts an binary String into a decimal into an
	 * IP address format 
	 * @param n the binary String to be converted
	 * @return the converted binary String into an IP address format
	 */
	public String IPadd(String n){
		
		String IPadd="";
		
		String oct1, oct2, oct3, oct4;
		oct1=n.substring(0,8);
		n=n.substring(8, n.length());
		oct2=n.substring(0,8);
		n=n.substring(8, n.length());
		oct3=n.substring(0,8);
		n=n.substring(8, n.length());
		oct4=n;
		
		IPadd=Integer.parseInt(oct1, 2)+"."+Integer.parseInt(oct2, 2)+"."+Integer.parseInt(oct3, 2)+"."+Integer.parseInt(oct4, 2);
		return IPadd;
		
	}
}
