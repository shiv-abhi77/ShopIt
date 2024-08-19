import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
interface WholeSale{
    public void Loaditem();
}
class Grocery{
    protected String [] product = {"ORANGE","APPLE","PINEAPPLE","BANANA","KIWI","NOTEBOOK","PENCIL BOX","PEN","ERASER","SHARPENER","PIZZA","BURGER","NOODLES","FRENCH FRIES","VADAPAV","PHONE","LAPTOP","WATCH","TV","AC","COCA-COLA","APPY FIZZ","ORANGE JUICE","COLD COFFEE","BUTTER-MILK"};
    protected String [] products = {"ORANGES","APPLES","PINEAPPLES","BANANAS","KIWIS","NOTEBOOKS","PENCIL BOXES","PENS","ERASERS","SHARPENERS","PIZZAS","BURGERS","NOODLES","FRENCH FRIES","VADAPAVS","PHONES","LAPTOPS","WATCHES","TVs","ACs","COCA-COLA","APPY FIZZ","ORANGE JUICES","COLD COFFEES","BUTTER-MILK"};
    protected String [] files = {"orange.txt","apple.txt","pineapple.txt","banana.txt","kiwi.txt","notebook.txt","boxofpencil.txt","pen.txt","eraser.txt","sharpener.txt","pizza.txt","burger.txt","noodles.txt","fries.txt","vadapav.txt","phone.txt","laptop.txt","watch.txt","tv.txt","ac.txt","cocacola.txt","fizz.txt","juice.txt","coffee.txt","buttermilk.txt"};
    protected int [] price ={20,23,70,10,100,100,70,10,5,4,120,45,100,80,40,30000,100000,18500,150000,125000,40,20,30,50,15};
    protected int [] bought={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ;
}
abstract class Person{   
    String name;
    String age;
    String phone;

    abstract void Filewr(); 

}
class Employee extends Person{
    String salary;

