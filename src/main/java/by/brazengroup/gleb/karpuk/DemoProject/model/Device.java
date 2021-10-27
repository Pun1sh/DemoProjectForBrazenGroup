package by.brazengroup.gleb.karpuk.DemoProject.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="project_id",referencedColumnName = "id")
    private Project project;
    @Column(name = "serial_number")
    private String serialNumber;
    @OneToMany(mappedBy = "device")
    private List<Event> events;

}
