package com.nami.jw.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
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
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nami.jw.util.JwUtils;


public class JwLogin {
	/**
	 * 通过Post请求得到Location中的头部请求的实际网址信息，解析出中间那段上面说的很重要的字符串，作用是标志这是一次有效访问。
	 * 在通过Get请求解析出隐藏在当前页面的__VIEWSTATE的值
	 * @return
	 */
	public static Map<String, String> getBaseValue() {
		Map<String, String> map = new HashMap<String, String>();
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpGet hg = null;
		HttpPost hp = new HttpPost(JwUtils.BASE_URL);
		HttpResponse responseGet = null;
		HttpResponse responsePost = null;
		try {
			responsePost = hc.execute(hp);
			if("HTTP/1.1 302 Found".equals(responsePost.getStatusLine().toString())){
				Header lheader = responsePost.getFirstHeader("Location");
				String urlcode = lheader.getValue();
				urlcode = urlcode.substring(urlcode.indexOf("("), (urlcode.indexOf(")")+1));
				map.put("urlcode", urlcode);
				hg = new HttpGet(JwUtils.BASE_URL+urlcode+"/default2.aspx");
				responseGet = hc.execute(hg);
				if ("HTTP/1.1 200 OK".equals(responseGet.getStatusLine().toString())) {
					HttpEntity entity = responseGet.getEntity();
					if (entity != null) {
						String result = EntityUtils.toString(entity, "utf-8");
						org.jsoup.nodes.Document doc = Jsoup.parse(result);
						map.put("viewstate", doc.select("input[name=__VIEWSTATE]").val());
					}
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
			if(hp != null){
				hp.abort();
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @param hm 用户名
	 * @param pwd 密码
	 * @param role 用户类型
	 * @param secretcode 验证码
	 * @param viewstate 页面隐藏的__VIEWSTATE的值
	 * @param urlcode 网址中那段标志是此次有效请求的值
	 * @return
	 */
	public static String isLogin(String hm, String pwd, String role, String secretcode, String viewstate, String urlcode) {
		CloseableHttpClient hc = JwUtils.getHttpClient();
		HttpPost hp = new HttpPost(JwUtils.BASE_URL+urlcode+"/default2.aspx");
		HttpResponse responsePost = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("__VIEWSTATE", viewstate));
		params.add(new BasicNameValuePair("txtUserName", hm));
		params.add(new BasicNameValuePair("TextBox2", pwd));
		params.add(new BasicNameValuePair("txtSecretCode", secretcode));
		try {
			params.add(new BasicNameValuePair("RadioButtonList1", new String("教师".getBytes("gb2312"), "8859_1")));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		params.add(new BasicNameValuePair("Button1", ""));
		params.add(new BasicNameValuePair("lbLanguage", ""));
		params.add(new BasicNameValuePair("hidPdrs", ""));
		params.add(new BasicNameValuePair("hidsc", ""));
		try {
			hp.setEntity(new UrlEncodedFormEntity(params));
			responsePost = hc.execute(hp);
			Document doc2 = Jsoup.parse(EntityUtils.toString(responsePost.getEntity(), "utf-8"));
			// 获得跳转的网址
			System.out.println("responsePost:  "+responsePost.toString());
			Header locationHeader = responsePost.getFirstHeader("Location");
			if (locationHeader != null && "HTTP/1.1 302 Found".equals(responsePost.getStatusLine().toString())) {
				String login_success = locationHeader.getValue();// 获取登陆成功之后跳转链接
				HttpGet httpget = new HttpGet(JwUtils.BASE_URL+login_success);
				HttpResponse re2 = hc.execute(httpget);
				Document doc = Jsoup.parse(EntityUtils.toString(re2.getEntity(), "utf-8"));
				Element e = doc.getElementById("xhxm");
				if(e==null){
					return null;
				}else{
					System.out.println("登陆成功");
					return e.text();
				}
			} else{
				System.out.println("登陆不成功，请稍后再试!");
				return null;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(hp != null){
				hp.abort();
			}
		}
		return null;
	}
}
