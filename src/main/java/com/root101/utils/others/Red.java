/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.utils.others;

import java.net.Socket;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class Red {

    /**
     * Check if some service is running in //ip:port
     *
     * @param ip
     * @param port
     * @return
     */
    public static boolean isRunning(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            return true;//socket.isConnected();
        } catch (Exception e) {
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                } catch (Exception e) {
                }
            }
        }
        return false;
    }
}
