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
public class TestReportHeader {
    public static void main(String[] args) {
        //ReportHeader rh = new ReportHeader("pc","P C   S T A T U S","-");
        ReportHeader rh = new ReportHeader();
        Server s = new Server("Dell desktop","pc","dell-d400");
        String headerText = String.format("%s        %s        %s (%s)",rh.getHeaderDate(), rh.getHeaderDetail(), rh.getHostname(), s.getDescription());
        StringLength sl = new StringLength(0);
        if (headerText.length() > sl.getHighestCharCount()) {
            sl.setHighestCharCount(headerText);
        }
        System.out.println(headerText);
        System.out.println(rh.getReportLineSeparator(sl.getHighestCharCount()));
    }
}
