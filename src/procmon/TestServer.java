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
public class TestServer {
    public static void main(String[] args) {
        Server s = new Server("Web server", "briweb004", "web");
        System.out.println(s);
    }
}
