package com.example.messenger.service;

import com.example.messenger.model.Chat;
import com.example.messenger.model.User;
import com.example.messenger.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Optional<Chat> getChatBetweenUsers(User user1, User user2){
        Optional<Chat> chatOpt = chatRepository.findChatByParticipant1AndParticipant2(user1, user2);
        if (chatOpt.isPresent()) return chatOpt;
        return chatRepository.findChatByParticipant1AndParticipant2(user2,user1);
    }

    public Chat createChat(User user1, User user2){
        Chat chat = new Chat();
        chat.setParticipant1(user1);
        chat.setParticipant2(user2);
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    public Optional<Chat> getChatById(Integer chatId){
        return chatRepository.findById(chatId);
    }

    public List<Chat> getChatsForUser(User user){
        return chatRepository.findByParticipant1OrParticipant2(user, user);
    }
}
