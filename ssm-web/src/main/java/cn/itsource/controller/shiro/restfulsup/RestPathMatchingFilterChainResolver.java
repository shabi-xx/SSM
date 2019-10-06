package cn.itsource.controller.shiro.restfulsup;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RestPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestPathMatchingFilterChainResolver.class);

    public RestPathMatchingFilterChainResolver() {
        super();
    }

    public RestPathMatchingFilterChainResolver(FilterConfig filterConfig) {
        super(filterConfig);
    }

    @Override
    public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }
/*/user--post
/user--put=supAjaxPerm[user:update]
/user/*--delete=supAjaxPerm[user:delete]*/
        String requestURI = getPathWithinApplication(request);
        String[] urls ;
        for (String pathPattern : filterChainManager.getChainNames()) {
            urls = pathPattern.split("--");
            if (urls.length == 2) {
                // 分割出url+httpMethod,判断httpMethod和request请求的method是否一致,不一致直接false
                if (WebUtils.toHttp(request).getMethod().toUpperCase().equals(urls[1].toUpperCase())) {
                    pathPattern = urls[0];
                }
            }
            if (pathMatches(pathPattern, requestURI)) {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("Matched path pattern [" + pathPattern + "] for requestURI [" + requestURI + "].  " +
                            "Utilizing corresponding filter chain...");
                }
                if (urls.length == 2) {
                    pathPattern = pathPattern.concat("--").concat(WebUtils.toHttp(request).getMethod().toUpperCase());
                }
                return filterChainManager.proxy(originalChain, pathPattern);
            }
        }
        return null;
    }

}