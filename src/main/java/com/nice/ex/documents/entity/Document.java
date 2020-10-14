package com.nice.ex.documents.entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import static com.nice.ex.documents.entity.Document.NAME_UNIQUE_CONSTRAINT_NAME;

@Entity
@Table(
        name = "document",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = NAME_UNIQUE_CONSTRAINT_NAME)}
)
public class Document {

    public static final String NAME_UNIQUE_CONSTRAINT_NAME = "unique_name_constraint";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String profession;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
