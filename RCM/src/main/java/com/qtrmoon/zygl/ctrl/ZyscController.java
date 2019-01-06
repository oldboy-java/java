package com.qtrmoon.zygl.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qtrmoon.common.BaseController;
import com.qtrmoon.sysmanage.SysConstant;
import com.qtrmoon.sysmanage.pojo.User;
import com.qtrmoon.zygl.pojo.Zysc;
import com.qtrmoon.zygl.serdao.IZyglService;

/**
 * 资源收藏表控制器
 */
@Controller
@RequestMapping("/zygl/zysc")
public class ZyscController extends BaseController{
	@Autowired
	private IZyglService zyglService;								//声明模块Service实例

	/**
	 * 查询方法，自带分页设置。
	 * @param zysc 承载查询条件的Bean
	 * @param request 可用来获取非Bean内的参数
	 * @param response 返回请求,输出Json
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/schZysc.action")
	public void schZysc(Zysc zysc,HttpServletRequest request,HttpServletResponse response) throws IOException{
		noCache(response);
		zysc.setCondition("");											//防sql注入
		zysc.assLike("zyid","scrid","scsj","xxid");

		List<Zysc> list=zyglService.schZysc(zysc);		//调用Service查询数据
		/*将数据Bean的集合转换为Json集合*/
		JSONArray datas=new JSONArray();									//构造业务数据的JSON集合
		JSONObject obj;														//声明用于构造Bean的Json对象
		for(Zysc u:list){												//循环查询的数据集构造Json数据集
			obj=u.getJsonInDict();											//将Bean转换为Json对象
			obj.put("_oper",u.getId());								//为"操作列"设定主键值
			datas.add(obj);													//添加Json对象到Json集合
		}
		/*构造分页数据的JSON对象，并包装到返回页面的Json中*/
		JSONObject res=new JSONObject();									//声明页面返回Json
		res.put("total",zysc.getDatasize());							//设置数据总记录数
		res.put("rows", datas);												//设置数据集
		response.getWriter().print(res.toJSONString());						//输出到页面
	}
	
	/**
	 * 预添加or预修改or查看方法
	 * @param zysc 预添加时可传入一些初始值；预修改和查看时用来承载主键值
	 * @param toL 挂字典的字段是否需要转为字典Label值。true则进行转换
	 * @param response 返回请求,输出Json
	 * @throws IOException
	 */
	@RequestMapping(value="/vieZysc.action")
	public void vieZysc(Zysc zysc,Boolean toL,HttpServletResponse response) throws IOException{
		noCache(response);													//Ajax方法设定无缓存
		if(toL!=null&&toL){													//查看页面加载数据时调用，返回的对象已进行字典转换
			if(zysc.getId()!=null&&zysc.getId()!=0){					//检测主键非空
				zysc=zyglService.schZyscById(zysc.getId());			//按主键查询数据
				response.getWriter().print(zysc.getJsonInDict());						//输出经过字典转换Json对象到查看页(vie_)
			}
		}else{																//修改页面加载数据时调用
			if(zysc.getId()!=null&&zysc.getId()!=0){	//检查主键非空
				zysc=zyglService.schZyscById(zysc.getId());		//按主键查询数据
				response.getWriter().print(zysc.getJson());			//输出Json对象到修改页(upd_)
			}else{															//添加页面加载数据时调用，可设置添加页面中一些属性的预设值。
				response.getWriter().print(new Zysc().getJson());		//输出Json对象到添加页(upd_)
			}
		}
	}
	
	/**
	 * 添加或修改的提交方法
	 * @param zysc 数据表单Bean
	 * @param response 返回请求
	 * @throws IOException
	 */
	@RequestMapping(value="/updZysc.action")
	public void updZysc(Zysc zysc,HttpServletResponse response,HttpServletRequest request) throws IOException{
		noCache(response);													//Ajax方法设定无缓存
		User user = (User)request.getSession(true).getAttribute(SysConstant.CURRENT_USER);
		zysc.setScrid(user.getId());
		if(zysc.getId()==null){									//检测主键为空则
			zyglService.addZysc(zysc);						//添加数据
		}else{																//主键非空则
			zyglService.updZysc(zysc);						//修改数据
		}
	}
	
	/**
	 * 删除方法
	 * @param ids 待删除数据的主键数组
	 * @param response 返回请求
	 */
	@RequestMapping(value="/delZysc.action")
	public void delZysc(@RequestParam(value = "ids[]") Integer[] ids,HttpServletResponse response){
		noCache(response);													//Ajax方法设定无缓存
		zyglService.delZysc(ids);									//删除数据
	}
	
	/**
	 * JSP页面跳转
	 * @param page jsp页面文件名，无模块前缀和.jsp后缀
	 * @return 返回跳转的页面
	 */
	@RequestMapping(value="/page.action")
	public String goPage(String page){										//放过本Ctrl的页面，不合法的跳转到error页。
		if("index".equals(page)||"zysc_sch".equals(page)||"zysc_vie".equals(page)||"zysc_upd".equals(page)){
			return "/zygl/"+page;
		}else{
			return "/error";
		}
	}
}
