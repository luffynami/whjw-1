package com.nami.jw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import com.nami.jw.base.JwLogin;
import com.nami.jw.crackcode.ImagePreProcess;
import com.nami.jw.util.JwUtils;

public class JsonOutLogin extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		JSONObject jobj = new JSONObject();
		Map<String, String> map = JwLogin.getBaseValue();// 得到viewstate  urlcode
		String viewstate = map.get("viewstate");
		String urlcode = map.get("urlcode");
		String usernumber = req.getParameter("usernumber");
		String pwd = req.getParameter("pwd");
		String role = req.getParameter("role");
		String xhxm = null;
		String checkcodeUrl = JwUtils.BASE_URL + urlcode + "/CheckCode.aspx";
		for(int i=0; i<5 && xhxm==null; i++){
			try{
				String checkCodeImg = ImagePreProcess.downloadImage(checkcodeUrl+"?", usernumber+".png");
				String secretcode = "";
				if (checkCodeImg != null) {
					secretcode = ImagePreProcess.getAllOcr(checkCodeImg);
				}
				xhxm = JwLogin.isLogin(usernumber, pwd, role, secretcode, viewstate, urlcode); //用户名
			}catch(Exception e){
			}
		}
		if(xhxm != null && !"".equals(xhxm)){
			xhxm = xhxm.substring(0, xhxm.length()-2);
			jobj.put("succ", true);
			jobj.put("username", xhxm);
			jobj.put("usernumber", usernumber);
			jobj.put("urlcode", urlcode);
			jobj.put("returnCode", 1);
		}else{ 
			jobj.put("succ", false);
			jobj.put("returnCode", 0);
		}
		out.write(jobj.toString());
		out.flush();
		out.close();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
