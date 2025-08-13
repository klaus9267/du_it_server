package duit.server.domain.view.service

import duit.server.domain.event.entity.Event
import duit.server.domain.view.entity.View
import jakarta.persistence.EntityNotFoundException
import duit.server.domain.view.repository.ViewRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ViewService(
    private val viewRepository: ViewRepository
) {
    fun createView(event: Event) = viewRepository.save(View(event = event))

    @Transactional
    fun increaseCount(eventId: Long) {
        val view = viewRepository.findByEventId(eventId)
            ?: throw EntityNotFoundException("조회수 정보를 찾을 수 없습니다: $eventId")
        view.increaseCount()
    }
}