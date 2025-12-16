package ru.rksp.klosep.data_service.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_event", schema = "utmn")
public class UserEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "event_type")
    private String event_type;

    @Column(name = "event_time")
    private OffsetDateTime event_time;
}
