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
@Table(name = "users")
public class User {
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(length = 50)
    private String name;
    @Column(length = 8)
    private String gender;
    @Column(unique = true)
    private String oneSignalId;
    @Column(unique = true)
    private String studentIdCard;
    private Boolean isStudent;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccountList;
}
