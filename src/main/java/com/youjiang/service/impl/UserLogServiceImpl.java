package com.youjiang.service.impl;

import com.youjiang.beans.UserLog;
import com.youjiang.dao.UserLogDao;
import com.youjiang.service.IUserLogService;
import com.youjiang.utils.StringUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by meng on 2017/5/6.
 */
@Service(value = "userLogService")
public class UserLogServiceImpl implements IUserLogService {

    @Autowired
    private UserLogDao userLogDao;
    private UserLog userLog;

    @Override
    public void add(HttpServletRequest request) throws Exception {
        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }

        UserLog userLog = new UserLog();
        userLog.setMethod(request.getMethod());
        if (request.getHeader("x-forward-for") == null) {
            userLog.setIpAddrV4(request.getRemoteAddr());
        } else {
            userLog.setIpAddrV4(request.getHeader("x-forward-for"));
        }

        userLog.setOther(request.getHeader("User-Agent"));
        userLog.setSessionId(request.getSession().getId());
        userLog.setDescription(request.getRequestURI());
        if (!StringUtils.isEmpty(queryString)) {
            userLog.setRequestBody(queryString);
        }

        try {
            UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
            userLog.setOsName(userAgent.getOperatingSystem().getName());
            userLog.setBroName(StringUtils.isEmpty(userAgent.getBrowser().getName()) ? "" : userAgent.getBrowser().getName());
            userLog.setBroVersion(StringUtils.isEmpty(userAgent.getBrowserVersion().getVersion()) ? "" : userAgent.getBrowserVersion().getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            userLogDao.insert(userLog);
        }

    }

    @Override
    public List<UserLog> findAll(int pageNum, int pageSize) {
        pageNum -= 1;
        return userLogDao.selectAll(pageNum * pageSize + 1, pageSize);
    }

    @Override
    public int getAllCount() {
        return userLogDao.countAll();
    }


}
