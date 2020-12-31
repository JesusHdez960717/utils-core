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
package com.root101.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * 
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 */
public class SerialNumber {

    private static String sn = null;

    /**
     * Method for get a Unique identifier for any os
     *
     * @return MAC Address
     */
    public static byte[] getUID() {
        return SHA.hash256(getSimpleUID().getBytes());
    }

    public static String getSimpleUID() {
        String OSName = System.getProperty("os.name");
        String ret = "";
        if (OSName.toLowerCase().contains("Windows".toLowerCase())) {
            ret = getWindowsSerialNumber();
        } else {
            ret = getMacSerialNo();
        }
        return ret;
    }

    //
    // Windows Serial Number
    //
    public static String getWindowsSerialNumber() {
        if (sn != null) {
            return sn;
        }

        InputStream is = null;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(new String[]{"wmic", "bios", "get", "serialnumber"});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        is = process.getInputStream();

        Scanner sc = new Scanner(is);
        try {
            while (sc.hasNext()) {
                String next = sc.next();
                if ("SerialNumber".equals(next)) {
                    sn = sc.next().trim();
                    break;
                }
            }
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (sn == null) {
            throw new RuntimeException("Cannot find computer SN");
        }

        return sn;
    }

    //
    // Mac Serial Number
    //
    public static String getMacSerialNo() {
        if (sn != null) {
            return sn;
        }

        InputStream is = null;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(new String[]{"/usr/sbin/system_profiler", "SPHardwareDataType"});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        is = process.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String marker = "Serial Number";
        try {
            while ((line = br.readLine()) != null) {
                if (line.contains(marker)) {
                    sn = line.split(":")[1].trim();
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (sn == null) {
            throw new RuntimeException("Cannot find computer SN");
        }

        return sn;
    }

    //
    // Linux Get Serial Number
    //
    public static String getLinuxSerialNumber() {

        if (sn == null) {
            readDmidecode();
        }
        if (sn == null) {
            readLshal();
        }
        if (sn == null) {
            throw new RuntimeException("Cannot find computer SN");
        }

        return sn;
    }

    private static BufferedReader read(String command) {

        OutputStream os = null;
        InputStream is = null;

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            process = runtime.exec(command.split(" "));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        os = process.getOutputStream();
        is = process.getInputStream();

        try {
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new BufferedReader(new InputStreamReader(is));
    }

    private static void readDmidecode() {
        String line = null;
        String marker = "Serial Number:";
        BufferedReader br = null;

        try {
            br = read("dmidecode -t system");
            while ((line = br.readLine()) != null) {
                if (line.contains(marker)) {
                    sn = line.split(marker)[1].trim();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void readLshal() {

        String line = null;
        String marker = "system.hardware.serial =";
        BufferedReader br = null;

        try {
            br = read("lshal");
            while ((line = br.readLine()) != null) {
                if (line.indexOf(marker) != -1) {
                    sn = line.split(marker)[1].replaceAll("\\(string\\)|(\\')", "").trim();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
