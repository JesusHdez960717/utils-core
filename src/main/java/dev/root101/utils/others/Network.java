/*
 * Copyright 2021 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.root101.utils.others;

import java.net.Socket;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class Network {

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
