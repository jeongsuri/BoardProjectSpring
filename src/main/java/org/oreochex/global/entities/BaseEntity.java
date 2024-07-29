package org.oreochex.global.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass //공통 속성화를 위한 상위 클래스. 추상클래스를 상속을 통해 공통으로 사용될 속성들을 공유.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false) //수정불가
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false) //추가불가
    protected LocalDateTime modifiedAt;

    @Column(insertable = false) //추가불가
    protected LocalDateTime deletedAt;
}
