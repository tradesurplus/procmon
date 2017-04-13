/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procmon;

/**
 *
 * @author john
 */
public class TestProcess {
    public static void main(String[] args) {
        Process p = new Process("Spot FIRM","spotFirm","efxfirm","","firm/firm","legacy","1");
        System.out.println(p);
    }
}
