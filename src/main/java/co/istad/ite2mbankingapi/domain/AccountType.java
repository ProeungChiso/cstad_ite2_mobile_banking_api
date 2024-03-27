package co.istad.ite2mbankingapi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 100, unique = true, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Boolean idDeleted;

    @OneToMany(mappedBy = "accountType")
    private List<Account> accounts;
}
