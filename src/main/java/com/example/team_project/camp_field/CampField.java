package com.example.team_project.camp_field;

import com.example.team_project._core.utils.PriceUtils;
import com.example.team_project.camp.Camp;
import com.example.team_project.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "camp_field_tb")
public class CampField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fieldName;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Camp camp;

    @OneToMany(mappedBy = "campField", fetch = FetchType.LAZY)
    private List<Order> orderList;

    @Builder
    public CampField(Integer id, String fieldName, Integer price, Camp camp) {
        this.id = id;
        this.fieldName = fieldName;
        this.price = price;
        this.camp = camp;
    }

    public Integer orderCount (){
        return orderList.size();
    }

    public String formatPrice(){
        return PriceUtils.formatBalance(price);
    }
}
