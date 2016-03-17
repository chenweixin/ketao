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

import com.alibaba.fastjson.JSONObject;
import com.springmvc.ketao.entity.Inform;
import com.springmvc.ketao.service.IInformManager;

@Controller
@RequestMapping("/inform")
public class InformController {

	@Resource(name="informManager")
	private IInformManager informManager;
	
	@RequestMapping("")
	public String index(){
		return "/view/inform";
	}
	
	@RequestMapping("/add")
	public void addInform(Inform inform, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		try {
			informManager.addInform(inform);
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
	
	@RequestMapping("delbyids")
	public void delInformByIds(@RequestParam(value="ids[]") String []ids, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(informManager.delInformsByIds(ids)){
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
	public void updateInform(Inform inform, HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		if(informManager.updateInform(inform)){
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
	public String getInform(HttpServletRequest request){
		String inform_id = request.getParameter("informid");
		Inform inform = informManager.getInform(inform_id);
		request.setAttribute("inform", inform);
		return "/view/inform_info";
	}
	
	@RequestMapping("/getinforms")
	public String getInformsBySearch(HttpServletRequest request){
		String search = request.getParameter("search");
		List<Inform> informs = informManager.getInformsBySearch(search);
		request.setAttribute("informs", informs);
		request.setAttribute("search", search);
		return "/view/inform_result";
	}
	
	@RequestMapping("/getall")
	public String getAllInforms(HttpServletRequest request){
		List<Inform> informs = informManager.getAllInforms();
		request.setAttribute("informs", informs);
		request.setAttribute("search", "全部公告");
		return "/view/inform_result";
	}
}
