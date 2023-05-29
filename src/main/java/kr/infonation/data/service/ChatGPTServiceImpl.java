package kr.infonation.data.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService{

    private final ChatgptService chatgptService;

    @Override
    public String getChatResponse(String prompt) {
        return chatgptService.sendMessage(prompt);
    }
}
