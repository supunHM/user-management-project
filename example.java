
import java.time.*;
import java.util.*;

class CustomerList{
    private Customer[] customerArray;
    private int nextIndex;
    private int size;
    private int loadFactor;

    CustomerList(int size, int loadFactor) {
        customerArray = new Customer[size];
        nextIndex = 0;
        this.size = size;
        this.loadFactor = loadFactor;
    }
    private boolean isFull(){
        return nextIndex >= size;
    }

    //-----------Extend Array Method--------------
    private void extendArrays(){
        Customer[] tempCustomerArray = new Customer[size+loadFactor]; 

        for(int i=0; i<nextIndex; i++){
            tempCustomerArray[i] = customerArray[i];
        }
        customerArray=tempCustomerArray;
    }

    //-------------Add Method--------------
    public void add(Customer customer){
        if(isFull()){
            extendArrays();
        }
        customerArray[nextIndex++] = customer;
    }

     //---------------------------SEARCH METHOD--------------------------
     public int searchByNameOrPhoneNumber(String nameOrPhone){
        for(int i=0; i<nextIndex; i++){
            if(customerArray[i].getName().equals(nameOrPhone) || customerArray[i].getPhoneNumber().equals(nameOrPhone)){
                return i;
            }
        }
        return -1;
    }

    //--------------Temp Array------------------
    public Customer[] tempArray(){
        Customer[] tempCustomerArray = new Customer[size];
        for(int i=0; i<nextIndex; i++){
            tempCustomerArray[i] = customerArray[i];
        }
        customerArray=tempCustomerArray;
        return tempCustomerArray;
    }

    public int getSize(){
        return nextIndex;
    }

    //---------------Update Name-----------------
    public void updateName(int index , String newName){
        customerArray[index].setName(newName);
    }
    //---------------Update Phone Number-----------------
    public void updatePhoneNumber(int index , String newPhoneNumber){
        customerArray[index].setPhoneNumber(newPhoneNumber);
    }
    //---------------Update Name-----------------
    public void updateCompanyName(int index , String newCompanyName){
        customerArray[index].setCompanyName(newCompanyName);
    }
    //---------------Update Name-----------------
    public void updateSalary(int index , double newSalary){
        customerArray[index].setSalary(newSalary);
    }

    public void delete(int index){
        for(int i = index; i <nextIndex-1; i++){
            customerArray[i] = customerArray[i+1];
        }
        nextIndex--;
        customerArray[nextIndex] = null;
    }

    


}
class Customer{
	private String id;
	private String name;
	private String phoneNumber;
	private String companyName;
	private double salary;
	private String birthday;
	//-------Constructer--------
	Customer(String id, String name , String phoneNumber , String companyName , double salary , String birthday){
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.companyName = companyName;
		this.salary = salary;
		this.birthday = birthday;
	}

