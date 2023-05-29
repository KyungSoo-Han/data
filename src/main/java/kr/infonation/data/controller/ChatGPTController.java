package kr.infonation.data.controller;

import kr.infonation.data.dto.ChatGPTDto;
import kr.infonation.data.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping
    public String getChatResponse(@RequestBody ChatGPTDto chatGPTDto){
        return chatGPTService.getChatResponse(chatGPTDto.getRequestMessage());
    }

}
