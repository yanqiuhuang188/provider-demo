package com.nimble.provider.controller;

import com.nimble.provider.ProviderApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProviderControllerTest extends ProviderApplicationTests {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();

        PreparedStatement ps1 =
                connection.prepareStatement("SELECT t.name AS name FROM user1 t WHERE t.id = '1'");
        ResultSet rs1 = ps1.executeQuery();

        printUserName(rs1);

        PreparedStatement ps2 =
                connection.prepareStatement("SELECT t.name AS name FROM user1 t WHERE t.id = '2'");
        ResultSet rs2 = ps2.executeQuery();

        printUserName(rs2);

        System.out.println("Test");
    }

    private void printUserName(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("***********************************");
            System.out.println("***********************************");
            System.out.println(rs.getString("name"));
            System.out.println("***********************************");
            System.out.println("***********************************");
        }
    }
}