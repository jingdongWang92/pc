package com.jcble.jcparking.common.service.system;

/**
 * 系统参数配置
 * @author Jingdong Wang 
 * @date 2017年3月22日 上午9:23:34
 *
 */
public interface SystemConfigService {

	/**
     * 通过key查询系统参数值
     * 
     * @param paramcode
     * @return
     * @throws Exception
     */
    public String getSysCfgParamValueByKey(String paramKey) throws Exception;

}
