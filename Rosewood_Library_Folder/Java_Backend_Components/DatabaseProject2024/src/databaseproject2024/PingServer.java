/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseproject2024;

import java.io.*;
import java.net.*;

public class PingServer {
    public static void main(String[] args) {
        String server = "LAPTOP-EBU3JLQG";  // Replace with your server name or IP address

        try {
            InetAddress inetAddress = InetAddress.getByName(server);
            System.out.println("Pinging " + server + "...");
            if (inetAddress.isReachable(5000)) {  // Timeout in milliseconds (5 seconds)
                System.out.println("Server is reachable.");
            } else {
                System.out.println("Server is not reachable.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to ping the server: " + e.getMessage());
        }
    }
}
