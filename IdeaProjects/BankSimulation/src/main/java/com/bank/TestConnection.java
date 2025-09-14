package com.bank;

import com.bank.util.DBConfig;

public class TestConnection {
    public static void main(String[] args) {
        DBConfig.checkConnection(); // this will confirm the JDBC connection
    }
}


