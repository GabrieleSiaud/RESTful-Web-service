package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 * Represents a member of a band. Each member belongs to a single {@link Band}.
 * This class is mapped to the "member" table in the database.
 */

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    public Member() {
    }

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
