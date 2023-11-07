package com.liashenko.applicant.command;

import com.liashenko.applicant.bot.ApplicantBot;
import com.liashenko.applicant.entity.BranchOfKnowledge;
import com.liashenko.applicant.entity.EducationProgram;
import com.liashenko.applicant.entity.Faculty;
import com.liashenko.applicant.entity.Speciality;
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
            String infoAboutBranchesOfKnowledges = this.getInfoAboutBranchesOfKnowledge(faculty);

            stringBuilder
                    .append(name)
                    .append("\n")
                    .append(infoAboutBranchesOfKnowledges);
        }

        String outPutText = stringBuilder.toString();

        this.applicantBot.sendMessage(chatId, outPutText);
    }

    private String getInfoAboutBranchesOfKnowledge(Faculty faculty) {
        Iterable<BranchOfKnowledge> branchOfKnowledges = faculty.getBranchOfKnowledges();
        StringBuilder stringBuilder = new StringBuilder();

        for (BranchOfKnowledge branchOfKnowledge: branchOfKnowledges) {
            String nameBranchOfKnowledge = branchOfKnowledge.getName();
            String infoAboutSpeciality = this.getInfoAboutSpeciality(branchOfKnowledge);

            stringBuilder
                    .append("--")
                    .append(nameBranchOfKnowledge)
                    .append("\n")
                    .append(infoAboutSpeciality);
        }

        return stringBuilder.toString();
    }

    private String getInfoAboutSpeciality(BranchOfKnowledge branchOfKnowledge) {
        Iterable<Speciality> specialities = branchOfKnowledge.getSpecialities();
        StringBuilder stringBuilder = new StringBuilder();

        for (Speciality speciality: specialities) {
            String infoAboutEducationProgram = this.getInfoAboutEducationProgram(speciality);

            stringBuilder
                    .append("----")
                    .append(speciality.getName())
                    .append("\n")
                    .append(infoAboutEducationProgram);
        }

        return stringBuilder.toString();
    }

    private String getInfoAboutEducationProgram(Speciality speciality) {
        Iterable<EducationProgram> educationPrograms = speciality.getEducationPrograms();
        StringBuilder stringBuilder = new StringBuilder();

        for (EducationProgram educationProgram: educationPrograms) {
            stringBuilder
                    .append("------")
                    .append(educationProgram.getName())
                    .append("\n");
        }

        return stringBuilder.toString();
    }
}
