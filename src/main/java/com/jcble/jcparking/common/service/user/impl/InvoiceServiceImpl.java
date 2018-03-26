package com.jcble.jcparking.common.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcble.jcparking.common.CommonEnum;
import com.jcble.jcparking.common.dao.admin.ParkingOrderDao;
import com.jcble.jcparking.common.dao.user.InvoiceDao;
import com.jcble.jcparking.common.dto.request.InvoiceApplyReqDto;
import com.jcble.jcparking.common.dto.request.OrderReqDto;
import com.jcble.jcparking.common.exception.ParkingServiceException;
import com.jcble.jcparking.common.model.admin.ParkingOrder;
import com.jcble.jcparking.common.model.user.Invoice;
import com.jcble.jcparking.common.service.user.InvoiceService;
import com.jcble.jcparking.common.utils.Generator;

import baseproj.common.util.DateUtil;

/**
 * 用户发票业务接口实现
 * 
 * @author Jingdong Wang
 * @date 2017年3月14日 下午6:02:43
 *
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private ParkingOrderDao orderDao;

	@Override
	public List<Invoice> queryUserInvoicesByPage(Invoice dto) {
		return invoiceDao.queryUserInvoicesByPage(dto);
	}

	@Override
	public Invoice getInvoiceDetail(Integer id) throws Exception {
		Invoice resp = new Invoice();
		Invoice dto = new Invoice();
		dto.setId(id);
		Invoice invoice = invoiceDao.select(dto);
		if (invoice != null) {
			resp.setInvoiceNo(invoice.getInvoiceNo());
			resp.setAmount(invoice.getAmount());
			resp.setInvoiceHead(invoice.getInvoiceHead());
			resp.setStatus(invoice.getStatus());
			resp.setReceiver(invoice.getReceiver());
			resp.setLinkPhone(invoice.getLinkPhone());
			resp.setAddress(invoice.getAddress());
			resp.setIsFreeDelivery(invoice.getIsFreeDelivery());
		}
		return resp;
	}
	

	@Override
	public void applyInvoice(InvoiceApplyReqDto reqDto) throws Exception {
		List<OrderReqDto> orders = reqDto.getOrder();
		if(orders == null || orders.size() < 1) {
			return;
		}
		if(reqDto.getAmount() == null || reqDto.getAmount().intValue() < 1) {
			throw new ParkingServiceException(ParkingServiceException.ERROR_10019);
		}
		//更新订单开票状态
		for (OrderReqDto reqOrder : orders) {
			ParkingOrder orderDto = new ParkingOrder();
			orderDto.setId(reqOrder.getOrderId());
			ParkingOrder order = orderDao.select(orderDto);
			if(order == null) {
				throw new ParkingServiceException(ParkingServiceException.ERROR_10009);
			}
			order.setOrderInvoiceStatus(CommonEnum.OrderInvoiceStatus.Applyed.code);
			orderDao.update(order);
		}
		
		String invoceNo = Generator.generateOrderNo();
		Invoice invoice = new Invoice();
		invoice.setInvoiceNo(invoceNo);
		invoice.setUserId(reqDto.getUserId());
		invoice.setAmount(reqDto.getAmount());
		invoice.setInvoiceHead(reqDto.getInvoiceHead());
		invoice.setReceiver(reqDto.getRecevier());
		invoice.setLinkPhone(reqDto.getLinkPhone());
		invoice.setAddress(reqDto.getAddress());
		invoice.setStatus(CommonEnum.InvoiceStatus.Review.code);
		invoice.setApplyDate(DateUtil.getDateTime());
		invoiceDao.insert(invoice);
	}

}
