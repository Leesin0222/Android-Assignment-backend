package com.yongjincompany.dsmtcg.service

import com.yongjincompany.dsmtcg.model.Card
import com.yongjincompany.dsmtcg.repository.CardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CardService(private val cardRepository: CardRepository) {
    fun getCards(): List<Card> = cardRepository.findAll()

    fun saveCard(card: Card): Card = cardRepository.save(card)

    fun deleteCardById(id: Long): String {
        val card = cardRepository.findById(id)
        return if (card.isPresent) {
            cardRepository.deleteById(id)
            "Card with id $id has been deleted."
        } else {
            "Card with id $id not found."
        }
    }

    fun markCardAsRead(id: Long, isRead: Boolean): Card? {
        val card = cardRepository.findByIdOrNull(id) ?: return null
        card.isRead = isRead
        card.readAt = if (isRead) LocalDateTime.now() else null
        return cardRepository.save(card)
    }

    fun getRecentlyReadCards(): List<Card> {
        return cardRepository.findByIsReadTrueOrderByReadAtDesc()
    }
}