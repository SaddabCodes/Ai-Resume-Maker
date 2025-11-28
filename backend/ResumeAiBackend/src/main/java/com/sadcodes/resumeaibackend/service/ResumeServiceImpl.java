package com.sadcodes.resumeaibackend.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ResumeServiceImpl implements ResumeService {

    private final ChatClient chatClient;

    public ResumeServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String generateResumeService(String userResumeDescription) throws IOException {

        String promptString = this.loadPromptFromFile("resume_prompt,txt");
        String  promptContent = this.putValuesToTemplate(promptString,Map.of("userDescription",userResumeDescription));

        Prompt prompt = new Prompt(promptContent);

        String response = chatClient.prompt(prompt).call().content();
        return response;
    }

   public String loadPromptFromFile(String fileName) throws IOException {



        Path path = new ClassPathResource(fileName).getFile().toPath();
        return Files.readString(path);
    }

   public String putValuesToTemplate(String template, Map<String ,String> values){
        for (Map.Entry<String ,String > entry: values.entrySet())
        {
            template.replace("{" + entry.getKey()+ "}",entry.getValue());
        }
   }
}
