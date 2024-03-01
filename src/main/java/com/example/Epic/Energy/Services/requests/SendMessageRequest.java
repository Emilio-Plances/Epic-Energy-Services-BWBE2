package com.example.Epic.Energy.Services.requests;

import lombok.Data;

@Data
public class SendMessageRequest {
    private String destinatario;
    private String oggetto;
    private String messaggio;
}
