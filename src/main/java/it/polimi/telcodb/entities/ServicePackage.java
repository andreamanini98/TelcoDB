package it.polimi.telcodb.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "servicePackages", fetch = FetchType.LAZY)
    private List<User> users;

    public ServicePackage() {
    }

}
