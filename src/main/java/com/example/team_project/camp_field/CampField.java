package com.example.team_project.camp_field;

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

    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Camp camp;

    @OneToMany(mappedBy = "campField", fetch = FetchType.LAZY)
    private List<Order> order;

    @Builder
    public CampField(Integer id, String fieldName, String price, Camp camp) {
        this.id = id;
        this.fieldName = fieldName;
        this.price = price;
        this.camp = camp;
    }
}
