package com.example.team_project.option_management;

import com.example.team_project.camp.Camp;
import com.example.team_project.option.Option;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "option_management_tb")
public class OptionManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Camp camp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    @Builder
    public OptionManagement(Integer id, Camp camp, Option option) {
        this.id = id;
        this.camp = camp;
        this.option = option;
    }
}
