package Filetransfer;

import org.apache.poi.util.SystemOutLogger;

public class AREC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String RecordCount = "8";
		int rec = Integer.parseInt(RecordCount) +2;
		String ARecordCount = "0000000000".substring((rec+"").length()) + rec;
		System.out.println(ARecordCount);
		String DetailRecord3 ="0002290178000.00000.00002.00002.00002.00030301007201000002031000********C***007***********03S0335R0000000000000000800000000000000010000000128088080";
		System.out.println(DetailRecord3.length());
		String check="0002290178000.00000.00003.00002.00002.00030303007203000002031000********C***007***********01S0335R0000000000000000800000000000000010000000128088080";
		System.out.println(check.length());
	}

}
