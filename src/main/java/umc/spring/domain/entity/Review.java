package umc.spring.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    @Column(nullable = false, length = 50)
    private String body;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", score=" + score +
                ", store=" + store +
                ", member=" + member +
                ", body='" + body + '\'' +
                '}';
    }

    public void setMember (Member member) {
        this.member = member;
    }

    public void setStore (Store store) {
        this.store = store;
    }

}
