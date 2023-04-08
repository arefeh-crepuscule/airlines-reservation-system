public class Admin {
    private final String userName ="admin user";
    private final String passWord = "passadmin2023";


    // check if the enter user and pass word are for admin
    public boolean checkAdmin (String user , String pass){
        return user.equals(userName) && pass.equals(passWord);
    }

    //just print admin menu
    public void printAdminMenu (){
        System.out.println("--------------------------------------------------");
        System.out.println("               Admin Menu Options");
        System.out.printf ("--------------------------------------------------\n\n");
        System.out.println("   <1> Add");
        System.out.println("   <2> Update");
        System.out.println("   <3> Remove");
        System.out.println("   <4> Flight schedules");
        System.out.println("   <0> Sign out");
    }


}
