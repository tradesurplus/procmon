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
public class TestColour {
    public static void main(String[] args) {
        ReportHeaderColour rhc = new ReportHeaderColour("[1;36m");
        System.out.println(rhc);
        ReportProcNotRunningColour rpnrc = new ReportProcNotRunningColour("[1;31m");
        System.out.println(rpnrc);
        ReportProcRunningColour rprc = new ReportProcRunningColour("[1;32m");
        System.out.println(rprc);
        ReportSeparatorColour rsc = new ReportSeparatorColour("[0;37m");
        System.out.println(rsc);
        ReportStandardColour rstc = new ReportStandardColour("[0;37m");
        System.out.println(rstc);
        ReportWarningColour rwc = new ReportWarningColour("[1;33m");
        System.out.println(rwc);
        ReportSequenceTerminator rst = new ReportSequenceTerminator("[m");
        System.out.println(rst);
        ReportEscapeSequence res = new ReportEscapeSequence("\\u001b");
        System.out.println(res);
    }
}
