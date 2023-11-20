CREATE TABLE admission_rules
(
    id           UUID NOT NULL,
    path_to_file VARCHAR(255),
    description  VARCHAR(255),
    CONSTRAINT pk_admission_rules PRIMARY KEY (id)
);

CREATE TABLE branch_of_knowledges
(
    id         UUID    NOT NULL,
    name       VARCHAR(255),
    code       INTEGER NOT NULL,
    faculty_id UUID    NOT NULL,
    CONSTRAINT pk_branch_of_knowledges PRIMARY KEY (id)
);

CREATE TABLE cost_education
(
    id             UUID NOT NULL,
    path_to_file   VARCHAR(255),
    description    VARCHAR(255),
    time_relevance TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_cost_education PRIMARY KEY (id)
);

CREATE TABLE document_admissions
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_document_admissions PRIMARY KEY (id)
);

CREATE TABLE education_programs
(
    id            UUID NOT NULL,
    name          VARCHAR(255),
    speciality_id UUID,
    CONSTRAINT pk_education_programs PRIMARY KEY (id)
);

CREATE TABLE faculties
(
    id   UUID NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_faculties PRIMARY KEY (id)
);

CREATE TABLE open_days
(
    id          UUID NOT NULL,
    date        TIMESTAMP WITHOUT TIME ZONE,
    description VARCHAR(255),
    CONSTRAINT pk_open_days PRIMARY KEY (id)
);

CREATE TABLE specialties
(
    id                      UUID    NOT NULL,
    name                    VARCHAR(255),
    code                    INTEGER NOT NULL,
    branch_of_knowledges_id UUID    NOT NULL,
    CONSTRAINT pk_specialties PRIMARY KEY (id)
);

CREATE TABLE user_settings
(
    id                   UUID    NOT NULL,
    is_show_notification BOOLEAN NOT NULL,
    user_id              UUID,
    CONSTRAINT pk_user_settings PRIMARY KEY (id)
);

CREATE TABLE users
(
    id              UUID NOT NULL,
    chat_id         VARCHAR(255),
    fist_name       VARCHAR(255),
    last_name       VARCHAR(255),
    user_setting_id UUID,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE branch_of_knowledges
    ADD CONSTRAINT FK_BRANCH_OF_KNOWLEDGES_ON_FACULTY FOREIGN KEY (faculty_id) REFERENCES faculties (id);

ALTER TABLE education_programs
    ADD CONSTRAINT FK_EDUCATION_PROGRAMS_ON_SPECIALITY FOREIGN KEY (speciality_id) REFERENCES specialties (id);

ALTER TABLE specialties
    ADD CONSTRAINT FK_SPECIALTIES_ON_BRANCH_OF_KNOWLEDGES FOREIGN KEY (branch_of_knowledges_id) REFERENCES branch_of_knowledges (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_USERSETTING FOREIGN KEY (user_setting_id) REFERENCES user_settings (id);

ALTER TABLE user_settings
    ADD CONSTRAINT FK_USER_SETTINGS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
