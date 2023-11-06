package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.entity.BranchOfKnowledge;
import com.liashenko.applicant.entity.Faculty;
import com.liashenko.applicant.service.FacultyService;
import lombok.Data;

@Data
public class CommandGetSpeciality implements Command {
    private final FacultyService facultyService;
    private final ApplicantBot applicantBot;
    private final Long chatId;

    public CommandGetSpeciality(Long chatId, FacultyService facultyService, ApplicantBot applicantBot) {
        this.facultyService = facultyService;
        this.applicantBot = applicantBot;
        this.chatId = chatId;
    }

    @Override
    public void execute() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterable<Faculty> faculties = this.facultyService.getFaculties();

        for (Faculty faculty: faculties) {
            String name = faculty.getName();

            stringBuilder
                    .append(name)
                    .append("\n");

            Iterable<BranchOfKnowledge> branchOfKnowledges = faculty.getBranchOfKnowledges();

            for (BranchOfKnowledge branchOfKnowledge: branchOfKnowledges) {
                String nameBranchOfKnowledge = branchOfKnowledge.getName();
                stringBuilder
                        .append("--")
                        .append(nameBranchOfKnowledge)
                        .append("\n");
            }
        }

        String outPutText = stringBuilder.toString();

        this.applicantBot.sendMessage(chatId, outPutText);
    }
}
