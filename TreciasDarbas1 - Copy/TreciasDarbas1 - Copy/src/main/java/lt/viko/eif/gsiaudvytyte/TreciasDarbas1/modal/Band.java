package lt.viko.eif.gsiaudvytyte.TreciasDarbas1.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

/**
 * Represents a musical band entity with attributes such as title, genre, and date.
 * This class is mapped to the "band" table in the database.
 * A band can have multiple associated {@link Member} entities.
 */

@Entity
@Table(name = "band")
public class Band {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String title;
    private String genre;
    private String date;

    @JsonIgnore
    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL)
    private List<Member> members;

    public Band() {
    }


    public Band(int id,String title, String genre, String date) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
