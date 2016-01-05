package com.nami.jw.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.nami.jw.util.JwUtils;


public class JwQuery {
	
	//�ӽ���õ���ǰҳ���viewstateֵ
	public static String getViewstate(String url, String addHeader){
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpGet hg = new  HttpGet(url);
		hg.setHeader("referer", addHeader);
		HttpResponse response = null;
		try {
			response = hc.execute(hg);
			if ("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "utf-8");
					org.jsoup.nodes.Document doc = Jsoup.parse(result);
					return doc.select("input[name=__VIEWSTATE]").val();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(hg != null){
				hg.abort();
			}
		}
		return "";
	}
	//�õ�����ɼ�
	public static List<Map<String, String>> getGrade(String url, String addHeader, String viewstate, 
			String ddlXN, String ddlXQ, String btn_zcj){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("referer", addHeader);
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("__EVENTTARGET",""));
		params.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		params.add(new BasicNameValuePair("__VIEWSTATE", viewstate));
		params.add(new BasicNameValuePair("ddlXN", ddlXN));
		params.add(new BasicNameValuePair("ddlXQ", ddlXQ));
		params.add(new BasicNameValuePair("ddl_kcxz", ""));
		if("btn_xn".equals(btn_zcj)){
			params.add(new BasicNameValuePair("btn_xn", "ѧ��ɼ�"));
		}else if("btn_xq".equals(btn_zcj)){
			params.add(new BasicNameValuePair("btn_xq", "ѧ�ڳɼ�"));
		}else{
			params.add(new BasicNameValuePair("btn_zcj", "����ɼ�"));
		}
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = hc.execute(httppost);
//			System.out.println(response.getStatusLine().toString());
			if ("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "utf-8");
					org.jsoup.nodes.Document doc = Jsoup.parse(result);
					Elements ele = doc.select("table[id=Datagrid1]").select("tr");
					for(int i=0; i<ele.size(); i++){
						Elements tds = ele.get(i).select("td");
						Map<String, String> map =new HashMap<String, String>();
						for(int j=0; j<tds.size(); j++){
							String tdText = tds.get(j).text();
							map.put("td"+j, tdText);
						}
						list.add(map);
					}
					return list;
					//д��
					/*FileOutputStream out = null;
					BufferedWriter writer = null;
					try {
						out = new FileOutputStream("f:\\code\\grade.html");
						writer = new BufferedWriter(new OutputStreamWriter(out));
						writer.write(result);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						if(writer != null){
							try {
								writer.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}*/
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(httppost != null){
				httppost.abort();
			}
		}
		return list;
	}
	
	//�õ��α�
	public static List<Map<String, String>> getTimeTable(String url, String addHeader, String viewstate){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpGet httppost = new HttpGet(url);
		httppost.setHeader("referer", addHeader);
		HttpResponse response = null;
		try {
			response = hc.execute(httppost);
			if ("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "utf-8");
					org.jsoup.nodes.Document doc = Jsoup.parse(result);
					Elements trs = doc.select("table[id=Table1]").select("tr");
					for(int i=2; i<trs.size(); i++){
						Elements tds = trs.get(i).select("td");
						Map<String, String> map =new HashMap<String, String>();
						if(i==3 || i==5 || i==7 || i==9 || i==11|| i==13){
						}else{
							if(i==2 || i==6 || i==10){
								for(int j=2, temp=1; j<tds.size(); j++){
									String tdText = tds.get(j).text();
									//System.out.println(tdText);
									map.put("week"+temp, tdText);
									temp++;
								}
							}else{
								for(int j=1, temp=1; j<tds.size(); j++){
									String tdText = tds.get(j).text();
									//System.out.println(tdText);
									map.put("week"+temp, tdText);
									temp++;
								}
							}
							list.add(map);
							//System.out.println(i+"======================================================================"+list.size());
						}
					}
					return list;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(httppost != null){
				httppost.abort();
			}
		}
		return list;
	}
	
	//�õ�������Ϣ
	/*public static Map<String, String> getUserInfo(String url, String addHeader, String viewstate){
		Map<String, String> map = new HashMap<String, String>();
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpGet httppost = new HttpGet(url);
		httppost.setHeader("referer", addHeader);
		HttpResponse response = null;
		try {
			response = hc.execute(httppost);
			System.out.println(response.getStatusLine().toString());
			if ("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "utf-8");
					System.out.println(result);
					org.jsoup.nodes.Document doc = Jsoup.parse(result);
					Elements trs = doc.select("table[id=Table1]").select("tr");
					map.put("usernumber", trs.get(1).select("td").get(0).text().substring(3));
					map.put("username", trs.get(1).select("td").get(1).text().substring(3));
					map.put("department", trs.get(1).select("td").get(2).text().substring(3));
					map.put("discipline", trs.get(2).select("td").get(0).text().substring(3));
					map.put("classes", trs.get(2).select("td").get(2).text().substring(4));
					return map;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(httppost != null){
				httppost.abort();
			}
		}
		return map;
	}*/
	
	public static Map<String, String> getUserInfo(String url, String addHeader, String viewstate){
		Map<String, String> map = new HashMap<String, String>();
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpGet httppost = new HttpGet(url);
		httppost.setHeader("referer", addHeader);
		HttpResponse response = null;
		try {
			response = hc.execute(httppost);
			System.out.println(response.getStatusLine().toString());
			if ("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "utf-8");
					org.jsoup.nodes.Document doc = Jsoup.parse(result);
					Elements trs = doc.select("table[class=formlist]").select("tr");
					for(int i=0; i<trs.size(); i++){
						Elements tds = trs.get(i).select("td");
						for(int j=0; j<tds.size(); j++){
							String tdText = tds.get(j).text();
							if(i==0 && j==1){
								map.put("usernumber", tdText);
							}else if(i==1 && j==1){
								map.put("username", tdText);
							}else if(i==3 && j==1){
								map.put("sex", tdText);
							}else if(i==11 && j==3){
								map.put("qualifications", tdText); //ѧ��
							}else if(i==12 && j==1){
								map.put("faculty", tdText); //ѧԺ
							}else if(i==14 && j==1){
								map.put("professional", tdText); //רҵ
							}else if(i==16 && j==1){
								map.put("classes", tdText); //�༶
							}else if(i==20 && j==1){
								map.put("level", tdText);
							}
						}
					}
					return map;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(httppost != null){
				httppost.abort();
			}
		}
		return map;
	}
	
	//�õ����԰���
	public static List<Map<String, String>> getExamArrange(String url, String addHeader, String viewstate, boolean isCurrent, String xnd, String xqd){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("referer", addHeader);
		HttpResponse response = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(!isCurrent){
			params.add(new BasicNameValuePair("__EVENTTARGET","xqd"));
			params.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
			params.add(new BasicNameValuePair("__VIEWSTATE", viewstate));
			params.add(new BasicNameValuePair("xnd", xnd));
			params.add(new BasicNameValuePair("xqd", xqd));
		}
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = hc.execute(httppost);
			if ("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					String result = EntityUtils.toString(entity, "utf-8");
					org.jsoup.nodes.Document doc = Jsoup.parse(result);
					Elements ele = doc.select("table[id=DataGrid1]").select("tr");
					for(int i=0; i<ele.size(); i++){
						Elements tds = ele.get(i).select("td");
						Map<String, String> map =new HashMap<String, String>();
						for(int j=0; j<tds.size(); j++){
							String tdText = tds.get(j).text();
							map.put("td"+j, tdText);
						}
						list.add(map);
					}
					return list;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(httppost != null){
				httppost.abort();
			}
		}
		return list;
	}
	
}
