    package com.example.messenger.model;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.time.LocalDateTime;
    import java.util.List;

    @Data
    @Entity
    public class Chat {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "participant1_id", nullable = false)
        private User participant1;

        @ManyToOne
        @JoinColumn(name = "participant2_id", nullable = false)
        private User participant2;

        @Column(nullable = false)
        private LocalDateTime createdAt;

        @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Message> messages;
    }
