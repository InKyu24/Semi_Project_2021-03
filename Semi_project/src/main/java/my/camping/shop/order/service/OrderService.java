package my.camping.shop.order.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.camping.shop.order.dao.OrderDAO;
import my.camping.shop.order.vo.OrderVO;

@Service
public class OrderService {
	@Autowired
	OrderDAO orderDAO;
	
	public long order(ArrayList<OrderVO> list) throws Exception{
		return orderDAO.order(list);
	}
}

