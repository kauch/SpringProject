package com.netcracker.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppOrder {
	@NotNull
    private Long ownerID;
	@NotNull
    private String destPoint; 
    private Long orderID;
    private int weight;
    
    private static Logger log = LogManager.getLogger(AppOrder.class);

    public AppOrder(Long owner, Long id, int weight, String dest){
        this.ownerID = owner;
        this.orderID = id;
        this.weight = weight;
        this.destPoint = dest;
    }
    
    AppOrder(Long owner, String destPoint){
        this.ownerID = owner;
        orderID = uniqueID();
        weight = Integer.parseInt(null);
        this.destPoint = destPoint;

    }

    public Long uniqueID() {
    	LocalDateTime id = LocalDateTime.now();
    	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMDDHHmmssSSS");
        String dataNew = fmt.format(id);
        Random myRandom = new Random();
        StringBuilder bld = new StringBuilder();
        bld.append(dataNew);
        for(int i = 0; i < 3; i++){
        	bld.append(myRandom.nextInt(9));
        }
        Long result = Long.valueOf(bld.toString());
        log.info("id new order = " + result);
        return result;
    }
    
    
    public String getDestPoint() {
        return destPoint;
    }

    public void setDestPoint(String destPoint) {
        this.destPoint = destPoint;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long owner) {
        this.ownerID = owner;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderId) {
        this.orderID = orderId;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Order №" + orderID +
                ", owner = " + ownerID +
                ", weight = " + weight + 
                ", destination = " + destPoint;
    }
}
