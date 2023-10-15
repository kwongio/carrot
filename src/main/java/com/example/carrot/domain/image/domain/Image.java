package com.example.carrot.domain.image.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private String image;//이미지 경로
    private String title;//이미지 제목
    private boolean isTemp;//임시 이미지 여부

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Builder
    public Image(Long id, String image, String title, boolean isTemp, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.isTemp = isTemp;
        this.lastModifiedDate = lastModifiedDate;
    }
}
