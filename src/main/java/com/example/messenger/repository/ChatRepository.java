package com.example.messenger.repository;

import com.example.messenger.model.Chat;
import com.example.messenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    Optional<Chat> findChatByParticipant1AndParticipant2(User participant1, User participant2);
    List<Chat> findByParticipant1OrParticipant2(User participant1, User participant2);

}
