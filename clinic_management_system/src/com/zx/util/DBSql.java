package com.zx.util;

/**
 * 
 * @author biao boss 
 *
 */
public class DBSql {
    public static final String FIND_ACCOUNT = "select * from account "
            + "where account_username = ? and account_password = ?";
    public static final String FIND_ACCOUNT_USERNAME="select * from account "
            +" where account_username= ? ";
    public static final String INSERT_ACCOUNT="insert into account values(?,?)";
}
