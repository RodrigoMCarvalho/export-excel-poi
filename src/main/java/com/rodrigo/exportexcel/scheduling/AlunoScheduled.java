package com.rodrigo.exportexcel.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
public class AlunoScheduled {

    //@Scheduled(cron = "5 * * * * *", zone = "America/Sao_Paulo")  //A cada 5 segundo de 1 minuto. Ex: 12:01:05, 12:02:05
    //@Scheduled(cron = "0 0 8 * * 1,2,3,4,5", zone = "America/Sao_Paulo") //At 08:00 AM, only on Monday, Tuesday, Wednesday, Thursday, and Friday
    @Scheduled(cron = "0/5 * * * * *", zone = "America/Sao_Paulo") //A cada 5 segundo
    public void executar() {
        System.out.println("Executou o Scheduled com delay " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