    Employee(String name,String age,String phone,String salary)
    {
        this.name=name;
        this.age=age;
        this.phone=phone;
        this.salary=salary;

    }
    void Filewr()
    {
        try {
            FileWriter writer = new FileWriter("Employee.txt", true);
            
            writer.write(this.name+"\n");
            writer.write(this.age+"\n" );
            writer.write(this.phone+"\n");
            writer.write(this.salary+"\n");   // write new line
            
            writer.write("\n\n\n\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Customer extends Person{
    
    Customer(String name,String age,String phone)
    {
        this.name=name;
        this.age=age;
        this.phone=phone;
    }
    void Filewr()
    {
        try {
            FileWriter writer = new FileWriter("Customer.txt", true);
            
            writer.write(this.name+"\n");
            writer.write(this.age+"\n" );
            writer.write(this.phone+"\n");
            
            writer.write("\n\n\n\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Mart extends Grocery implements WholeSale{
    Mart() {
        for(int i=0;i<25;i++){
            File f= new File(files[i]);
            if(!f.exists()){
                Write(files[i],50);
            }
        }
        
    }
    public void FrontMenu() {
        System.out.println("---------------------------------");
        System.out.println("ENTER 0 to exit\n      1 to enter MAINMENU");
        System.out.println("---------------------------------");
        System.out.print("Enter your choice here: ");
        Scanner sc = new Scanner(System.in);
        try {
            int c = sc.nextInt();
            if (c == 1) MainMenu();
            else bill();
        }
        catch(Exception e){
            bill();
        }
    }
    public void MainMenu() {
        System.out.println("---------------------------------");
        System.out.println("Press 0 to return FRONTMENU");
        System.out.println("      1 to buy FRUITS");
        System.out.println("      2 to buy STATIONARY PRODUCTS");
        System.out.println("      3 to buy SNACKS");
        System.out.println("      4 to buy ELECTRICAL GADGETS");
        System.out.println("      5 to buy REFRESHMENTS");
        System.out.println("      or any other number to exit");
        System.out.println("---------------------------------");
        System.out.print("Enter your choice here: ");
        Scanner sc = new Scanner(System.in);
        try {
            int num = sc.nextInt();
            if (num == 0) FrontMenu();
            else if (num <= 5) Menu((num - 1) * 5);
            else bill();
        }
        catch (Exception e){
            bill();
        }
    }
    public void Menu(int num) {
        Formatter f = new Formatter();
        System.out.println("--------------------------------------");
        f.format("%15s %15s\n", "PRODUCT NAME", "PRICE(in Rs)");
        f.format("--------------------------------------\n");
        for (int i = num; i < num + 5; i++) {
            f.format("%15s %8s\n", product[i], price[i]);
        }
        System.out.print(f);
        System.out.println("--------------------------------------");
        System.out.print("Press ");
        for(int i=num;i<num+6;i++){
            if(i==num) System.out.format("%d to return MainMenu\n",i%5);
            else if(i<=num+5) System.out.format("      %d to buy %s\n",i%5,product[i-1]);
            else if(i==num+5) System.out.format("      %d to buy %                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            s\n",5,product[i-1]);
        }
        System.out.println("      or any other number to exit");
        System.out.println("--------------------------------------");
        System.out.print("Enter your choice here: ");
        Scanner sc=new Scanner(System.in);
        int p=sc.nextInt();
        if(p==0) MainMenu();
        else if(p<=5) shop(num+p-1);
        else bill();
    }
    public void Loaditem()
    {
        System.out.println("--------------------------------------");
        System.out.print("Enter the quantity of product: ");
        Scanner sc= new Scanner(System.in);
        
        System.out.println("--------------------------------------");
        int av=0;
       
        
        for (int i = 1; i <= 25; i++) {
            int v=i-1;
            System.out.println(i+" - "+product[v]);
        }
        int h=1;
        int item=1,qty;
        while(h==1)
        {
            
                System.out.println("Enter item number to be loaded in Shop");
                item=sc.nextInt();
                int g=item-1;
                System.out.println("Enter quantity of the item");
                qty=sc.nextInt();
                File myFile=new File(files[g]);
                try {
                    Scanner reader = new Scanner(myFile);
                    while (reader.hasNextLine()){
                        String line = reader.next();
                        av=Integer.parseInt(line);
                    }
                    reader.close();
                }
                catch (Exception e){
                    av=0;
                }
                Write(files[g],av+qty);

            System.out.println("Want to add more items  (1.YES  0.RETURN TO MAIN MENU)");
            int v;
            v=sc.nextInt();
            if(v==0)
                h=0;

            
        }
        FrontMenu();
        
    }
    public void shop(int num){
        System.out.println("--------------------------------------");
        System.out.print("Enter the quantity of product: ");
        Scanner sc= new Scanner(System.in);
        int q=sc.nextInt();
        System.out.println("--------------------------------------");
        int av=0;
        File myFile=new File(files[num]);
        try {
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()){
                String line = reader.next();
                av=Integer.parseInt(line);
            }
            reader.close();
        }
        catch (Exception e){
            av=0;
        }
        if(av==0){
            System.out.format("SORRY, We are out of %s\n",products[num]);
        }
        else if(q>av){
            System.out.format("We only have %d %s\n",av,products[num]);
            System.out.println("--------------------------------------");
            System.out.println("Press 0 if you don't want to buy");
            System.out.format("      1 if you want to buy remaining %s\n",products[num]);
            System.out.println("--------------------------------------");
            System.out.print("Enter your choice here: ");
            int choice=sc.nextInt();
            if(choice==1){
                System.out.println("--------------------------------------");
                System.out.println("Your desired product has been added to the cart.");
                bought[num]+=av;
                Write(files[num],0);
            }
        }
        else{
            System.out.println("Your desired product has been added to the cart.");
            bought[num]+=q;
            Write(files[num],av-q);
        }
        System.out.println("--------------------------------------");
        System.out.println("Press 0 to return MainMenu");
        System.out.println("      or any other number to exit");
        System.out.println("--------------------------------------");
        System.out.print("Enter your choice here: ");
        try {
            int c = sc.nextInt();
            if (c == 0) MainMenu();
            else bill();
        }
        catch(Exception e){
            bill();
        }
    }
    public void bill() {
        System.out.println("----------------------------------------------------");
        System.out.println("THANK YOU FOR VISITING OUR STORE");
        System.out.println("----------------------------------------------------");
        System.out.println("YOUR BILL IS DISPLAYED BELOW");
        System.out.println("----------------------------------------------------");
        LocalDateTime myDateObj = LocalDateTime.now();
        
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
        Formatter f= new Formatter();
        f.format("%15s %10s %10s %10s\n","PRODUCT NAME","PRICE","BOUGHT","NET");
        f.format("----------------------------------------------------\n");
        int total=0;
        for(int i=0;i<25;i++){
            if(bought[i]>0) {f.format("%15s %10s %10s %10s\n",product[i],price[i],bought[i],price[i]*bought[i]);total+= (long) price[i] *bought[i];}
        }
        f.format("----------------------------------------------------\n");
        f.format("TAX = %2f\n",total*0.18f);
        f.format("----------------------------------------------------\n");
        f.format("TOTAL = %2f\n",total*1.18f);
        System.out.println(f);
        String str1 = Integer.toString(total);
        

        Scanner n=new Scanner(System.in);
        System.out.println("Enter Customer name");
        String name1=n.next();
        System.out.println("Enter age");
        String age1=n.next();
        System.out.println("Enter phone number");
        String phone1=n.next();
        Customer c1=new Customer(name1,age1,phone1);
        c1.Filewr();
        System.out.println("Thankyou for Visiting");
        int z=1;


        try {
            FileWriter writer = new FileWriter("MyFile.txt", true);
            writer.write("Customer Name : ");
            writer.write(name1+"\n");
            writer.write("Total Bill : " );
            writer.write(str1);
            writer.write("\r\n");   // write new line
            writer.write("Items puchased : \n");
            for(int i=0;i<25;i++){
                if(bought[i]>0) {writer.write(z+") "+product[i]+"  ");
                    writer.write(bought[i]+"\n");
                    z++;
                }
            }
            writer.write("\n\n\n\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Write(String fileName,int n){
        FileWriter fw=null;
        try{
            String s=String.valueOf(n);
            fw=new FileWriter(fileName);
            fw.write(s);
            fw.close();
        }
        catch(Exception e){
            System.out.println("--------------------------------------");
            System.out.println("Some error occurred\nPlease try again");
        }
    }
}
public class labpro {
    public static String fileToString(String filePath) throws Exception{
        String input = null;
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer sb = new StringBuffer();
        while (sc.hasNextLine()) {
           input = sc.nextLine();
           sb.append(input);
        }
        return sb.toString();
     }
    public static void main(String[] args) throws FileNotFoundException {
    Scanner sc=new Scanner(System.in);
    int ch;
    System.out.println("---------------------------------");
    System.out.println("Welcome to IIITN SUPERMART");
    System.out.println("---------------------------------\n");
    int jk=1;
    do{
        System.out.println("Enter Your Choice\n");
        System.out.println("1.SHOPPING\n");
        System.out.println("2.EMPLOYEES INFO\n");
        System.out.println("3.LOAD ITEMS IN SHOP\n");
        System.out.println("4.EXIT\n");
        ch=sc.nextInt();
        String name;
        String age;
        String phone;
        String salary;
    switch(ch){
    case 1:
        Mart customer= new Mart();
        customer.FrontMenu();
       
        break;
    case 2:
        int z;
       
        System.out.println("Enter 1 for new employee \n");
        System.out.println("Enter 2 for printing all employee details \n");
        System.out.println("Enter 3 for deleting a employee \n");
        z=sc.nextInt();
        switch(z){
            case 1:
                
                System.out.println("Enter name");
                name=sc.next();
                System.out.println("Enter age");
                age=sc.next();
                System.out.println("Enter phone number");
                phone=sc.next();
                System.out.println("Enter Salary");
                salary=sc.next();
                Employee e1=new Employee(name,age,phone,salary);
                e1.Filewr();
                break;
            case 2:
            try {
                File myObj = new File("Employee.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                  String Grocery = myReader.nextLine();
                  System.out.println(Grocery);
                }
                myReader.close();
              } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
              break;
              case 3:
              
              System.out.println("Enter name");
                      name=sc.next();
                      System.out.println("Enter age");
                      age=sc.next();
                      System.out.println("Enter phone number");
                      phone=sc.next();
                      System.out.println("Enter Salary");
                      salary=sc.next();
      
                 
              
              try {
                  String filePath = "Employee.txt";
                  String result = fileToString(filePath);
                  System.out.println("Contents of the file: "+result);
                  //Replacing the word with desired one
                  result = result.replaceAll(name, "");
                  result = result.replaceAll(age, "");
                  result = result.replaceAll(phone, "");
                  result = result.replaceAll(salary, "");
                  //Rewriting the contents of the file
                  PrintWriter writer = new PrintWriter(new File(filePath));
                  writer.append(result);
                  writer.flush();
                  System.out.println("Contents of the file after replacing the desired word:");
                  System.out.println(fileToString(filePath));
              } catch (Exception e) {
                 System.out.println("File not Exist");
              }
              break;
        }
        

        break;
    case 3:
        Mart cust= new Mart();
        cust.Loaditem();
    
        break;
    case 4:
        System.out.println("EXIT");
        jk=0;
        break;    
    default:
        System.out.println("Wrong Choice");
        break;         
    
    }
}while(jk==1);
    }
}
    

