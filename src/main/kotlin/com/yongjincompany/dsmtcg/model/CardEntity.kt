package com.yongjincompany.dsmtcg.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "card")
data class Card(
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) id자동생성 막기.
    val id: Long,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val grade: String,

    @Column(name = "image_url", nullable = false)
    val imageUrl: String,

    @Column(nullable = false)
    val description: String,

    @Column(name = "is_read", nullable = false)
    var isRead: Boolean = false,

    @Column(name = "read_at", nullable = true)
    var readAt: LocalDateTime? = null
)