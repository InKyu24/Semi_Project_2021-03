package my.camping.shop.order.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.camping.shop.order.vo.OrderVO;

@Repository
public class OrderDAO{

		@Autowired
		SqlSession sqlSession;
		
		public long order(ArrayList<OrderVO> list) {
			long order_group_no=getOrder_group_no();
			for(OrderVO orderVO:list) {		
				orderVO.setOrder_group_no(order_group_no);
				System.out.println(list);
				// sqlSession.insert("mapper.order.order",orderVO);
			}
			return order_group_no;
		}
		
		private long getOrder_group_no() {
			long order_group_no=sqlSession.selectOne("mapper.order.order_group_no");
			System.out.println(order_group_no);
			return order_group_no;
		}

}

