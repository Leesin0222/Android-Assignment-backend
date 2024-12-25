package com.yongjincompany.dsmtcg.repository

import com.yongjincompany.dsmtcg.model.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long> {

    fun findByIsReadTrueOrderByReadAtDesc(): List<Card>
}