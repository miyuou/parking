package com.smartpark.places.domaine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking_spot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSpot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private boolean active = true;

  @ManyToOne
  @JoinColumn(name = "zone_id")
  private Zone zone;
}
