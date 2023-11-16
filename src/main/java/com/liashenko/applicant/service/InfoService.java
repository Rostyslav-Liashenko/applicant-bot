package com.liashenko.applicant.service;

import org.springframework.stereotype.Service;

@Service
public class InfoService {
    public String getConsultationCenterSchedule() {
        return """
                Консультаційний центр працює у період з 1 липня по 10 вересня 2023 року.
                Графік роботи:
                Понеділок – П’ятниця: 09 – 17 год;
                Субота: 09-15 год;Неділя: вихідний
                По питаннях надання консультаційної допомоги по реєстрації кабінету вступника та подачі заяв у неділю прохання узгоджувати таку можливість за номером +380639742405""";
    }
}
