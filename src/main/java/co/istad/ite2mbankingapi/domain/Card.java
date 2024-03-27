package co.istad.ite2mbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false, length = 10)
    private String number;
    private String cvv;
    private String holder;
    private LocalDate issueAt;
    private LocalDate expiredAt;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CardType cardType;
}
