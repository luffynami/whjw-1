package com.nami;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Test {
	public static void postLogin(String usernumber, String pwd, String role) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/WhxyJw/BaseServlet/JsonOutLogin");
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("usernumber", usernumber));
		params.add(new BasicNameValuePair("pwd", pwd));
		params.add(new BasicNameValuePair("role", role));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httppost);
			System.out.println("�ӿڣ�"+ response.getStatusLine().toString());
			if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
				String result = EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void postGrade(String username, String usernumber, String urlcode, 
			String ddlXN, String ddlXQ, String btn_zcj) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/WhxyJw/JwServlet/JsonOutGrade");
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("usernumber", usernumber));
		params.add(new BasicNameValuePair("urlcode", urlcode));
		params.add(new BasicNameValuePair("ddlXN", ddlXN));
		params.add(new BasicNameValuePair("ddlXQ", ddlXQ));
		params.add(new BasicNameValuePair("btn_zcj", btn_zcj));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httppost);
			//System.out.println("�ӿڣ�"+ response.getStatusLine().toString());
			if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
				String result = EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void postTimeTable(String username, String usernumber, String urlcode) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/WhxyJw/JwServlet/JsonOutTimeTable");
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("usernumber", usernumber));
		params.add(new BasicNameValuePair("urlcode", urlcode));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httppost);
			//System.out.println("�ӿڣ�"+ response.getStatusLine().toString());
			if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
				String result = EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param usernumber
	 * @param urlcode  
	 * @param iscurrent  ��true����ȡ��ǰѧ�ڿ��԰��ţ���ʱxnd��xqd��""       ��false����xnd����2014-2015��  xqd ��1�����ǻ�ȡ��ͬѧ�ڵĿ��԰��ţ�����ʵֻ���ǵ�ǰѧ�ڿ��԰��ż���
	 * @param xnd
	 * @param xqd
	 */
	public static void postExamArrange(String username, String usernumber, String urlcode, String iscurrent, String xnd, String xqd) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/WhxyJw/JwServlet/JsonOutExamArrange");
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("usernumber", usernumber));
		params.add(new BasicNameValuePair("urlcode", urlcode));
		params.add(new BasicNameValuePair("xnd", xnd));
		params.add(new BasicNameValuePair("xqd", xqd));
		params.add(new BasicNameValuePair("iscurrent", iscurrent));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httppost);
			//System.out.println("�ӿڣ�"+ response.getStatusLine().toString());
			if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
				String result = EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void postUserInfo(String username, String usernumber, String urlcode) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/WhxyJw/JwServlet/JsonOutUserInfo");
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("usernumber", usernumber));
		params.add(new BasicNameValuePair("urlcode", urlcode));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httppost);
			//System.out.println("�ӿڣ�"+ response.getStatusLine().toString());
			if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
				String result = EntityUtils.toString(response.getEntity(), "utf-8");
				System.out.println(result);
			}else{
				System.out.println("�����ڵ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static void main(String[] args) {
//		postLogin("140101200034", "09050626tnn" , "ѧ��");
		postLogin("130103021125", "Zhu123456" , "ѧ��");
//		postLogin("201404017", "tiandi2015" , "��ʦ");
		String username = "�쵤��";
		String usernumber = "130103021125";
		String urlcode = "(gsawt5550lrhev451gzchn45)";  //�����ÿ�η�����ַ�м�������һ�Σ�����Ҫ��
//		postUserInfo(username, usernumber, urlcode);
//		postGrade(username, usernumber, urlcode, "2013-2014", "1", "btn_xq");
//		postGrade(username, usernumber, urlcode, "", "", "");
//		postTimeTable(username, usernumber, urlcode);
//		postExamArrange(username, usernumber, urlcode, "false", "2013-2014", "2");
	}
}
