package tables;

public class UsersTable {
    public static final String tableName="users";
    public static final String col_userName="userName";
    public static final String col_firstName="firstName";
    public static final String col_lastName="lastName";
    public static final String col_id="id";
    public static final String col_email="email";
    public static final String col_passcode="passcode";
    public static final String query_login="select * from "+tableName+" where "+col_userName+"=? and "+col_passcode+"=?;";
    public static final String query_register="insert into "+tableName+" (" +col_userName+
            ","+col_passcode+","+col_firstName+","+col_lastName+","+col_email+") VALUES "+" (?,?,?,?,?);";

}
