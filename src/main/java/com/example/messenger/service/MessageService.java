package com.example.messenger.service;

import com.example.messenger.model.Message;
import com.example.messenger.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    public Message sendMessage(Message message){
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChatId(Integer chatId){
        return messageRepository.findAllByChatIdOrderByTimestampAsc(chatId);

    }

    public Optional<Message> getMessageById(Integer messageId){
        return messageRepository.findById(messageId);
    }


}
