package com.netcracker.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.netcracker.mapper.AppOrderMapper;
import com.netcracker.model.AppOrder;
import com.netcracker.model.AppUser;

public class AppOrderDAO extends JdbcDaoSupport {

	private static Logger log = LogManager.getLogger(AppOrderDAO.class);

	AppOrderDAO(DataSource source) {
		this.setDataSource(source);
	}

	public boolean createOrder(AppOrder order) {
		boolean status = false;
		Object[] params = new Object[] { order.getOwnerID(), order.getOrderID(), order.getWeight(),
				order.getDestPoint() };
		String query = "INSERT into APP_ORDER (OWNER_ID, ORDER_ID, WEIGHT, DESTINATION_POINT) values (?,?,?,?)";
		try {
			this.getJdbcTemplate().update(query, params);
			status = true;

		} catch (DuplicateKeyException e) {
			log.info(order.toString() + "is duplicated");
		}
		return status;
	}

	public List<AppOrder> getAllOrders() {
		return this.getJdbcTemplate().query(AppOrderMapper.BASE_SQL, new AppOrderMapper());
	}

	public List<AppOrder> getOwnerOrders(AppUser owner) { // Метод для получения заказов одного пользователя

		String query = AppOrderMapper.BASE_SQL + " where Owner_ID = ?";
		List<AppOrder> orders = null;
		AppOrderMapper mapper = new AppOrderMapper();
		try {
			orders = this.getJdbcTemplate().query(query, mapper, owner.getUserId());
		} catch (EmptyResultDataAccessException e) {
			log.info("There is some problems with getting orders for user" + owner.toString());
		}
		return orders;
	}

	public List<AppOrder> getDestOrders(String dest) {// метод для полученя заказов по одному адресу
		String query = AppOrderMapper.BASE_SQL + " where Destination_Point = ?";
		List<AppOrder> orders = null;
		AppOrderMapper mapper = new AppOrderMapper();
		try {
			orders = this.getJdbcTemplate().query(query, mapper, dest);
		} catch (EmptyResultDataAccessException e) {
			log.info("There is some problems with getting orders for place named" + dest);
		}
		return orders;
	}

}
