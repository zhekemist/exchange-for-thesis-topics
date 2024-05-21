CREATE DATABASE IF NOT EXISTS topic_exchange;

USE topic_exchange;

CREATE TABLE IF NOT EXISTS user
(
    user_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_type  ENUM ('STUDENT', 'INSTRUCTOR') NOT NULL,
    username   TEXT                           NOT NULL,
    first_name TEXT                           NOT NULL,
    last_name  TEXT                           NOT NULL,
    email      TEXT                           NOT NULL,
    password   TEXT                           NOT NULL
);

CREATE TABLE IF NOT EXISTS research_group
(
    group_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             TEXT NOT NULL,
    research_profile TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS instructor
(
    user_id             BIGINT PRIMARY KEY,
    contact_information TEXT    NOT NULL,
    is_administrator    BOOLEAN NOT NULL DEFAULT false,
    group_id            BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES research_group (group_id)
);

CREATE TABLE IF NOT EXISTS student
(
    user_id              BIGINT PRIMARY KEY,
    study_program        TEXT NOT NULL,
    matriculation_number INT  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS thesis_topic
(
    topic_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         TEXT   NOT NULL,
    description   TEXT   NOT NULL,
    supervisor_id BIGINT NOT NULL,
    FOREIGN KEY (supervisor_id) REFERENCES instructor (user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS literature_reference
(
    reference_number BIGINT,
    year             INT    NOT NULL,
    title            TEXT   NOT NULL,
    link             TEXT   NOT NULL,
    author           TEXT   NOT NULL,
    topic_id         BIGINT NOT NULL,
    PRIMARY KEY (topic_id, reference_number),
    FOREIGN KEY (topic_id) REFERENCES thesis_topic (topic_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS category
(
    category_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name              TEXT NOT NULL,
    short_description TEXT NOT NULL,
    supercategory_id  BIGINT,
    FOREIGN KEY (supercategory_id) REFERENCES category (category_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bookmarked
(
    user_id  BIGINT,
    topic_id BIGINT,
    PRIMARY KEY (user_id, topic_id),
    FOREIGN KEY (user_id) REFERENCES student (user_id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES thesis_topic (topic_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS topic_choice
(
    user_id         BIGINT,
    topic_id        BIGINT,
    timestamp       TIMESTAMP NOT NULL,
    priority_points INT       NOT NULL,
    PRIMARY KEY (user_id, topic_id),
    FOREIGN KEY (user_id) REFERENCES student (user_id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES thesis_topic (topic_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS assigned_to
(
    user_id  BIGINT UNIQUE,
    topic_id BIGINT UNIQUE,
    reason   TEXT NOT NULL,
    PRIMARY KEY (user_id, topic_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES thesis_topic (topic_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS belongs_to
(
    category_id BIGINT,
    topic_id    BIGINT,
    PRIMARY KEY (category_id, topic_id),
    FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE,
    FOREIGN KEY (topic_id) REFERENCES thesis_topic (topic_id) ON DELETE CASCADE
);




