package com.nami.jw.base;

import com.nami.jw.util.JwUtils;

public class JwBaseUrl {
	
	public static String getMainHeader(String urlcode, String usernumber){
		return JwUtils.BASE_URL + urlcode + "/xs_main.aspx?xh="+usernumber;
	}
	//���꿼�Գɼ�
	public static String getAllGradeUrl(String urlcode, String usernumber, String username){
		return JwUtils.BASE_URL + urlcode + "/xscjcx.aspx?xh=" + usernumber + "&xm=" + username + "&gnmkdm=N121613";
	}
	//���˿α�
	public static String getTimeTableUrl(String urlcode, String usernumber, String username){
		return JwUtils.BASE_URL + urlcode + "/xskbcx.aspx?xh=" + usernumber + "&xm=" + username + "&gnmkdm=N121613";
	}
	//���԰���
	public static String getExamArrangeUrl(String urlcode, String usernumber, String username){
		return JwUtils.BASE_URL + urlcode + "/xskscx.aspx?xh=" + usernumber + "&xm=" + username + "&gnmkdm=N121603";
	}
	//����ͼ��
	public static String getUserPhotoUrl(String urlcode, String usernumber){
		return JwUtils.BASE_URL + urlcode + "/readimagexs.aspx?xh="+usernumber;
	}
	//������Ϣ
//	public static String getUserInfoUrl(String urlcode, String usernumber, String username){
//		return JwUtils.BASE_URL + urlcode + "/xscjcx.aspx?xh=" + usernumber + "&xm=" + username + "&gnmkdm=N121613";
//	}
	public static String getUserInfoUrl(String urlcode, String usernumber, String username){
		return JwUtils.BASE_URL + urlcode + "/xsgrxx.aspx?xh=" + usernumber + "&xm=" + username + "&gnmkdm=N121501";
	}
	
}
