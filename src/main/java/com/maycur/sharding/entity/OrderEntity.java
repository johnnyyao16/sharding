package com.maycur.sharding.entity;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OrderEntity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long orderId;
    private Long userId;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equal(id, that.id) && Objects.equal(orderId, that.orderId) && Objects.equal(userId, that.userId)
            && Objects.equal(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, orderId, userId, userName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("orderId", orderId).append("userId", userId)
            .append("userName", userName).toString();
    }
}
