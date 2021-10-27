package by.brazengroup.gleb.karpuk.DemoProject.model;

import by.brazengroup.gleb.karpuk.DemoProject.enums.Type;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "is_read")
    private Boolean is_read;

}
