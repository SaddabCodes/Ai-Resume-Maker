package com.sadcodes.resumeaibackend.service;

import org.springframework.ai.chat.client.ChatClient;

public class ResumeServiceImpl implements ResumeService{

    private final ChatClient chatClient;

    public ResumeServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String generateResumeService(String userResumeDescription) {
        return "";
    }
}
