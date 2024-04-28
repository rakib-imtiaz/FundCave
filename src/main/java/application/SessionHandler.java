package application;

public class SessionHandler {
   public static String studentID;

   public static void setSession(String studentID)
   {
       SessionHandler.studentID =studentID;
   }
   public static String getSession()
   {
       return  SessionHandler.studentID;
   }


}
