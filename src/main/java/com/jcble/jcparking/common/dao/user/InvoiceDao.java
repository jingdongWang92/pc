package com.jcble.jcparking.common.dao.user;

import java.util.List;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.user.Invoice;

/**
 * 用户发票dao层接口
 * 
 * @author Jingdong Wang
 * @date 2017年3月14日 下午6:00:45
 *
 */
public interface InvoiceDao extends BaseSimpleDao {

	List<Invoice> queryUserInvoicesByPage(Invoice dto);

}
