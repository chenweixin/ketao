package com.springmvc.ketao.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springmvc.ketao.entity.Spread;
import com.springmvc.ketao.service.ISpreadManager;

@Controller
@RequestMapping("/spread")
public class SpreadController {

	@Resource(name="spreadManager")
	private ISpreadManager spreadManager;
	
	@RequestMapping("")
	public String index(){
		return "/view/spread";
	}
	
	@RequestMapping("/add")
	public void add(Spread spread, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		try {
			spreadManager.add(spread);
			jsonObject.put("success", "true");
		} catch (Exception e1) {
			jsonObject.put("success", "false");
		} finally {
			response.setContentType("application/json");
			try {
				PrintWriter writer = response.getWriter();
				writer.write(jsonObject.toJSONString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/delbyids")
	public void delByIds(@RequestParam(value="ids[]") String []ids, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(spreadManager.delByIds(ids)){
			jsonObject.put("success", "true");
		}
		else{
			jsonObject.put("success", "false");
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/update")
	public void update(Spread spread, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(spreadManager.update(spread)){
			jsonObject.put("success", "true");
		}
		else{
			jsonObject.put("success", "false");
		}
		response.setContentType("application/json");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(jsonObject.toJSONString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request){
		String spreadid = request.getParameter("spreadid");
		Spread spread = spreadManager.get(spreadid);
		request.setAttribute("spread", spread);
		return "/view/spread_info";
	}
	
	@RequestMapping("/getspreads")
	public String getBySearch(HttpServletRequest request){
		String search = request.getParameter("search");
		List<Spread> spreads = spreadManager.getBySearch(search);
		request.setAttribute("spreads", spreads);
		request.setAttribute("search", search);
		return "/view/spread_result";
	}
	
	@RequestMapping("/getall")
	public String getAll(HttpServletRequest request){
		List<Spread> spreads = spreadManager.getAll();
		request.setAttribute("spreads", spreads);
		request.setAttribute("search", "所有推广");
		return "/view/spread_result";
	}
}
