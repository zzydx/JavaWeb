package servlet;

import Utlis.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {
    public String login(String username,String password) throws Exception{
        System.out.println(username);
        System.out.println(password);
        return "index";
    }
}
