package com.glens.pwCloudOs.pm.scene.workers.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glens.eap.platform.core.annotation.FormProcessor;
import com.glens.eap.platform.core.web.ControllerForm;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.eap.platform.framework.web.EAPJsonAbstractController;
import com.glens.eap.platform.framework.web.ResponseConstants;
import com.glens.eap.platform.framework.web.support.JsonProcessRequestHandler;
import com.glens.eap.platform.framework.web.support.response.BeanResult;
import com.glens.eap.platform.framework.web.support.response.KeyResult;
import com.glens.eap.platform.framework.web.support.response.ListResult;
import com.glens.eap.platform.framework.web.support.response.PageBeanResult;
import com.glens.eap.platform.framework.web.support.response.ResponseResult;
import com.glens.pwCloudOs.pm.scene.workers.service.ProWorkersService;

@RequestMapping("pmsServices/pm/workers")
@FormProcessor(clazz = "com.glens.pwCloudOs.pm.scene.workers.web.ProWorkersForm")
public class ProWorkersController extends EAPJsonAbstractController {

	@RequestMapping(value = "chooseWorkers", method = RequestMethod.GET)
	public Object chooseWorkers(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProWorkersService pservice = (ProWorkersService) getService();
				ProWorkersForm form = (ProWorkersForm) actionForm;
				form.setStatus("1");
				PageBean page = pservice.chooseWorkersList(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();

				if (page != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProWorkersForm form = (ProWorkersForm) actionForm;
				PageBean page = getService().queryForPage(form.toMap(),
						actionForm.getCurrentPage(), actionForm.getPerpage());
				PageBeanResult result = new PageBeanResult();
				if (page != null) {

					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setPageBean(page);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "getSpotWorkers", method = RequestMethod.GET)
	public Object getSpotWorkers(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProWorkersForm form = (ProWorkersForm) actionForm;
				ProWorkersService service = (ProWorkersService) getService();
				List list = service.getSpotWorkers(form.toMap());
				ListResult result = new ListResult();
				if (list != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功");
					result.setList(list);
				} else {

					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Object insert(HttpServletRequest request,
			HttpServletResponse response) {
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				// TODO Auto-generated method stub
				ProWorkersService pservice = (ProWorkersService) getService();
				KeyResult result = new KeyResult();
				try {
					ProWorkersForm form = (ProWorkersForm) actionForm;
					pservice.insert(form.toVo(), getRootPath(request));
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("新增成功");
					result.setGenerateKey(actionForm.getGenerateKey());
				} catch (Exception e) {
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("新增失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Object update(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProWorkersService pservice = (ProWorkersService) getService();
				ResponseResult result = new ResponseResult();
				try {
					pservice.updateWorkers(actionForm.toVo(),
							getRootPath(request));
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("修改成功");
				} catch (Exception e) {

					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("修改失败");
				}

				return result;
			}

		});
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ResponseResult result = new ResponseResult();
				ProWorkersService pservice = (ProWorkersService) getService();
				try {
					pservice.deleteWorker(actionForm.toVo());
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("删除成功");
				} catch (Exception e) {
					e.printStackTrace();
					result.setStatusCode(ResponseConstants.SERVER_ERROR);
					result.setResultMsg("删除出错");
				}
				return result;
			}

		});
	}

	@RequestMapping(value = "get", method = RequestMethod.GET)
	public Object get(HttpServletRequest request, HttpServletResponse response) {

		return this.process(request, response, new JsonProcessRequestHandler() {

			@Override
			public Object doWithRequest(HttpServletRequest request,
					HttpServletResponse response, ControllerForm actionForm) {
				ProWorkersService pservice = (ProWorkersService) getService();
				ProWorkersForm form = (ProWorkersForm) actionForm;
				Map m = pservice.get(form.getWorkerCode());
				BeanResult result = new BeanResult();
				if (m != null) {
					result.setStatusCode(ResponseConstants.OK);
					result.setResultMsg("获取成功!");
					result.setData(m);
				} else {
					result.setStatusCode(ResponseConstants.NO_DATA);
					result.setResultMsg("没有获取到数据");
				}

				return result;
			}

		});
	}
}
