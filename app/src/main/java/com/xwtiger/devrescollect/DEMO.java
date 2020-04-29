package com.xwtiger.devrescollect;

import java.util.ArrayList;
import java.util.List;

public class DEMO {

	public static void main(String[] args) {
		C1();
//		D3();
//		
		p();
	}

//	static String[] cards = new String[] { "card_82", "card_97", "card_67", "card_30", "card_92", "card_64", "card_94",
//			"card_95", "card_96", "card_80", "card_81", "card_87", "card_9", "card_90" }; //100,101,102,82,21
	static String[] cards = new String[] { "card_82", "card_97", "card_67", "card_30", "card_92", "card_64", "card_94",
			"card_95", "card_96", "card_80", "card_81", "card_87", "card_9", "card_90" ,"card_100","card_101","card_102","card_82","card_21"};
	static List<String> configs = new ArrayList<String>();
	static String msg = "<key>%s</key><string>%s</string>";

	
//	static String[] cards_d2=new String[]
//			{"card_82","card_97","card_67","card_30","card_92","card_64","card_94","card_95","card_96","card_81","card_87","card_90","card_9"};
//	static void D2(){
//		for(int i=0;i<cards_d2.length;i++){
//			configs.add(String.format(msg, cards_d2[i] + "&amp;" + "card_20", "card_line_22"));
//		}
//		
//	}
//	
//	static void D3(){
//		for(int i=0;i<cards_d2.length;i++){
//			configs.add(String.format(msg, "card_20"+ "&amp;" + cards_d2[i] , "card_line_21"));
//		}
//		
//	}
//	
	
	static void C1() {

		for (int i = 0; i < cards.length; i++) {
			for (int j = 0; j < cards.length; j++) {
				configs.add(String.format(msg, cards[i] + "&amp;" + cards[j], "card_line_21"));
			}
		}

		
	}
	
	
	static void p(){
		for (int i = 0; i < configs.size(); i++) {
			System.out.println("" + configs.get(i));
		}
//		System.out.println("size: " + "(" + cards.length + "*" + cards.length + ") = " + configs.size());
	}

	public static List removeDuplicate(List list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(j);
				}
			}
		}
		return list;
	}

}
