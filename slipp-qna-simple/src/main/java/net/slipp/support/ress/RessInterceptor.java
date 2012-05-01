package net.slipp.support.ress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.opensymphony.module.sitemesh.RequestConstants;

public class RessInterceptor extends HandlerInterceptorAdapter {
	private String mobileDecorator;

	public void setMobileDecorator(String mobileDecorator) {
		this.mobileDecorator = mobileDecorator;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Device device = findCurrentDevice();
		if (device.isMobile()) {
			request.setAttribute(RequestConstants.DECORATOR, mobileDecorator);
		}
		return super.preHandle(request, response, handler);
	}

	private Device findCurrentDevice() {
		return DeviceUtils.getCurrentDevice(RequestContextHolder.currentRequestAttributes());
	}
}
