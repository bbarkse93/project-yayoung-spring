package com.example.team_project.option;

import com.example.team_project.option.option_category.OptionCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "option_tb")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String optionName;

    @ManyToOne(fetch = FetchType.LAZY)
    private OptionCategory optionCategory;

    @Builder
    public Option(Integer id, String optionName, OptionCategory optionCategory) {
        this.id = id;
        this.optionName = optionName;
        this.optionCategory = optionCategory;
    }
}
