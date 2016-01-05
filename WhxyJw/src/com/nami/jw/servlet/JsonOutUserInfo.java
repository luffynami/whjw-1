package com.nami.jw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.nami.jw.base.JwBaseUrl;
import com.nami.jw.base.JwQuery;

public class JsonOutUserInfo extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		
		PrintWriter out = resp.getWriter();
		JSONObject jobj = new JSONObject();
		
		String username = req.getParameter("username");
		String usernumber = req.getParameter("usernumber");
		String urlcode = req.getParameter("urlcode");
		String userInfoUrl = JwBaseUrl.getUserInfoUrl(urlcode, usernumber, username);
		String mainHeader = JwBaseUrl.getMainHeader(urlcode, usernumber);
		String viewstate = JwQuery.getViewstate(userInfoUrl, mainHeader);
		Map<String, String> map = JwQuery.getUserInfo(userInfoUrl, userInfoUrl, viewstate);
		
		if(map == null || map.size()<1){
			jobj.put("succ", false);
			jobj.put("returnCode", 0);
		}else{
			jobj.put("succ", true);
			jobj.put("returnCode", 1);
			JSONObject jObject = JSONObject.fromObject(map);
			jobj.put("data", jObject);
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
