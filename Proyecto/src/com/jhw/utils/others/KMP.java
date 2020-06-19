package com.jhw.utils.others;

/**
 *
 * @author Jesús Hernández Barrios (jhernandezb96@gmail.com)
 */
public class KMP {

    private static String text = "";//text
    private static String lastKey = "";//P = key
    private static int b[] = new int[0];//b = back table

    public static boolean contain(Object text, Object key) {
        return contain(text.toString(), key.toString());
    }

    public static boolean contain(String text, String key) {
        String keyProcess = key.trim().toLowerCase();
        String textProcess = text.trim().toLowerCase();

        if (keyProcess.isEmpty()) {
            return true;
        }
        kmpPreprocess(keyProcess);
        return kmpSearch(textProcess) != -1;
    }

    private static void kmpPreprocess(String key) {//O(m)
        if (lastKey.equalsIgnoreCase(key)) {
            return;
        }
        lastKey = key;
        b = new int[lastKey.length() + 1];

        int i = 0, j = -1;
        b[0] = -1; // starting values
        while (i < lastKey.length()) { // pre-process the pattern string key
            while (j >= 0 && lastKey.charAt(i) != lastKey.charAt(j)) { // different, reset j using b
                j = b[j];
            }
            i++;
            j++; // if same, advance both pointers
            b[i] = j;
        }
    }

    private static int kmpSearch(String textProcess) {// O(n+m) this is similar as kmpPreprocess(), but on string text
        text = textProcess;

        int i = 0, j = 0; // starting values
        while (i < text.length()) { // search through string text
            while (j >= 0 && text.charAt(i) != lastKey.charAt(j)) { // different, reset j using b
                j = b[j];
            }
            i++;
            j++; // if same, advance both pointers
            if (j == lastKey.length()) { // a match found when j == m
                int index = i - j;
                return index;
                //cout << "P is found at index " << index << " in the Text\n";
                //break;//si no quiero seguir iterando
                //j = b[j]; // prepare j for the next possible match
            }
        }
        return -1;
    }

}
