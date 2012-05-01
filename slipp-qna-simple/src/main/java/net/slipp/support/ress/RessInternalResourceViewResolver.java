package net.slipp.support.ress;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class RessInternalResourceViewResolver extends InternalResourceViewResolver {
	private Logger logger = LoggerFactory.getLogger(RessInternalResourceViewResolver.class);

	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		Device device = findCurrentDevice();
		if (device.isMobile()) {
			viewName = getMobileViewName(viewName);
		}
		logger.debug("ViewName : {}", viewName);
		return (InternalResourceView) super.buildView(viewName);
	}

	protected Object getCacheKey(String viewName, Locale locale) {
		Device device = findCurrentDevice();
		if (device.isMobile()) {
			return super.getCacheKey(getMobileViewName(viewName), locale);
		}
		
		return super.getCacheKey(viewName, locale);
	}
	
	private Device findCurrentDevice() {
		return DeviceUtils.getCurrentDevice(RequestContextHolder.currentRequestAttributes());
	}

	private String getMobileViewName(String viewName) {
		return viewName + ".m";
	}
}