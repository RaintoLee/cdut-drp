package com.drp.controller;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.drp.annotations.NoNeedLogin;
import com.drp.service.UpdateService4es;
import com.drp.service.impl.UpdateServiceImpl4es;
@Controller
@RequestMapping("/es/")
public class DoMain {

	@NoNeedLogin
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
		updatejava4es();
		 ModelAndView mav = new ModelAndView("/test", modelMap);
		return mav;
	}
	public void updatejava4es(){
		//从文件读入数据,FileInputStream(需要更新的文件路径)
		try{
			Date date = new Date();
			String add = "";
			DateFormat dFormat1 = new SimpleDateFormat("yyyyMMdd"); //HH表示24小时制；  
			Calendar begin=Calendar.getInstance();
			begin.setTime(date);
			begin.add(Calendar.DAY_OF_MONTH,-1);
			add = dFormat1.format(begin.getTime());
			FileInputStream in = new FileInputStream("/var/lib/tomcat8/log4es/" + add);
			InputStreamReader reader = new InputStreamReader(in, "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			Integer line = 0;
			StringBuffer s = new StringBuffer();
			s.append("testtest");
			/*
			 * 1.每次读一条数据
			 * 2.数据的第一项为操作类型，第二项为index,第三项为type,第四项为id,第五项开始为数据
			 * 3.根据操作类型的不同，放入不同的数组中String[] create,update,delete
			 * 4.分类方向（index->type->id->CRUD）
			 * 5.调用不同的方法进行操作
			 * 6.在方法中对第二项和第三项进行分类处理
			 * 7.c,u,d为三个数组的下标
			 */
			String[] create = new String[10000],
					 update = new String[10000],
					 delete = new String[10000];
			Integer c = 0,
					u = 0,
					d = 0;
			Integer n = 0;
			String data = "";
			String[] dataOne = new String[10000];
			Integer i = 0;
			while((data = br.readLine()) != null){
				dataOne[line] = data;
				line++;
				//如果if判断为真,表示dataOne存够10000条数据，则可以开始进行分类、更新。
				if(line == 10000){
					//进入for循环,根据dataOne[i]数据的操作类型分类，存入相应操作的数组
					for(i = 0 ; i < 10000 ; i++){
						String[] dataSplit = dataOne[i].split(",");
						if(dataSplit[0].equals("createDocument")){
							create[c] = dataOne[i];
							c++;
						}else if(dataSplit[0].equals("updateDocument")){
							update[u] = dataOne[i];
							u++;
						}else if(dataSplit[0].equals("deleteDocument")){
							delete[d] = dataOne[i];
							d++;
						}
					}
					//当一万条数据分类完成时：1.初始化c,u,d;2.初始化line;3.初始化dataOne;4.调用更新方法
					UpdateServiceImpl4es.create4es(create, c);
					UpdateServiceImpl4es.update4es(update, u);
					UpdateServiceImpl4es.delete4es(delete, d);
					c = 0;
					u = 0;
					d = 0;
					i = 0;
					line = 0;
					n = n + 10000;
					System.out.println("完成" + n + "条数据更新。");
				}
			}
			for(i = 0 ; i < line ; i++){
				String[] dataSplit = dataOne[i].split(",");
				if(dataSplit[0].equals("createDocument")){
					create[c] = dataOne[i];
					c++;
				}else if(dataSplit[0].equals("updateDocument")){
					update[u] = dataOne[i];
					u++;
				}else if(dataSplit[0].equals("deleteDocument")){
					delete[d] = dataOne[i];
					d++;
				}
			}
			UpdateServiceImpl4es.create4es(create, c);
			UpdateServiceImpl4es.update4es(update, u);
			UpdateServiceImpl4es.delete4es(delete, d);
			System.out.println("完成" + (n+i) + "条数据更新。");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
