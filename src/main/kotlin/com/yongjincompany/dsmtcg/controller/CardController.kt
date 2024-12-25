package com.yongjincompany.dsmtcg.controller

import com.yongjincompany.dsmtcg.model.Card
import com.yongjincompany.dsmtcg.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/card")
class CardController(private val cardService: CardService) {

    @GetMapping
    fun getAllCards(): List<Card> = cardService.getCards()

    @PostMapping
    fun addCard(@RequestBody card: Card): Card = cardService.saveCard(card)

    @PatchMapping("/read/{id}")
    fun markCardAsRead(@PathVariable id: Long, @RequestParam isRead: Boolean): ResponseEntity<Card> {
        val updatedCard = cardService.markCardAsRead(id, isRead)
        return if (updatedCard != null) {
            ResponseEntity.ok(updatedCard)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @GetMapping("/read")
    fun getRecentlyReadCards(): ResponseEntity<List<Card>> {
        val recentlyReadCards = cardService.getRecentlyReadCards()
        return ResponseEntity.ok(recentlyReadCards)
    }

    @DeleteMapping("/{id}")
    fun deleteCardById(@PathVariable id: Long): ResponseEntity<String> {
        return try {
            val result = cardService.deleteCardById(id)
            if (result.contains("not found")) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(result)
            } else {
                ResponseEntity.ok(result)
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting the card.")
        }
    }
}