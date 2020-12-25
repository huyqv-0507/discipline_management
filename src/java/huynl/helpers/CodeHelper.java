/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynl.helpers;

/**
 *
 * @author Huy Nguyen
 */
public class CodeHelper {
    public static int getCountOfCode(String code) {
        String tmp[] = code.split(":");
        return Integer.parseInt(tmp[1]);
    }
    public static String generateCode(String code, int count) {
        count += 1;
        return code + "-" + count;
    }
}
