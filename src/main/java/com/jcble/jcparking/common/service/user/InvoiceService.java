package com.jcble.jcparking.common.service.user;

import java.util.List;

import com.jcble.jcparking.common.dto.request.InvoiceApplyReqDto;
import com.jcble.jcparking.common.model.user.Invoice;

/**
 * 用户发票业务接口
 * 
 * @author Jingdong Wang
 * @date 2017年3月14日 下午6:02:43
 *
 */
public interface InvoiceService {

	/**
	 * 获取用户所有发票信息
	 * 
	 * @param dto
	 * @return
	 */
	List<Invoice> queryUserInvoicesByPage(Invoice dto);

	/**
	 * 发票详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Invoice getInvoiceDetail(Integer id) throws Exception;

	/**
	 * 申请开票
	 * 
	 * @param reqDto
	 * @throws Exception 
	 */
	void applyInvoice(InvoiceApplyReqDto reqDto) throws Exception;

}
