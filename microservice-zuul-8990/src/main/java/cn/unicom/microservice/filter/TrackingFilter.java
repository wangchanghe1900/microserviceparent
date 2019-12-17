package cn.unicom.microservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * @author 王长河
 * @create 2019-11-29 10:31
 */
@Component
public class TrackingFilter extends ZuulFilter {
    public static final int FILTER_ORDER=1;
    public static final boolean SHOULD_FILTER=true;


    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isCorrelationIdPresent(){
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
