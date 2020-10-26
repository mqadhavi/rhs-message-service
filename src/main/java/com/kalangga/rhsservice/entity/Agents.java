package com.kalangga.rhsservice.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="agents")
public class Agents {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agent_code")
    private String agentCode;

    private String address;

    private String province;

    private String regency;

    private String district;

    private Long zipcode;

    private Float longitude;

    private Float latitude;

    private Double saldo;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
    
    @Transient
    private Integer distance;
}
