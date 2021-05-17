package com.doubledragon.webservice.domain.posts;

import com.doubledragon.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String coinName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String riseRate;
    private String declineRate;

    @Builder
    public Posts(String coinName, String content, String riseRate, String declineRate) {
        this.coinName = coinName;
        this.content = content;
        this.riseRate = riseRate;
        this.declineRate = declineRate;
    }
}