	//---------Setters----------------------
	public void setId(String id){
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public void setSalary(double salary){
		this.salary = salary;
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	//----------Getters--------------

	public String getId(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public String getCompanyName(){
		return companyName;
	}
	public double getSalary(){
		return salary;
	}
	public String getBirthday(){
		return birthday;
	}

}
class example{
    //-------------------CREATE AN ARRAYS ----------------

	//public static Customer [] contactArray = new Customer[0];
    public static CustomerList customerList = new CustomerList(100, 50);
    
	    //----------------------CLEAR CONSOLE --------------------
		public final static void clearConsole() { 
			try {
			final String os = System.getProperty("os.name"); 
			if (os.contains("Windows")) {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
			System.out.print("\033[H\033[2J"); 
			System.out.flush();
			}
			} catch (final Exception e) {
			e.printStackTrace();
			// Handle any exceptions.
			}
		}
    //-----------------MAIN METHOD--------------------
    public static void main(String[] args){
        homepage();
    }
    //-------------------GENERATE ID----------------
    public static String generateId(){
			return String.format("C%04d",customerList.getSize()+1);	
	}
    //-----------------HOME PAGE--------------------
    public static void homepage(){
        Scanner input = new Scanner(System.in);
        System.out.println("=======================iFRIEND CONTACT ORGANIZER============================");
        System.out.println("\n[01] Add Contacts");
        System.out.println("\n[02] Update Contacts");
        System.out.println("\n[03] Delete Contacts");
        System.out.println("\n[04] Search Contacts");
        System.out.println("\n[05] List Contacts");
        System.out.println("\n[06] Exit");
        System.out.print("\nEnter option to continue : ");
        int option=input.nextInt();
        clearConsole();
        switch(option){
            case 1 : addContacts();break;
            case 2 : updateContacts();break;
            case 3 : deleteContacts();break;
            case 4 : searchContacts();break;
            case 5 : listContacts();break;
            case 6 : exit();break;
            default : System.out.println("Invalid option...");break;
        }

    }
    //------------------------VALIDATE PHONENUMBER---------------------------
    public static boolean isValidPhoneNumber(String phoneNumber){
        if(phoneNumber.length()==10 && phoneNumber.charAt(0)=='0'){
            for(int i=1; i<phoneNumber.length(); i++){
                if(!Character.isDigit(phoneNumber.charAt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;

    }
    //-------------------VALIDATE SALARY----------------------
    public static boolean isValidSalary(double salary){
        return salary>0;
    }
    // -------------------BIRTHDAY VALIDATION----------------
	public static boolean isValidBirthday(String birthday){
        String y=birthday.substring(0,4);
		int year=Integer.parseInt(y);
		String m=birthday.substring(5,7);
		int month=Integer.parseInt(m);
		String d=birthday.substring(8,10);
		int day=Integer.parseInt(d);
		LocalDate currentDate = LocalDate.now();
		int currentMonthValue = currentDate.getMonthValue();
		int currentYear=currentDate.getYear();    
		int currentMonthDate=currentDate.getDayOfMonth();
			
		if(year%4!=0 & month==2){
			if(day>28){
				return false;
			}else{
				return true;
			}
		}
		if(year%4==0 & month==2){
			if(day>29){
				return false;
			}else{
				return true;
			}
		}
		if(month==4 || month==6 || month==9 || month==11){
			if(day>30){
				return false;					
			}
		}
		if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
			if(day>31){
				return false;
			}	
		}
		if(month>12){
			return false;
		}
		if(year<currentYear){
			return true;
			}else if(year==currentYear){
									
				if(month>currentMonthValue){
					return true;
				}else if(month==currentMonthValue){
									
					if(day<=currentMonthDate){
						return true;
					}
				}
			}
					return false;
    }
    //-------------------------EXTEND ARRAYS-----------------------
    // public static void extendArrays(){
	// 	Customer[] tempContactArray = new Customer[contactArray.length+1];
		  


    //     for(int i=0; i<contactArray.length; i++) {
	// 		tempContactArray[i] = contactArray[i];
    //     }
	// 	contactArray = tempContactArray;
     
    // }
    //-----------------ADD CONTACTS--------------------
    public static void addContacts(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("======================ADD CONTACTS================");
            String id = generateId();
            System.out.println("\n"+id);
            System.out.println("=============");
            System.out.print("Name : ");
            String name=input.next();
            String phoneNumber;
            L1:do{
                System.out.print("Phone Number : ");
                phoneNumber  = input.next();
                if(!isValidPhoneNumber(phoneNumber)){
                    System.out.println("\n\tInvalid phone number...");
                    System.out.print("\nDo you want to input phone number again : ");
                    char ch = input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L1;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidPhoneNumber(phoneNumber));

            System.out.print("Company Name : ");
            String companyName  = input.next();
            double salary;

            L2:do{
                System.out.print("Salary : ");
                salary=input.nextDouble();
                if(!isValidSalary(salary)){
                    System.out.println("\n\tInvalid salary...");
                    System.out.print("\nDo you want to input salary again : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L2;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidSalary(salary));
            String birthday;

            L3:do{
                System.out.print("Birthday : ");
                birthday = input.next();
                if(!isValidBirthday(birthday)){
                    System.out.println("\n\tInvalid birthday...");
                    System.out.print("\nDo you want to input birthday again : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        System.out.print("\033[5A");
                        System.out.print("\033[0J");
                        continue L3;
                    }else if(ch=='N'||ch=='n'){
                        clearConsole();
                        homepage();
                    }
                }

            }while(!isValidBirthday(birthday));

            //extendArrays();

			Customer customer = new Customer(id, name, phoneNumber, companyName, salary, birthday);

            customerList.add(customer);

			//contactArray[contactArray.length-1]=customer;



            System.out.println("\n\tContact has been added successfully...");
            System.out.print("\nDo you want to add another contact : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                addContacts();
            }else if(ch=='N'||ch=='n'){
                clearConsole();
                homepage();
            }

        }while(true);

    }
   
    //--------------------------PRINT DETAILS---------------------------
    public static void printDetails(int index){
        System.out.println("Contact Id : "+customerList.tempArray()[index].getId());
        System.out.println("Name : "+customerList.tempArray()[index].getName());
        System.out.println("Phone Number : "+customerList.tempArray()[index].getPhoneNumber());
        System.out.println("Company Name : "+customerList.tempArray()[index].getCompanyName());
        System.out.println("Salary : "+customerList.tempArray()[index].getSalary());
        System.out.println("Birthday : "+customerList.tempArray()[index].getBirthday());
    }
    //--------------------------SEARCH CONTACT-------------------------
    public static void searchContacts(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("=====================SEARCH CONTACTS======================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = customerList.searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    searchContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }else{
                printDetails(index);
                System.out.print("\nDo you want to search another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    searchContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }

        }while(true);
    }
    //--------------------------UPDATE NAME----------------------------
    public static void updateName(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Name");
        System.out.println("===================");
        System.out.print("\nInput new name : ");
        String newName = input.next();
        customerList.updateName(index , newName);
       // contactArray[index].setName(newName);
    }
    //--------------------------UPDATE PHONE NUMBER----------------------------
    public static void updatePhoneNumber(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Phonenumber");
        System.out.println("========================");
        System.out.print("\nInput new phone number : ");
        String newPhoneNumber = input.next();
        customerList.updatePhoneNumber(index , newPhoneNumber);
        //contactArray[index].setPhoneNumber(newPhoneNumber);
    }
    //--------------------------UPDATE COMPANY NAME---------------------------
    public static void updateCompanyName(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Company Name");
        System.out.println("===========================");
        System.out.print("\nInput new company name : ");
        String newCompanyName = input.next();
        customerList.updateCompanyName(index , newCompanyName);
        //contactArray[index].setCompanyName(newCompanyName);
    }
    //--------------------------UPDATE SALARY----------------------------
    public static void updateSalary(int index){
        Scanner input=new Scanner(System.in);
        System.out.println("\n Update Salary");
        System.out.println("==================");
        System.out.print("\nInput new salary : ");
        double newSalary = input.nextDouble();
        customerList.updateSalary(index , newSalary);
        //contactArray[index].setSalary(newSalary);
    }
    //--------------------------UPDATE CONTACTS-----------------------
    public static void updateContacts(){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("=======================UPDATE CONTACTS================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = customerList.searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    updateContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }
            else{
                printDetails(index);

                System.out.println("\nWhat do you want to update");
                System.out.println("\n\t[01] Name");
                System.out.println("\t[02] Phone number");
                System.out.println("\t[03] Company Name");
                System.out.println("\t[04] Salary");
                System.out.println("\nEnter an option to continue...");
                int option=input.nextInt();
                switch(option){
                    case 1 : updateName(index);break;
                    case 2 : updatePhoneNumber(index);break;
                    case 3 : updateCompanyName(index);break;
                    case 4 : updateSalary(index);break;
                    default : System.out.println("\n\tInvalid option...");
                }
                System.out.println("\nContact has been update successfully.");
                System.out.print("\nDo you want to update another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    updateContacts();
                }else if(ch=='N'|| ch=='n'){
                    clearConsole();
                    homepage();
                }
            }
        }while(true);
    }
    //---------------------------DELETE-------------------------------
    // public static void delete(int index){
	// 	Customer[] tempContactArray = new Customer[contactArray.length-1];
       

    //     for(int i=index; i<contactArray.length-1; i++) {
	// 		contactArray[i] = contactArray[i+1];
          
    //     }
    //     for(int i=0; i<contactArray.length-1; i++) {
	// 		tempContactArray[i] = contactArray[i];
    //     }
	// 	contactArray = tempContactArray;
      
 
    // }
    //--------------------------DELETE CONTACTS-----------------------
    public static void deleteContacts(){
        Scanner input = new Scanner(System.in);
        do{
            System.out.println("======================DELETE CONTACTS================");
            System.out.print("\nSearch contact by name or phone number : ");
            String nameOrPhone=input.next();
            int index = customerList.searchByNameOrPhoneNumber(nameOrPhone);

            if(index == -1){
                System.out.println("\n\tNo contact found for "+nameOrPhone);
                System.out.print("\nDo you want to try a new search : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    deleteContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
            }else{
                printDetails(index);
                L1:do{
                    System.out.print("\nDo you want to delete this contact : ");
                    char ch=input.next().charAt(0);
                    if(ch=='Y'||ch=='y'){
                        customerList.delete(index);
                        System.out.println("\nContact has been deleted successfully...");
                        break L1;
                    }else if(ch=='N'||ch=='n'){
                        break L1;
                    }

                }while(true);

                System.out.println("\nDo you want to delete another contact : ");
                char ch=input.next().charAt(0);
                if(ch=='Y'||ch=='y'){
                    clearConsole();
                    deleteContacts();
                }else if(ch=='N'||ch=='n'){
                    clearConsole();
                    homepage();
                }
                
            }

        }while(true);

    }
    //--------------------------LIST CONTACTS------------------------
    public static void listContacts(){
        Scanner input = new Scanner(System.in);
        System.out.println("=======================SORT CONTACTS==========================");
        System.out.println("\n[01] Sorting by name");
        System.out.println("\n[02] Sorting by salary");
        System.out.println("\n[03] Sorting by birthday");
        System.out.print("\nEnter option to continue : ");
        int option=input.nextInt();
        switch(option){
            case 1 : sortByName();break;
            case 2 : sortBySalary();break;
            case 3 : sortByBirthday();break;
            default : System.out.println("\n\tInvalid option...");
        }

    }
    //--------------------------SORT BY NAME-------------------------
    public static void sortByName(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingByName();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    //--------------------------SORT BY SALARY-------------------------
    public static void sortBySalary(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingBySalary();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    //--------------------------SORT BY BIRTHDAY-------------------------
    public static void sortByBirthday(){
        Scanner input=new Scanner(System.in);
        do{
            System.out.println("============LIST CONTACT BY NAME============\n");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|  Contact Id  |     Name     |   Phone Number   |    Company    |    Salary    |      Birthday     |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");

            sortingByBirthday();

            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.print("\nDo you want to go homepage : ");
            char ch=input.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                clearConsole();
                homepage();
            }else if(ch=='N'|| ch=='n'){
                clearConsole();
                listContacts();
            }


        }while(true);
    }
    //-------------------------NAME SORT---------------------------
    public static void sortingByName(){
		Customer[] tempContactArray = new Customer[customerList.getSize()];

        for(int i=0; i<tempContactArray.length; i++){
			tempContactArray[i] = customerList.tempArray()[i];
   
        }
        for(int j=1; j<tempContactArray.length; j++){
            for(int i=0; i<tempContactArray.length-j; i++){
                if(tempContactArray[i].getName().compareTo(tempContactArray[i+1].getName())>0){
                    Customer tempContact = tempContactArray[i];
                    tempContactArray[i] = tempContactArray[i+1];
                    tempContactArray[i+1] = tempContact;


                }
            }
        }

        for(int i=0; i<tempContactArray.length; i++) {
            System.out.printf("| %-13s| %-13s| %-16s| %-15s| %-13.2f| %-18s|\n",tempContactArray[i].getId(),tempContactArray[i].getName(),tempContactArray[i].getPhoneNumber(),tempContactArray[i].getCompanyName(),tempContactArray[i].getSalary(),tempContactArray[i].getBirthday());
        }

    }
    //-------------------------SALARY SORT---------------------------
    public static void sortingBySalary(){
		Customer[] tempContactArray = new Customer[customerList.getSize()];
   

        for(int i=0; i<tempContactArray.length; i++){
			tempContactArray[i] = customerList.tempArray()[i];
   
        }
        for(int j=1; j<tempContactArray.length; j++){
            for(int i=0; i<tempContactArray.length-j; i++){
                if(tempContactArray[i].getSalary()>tempContactArray[i+1].getSalary()){
					Customer tempContact = tempContactArray[i];
					tempContactArray[i] = tempContactArray[i+1];
					tempContactArray[i+1] = tempContact;


                }
            }
        }

        for(int i=0; i<tempContactArray.length; i++) {
            System.out.printf("| %-13s| %-13s| %-16s| %-15s| %-13.2f| %-18s|\n",tempContactArray[i].getId(),tempContactArray[i].getName(),tempContactArray[i].getPhoneNumber(),tempContactArray[i].getCompanyName(),tempContactArray[i].getSalary(),tempContactArray[i].getBirthday());
        }

    }

    //-------------------------BIRTHDAY SORT---------------------------
    public static void sortingByBirthday(){
		Customer[] tempContactArray = new Customer[customerList.getSize()];


        for(int i=0; i<tempContactArray.length; i++){
			tempContactArray[i] = customerList.tempArray()[i];
 
        }
        for(int j=1; j<tempContactArray.length; j++){
            for(int i=0; i<tempContactArray.length-j; i++){
                if((tempContactArray[i].getBirthday()).compareTo(tempContactArray[i+1].getBirthday())>0){
					Customer tempContact = tempContactArray[i];
					tempContactArray[i] = tempContactArray[i+1];
					tempContactArray[i+1] = tempContact;
                  

                }
            }
        }

        for(int i=0; i<tempContactArray.length; i++) {
            System.out.printf("| %-13s| %-13s| %-16s| %-15s| %-13.2f| %-18s|\n",tempContactArray[i].getId(),tempContactArray[i].getName(),tempContactArray[i].getPhoneNumber(),tempContactArray[i].getCompanyName(),tempContactArray[i].getSalary(),tempContactArray[i].getBirthday());
        }

    }
    //-----------------------EXIT--------------------------
    public static void exit(){
        System.exit(0);
    }

}
