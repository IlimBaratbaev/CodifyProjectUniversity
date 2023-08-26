create table if not exists public.faculty
(
    id            bigserial primary key,
    faculty_title varchar
);

create table if not exists public.position
(
    id             bigserial primary key,
    position_title varchar
);

create table if not exists public.app_user
(
    id            bigserial primary key,
    user_name     varchar,
    user_surname  varchar,
    user_login    varchar,
    user_password varchar,
    is_deleted    boolean default false
);

create table if not exists public.department
(
    id               bigserial primary key,
    department_title varchar,
    faculty_id       bigint references public.faculty (id)
    );

create table if not exists public.speciality
(
    id               bigserial primary key,
    speciality_title varchar,
    department_id       bigint references public.department (id)
);

create table if not exists public.employee
(
    id               bigserial primary key,
    employee_name    varchar,
    employee_surname varchar,
    department_id    bigint references public.department (id),
    user_id      bigint references app_user (id),
    position_id      bigint unique references position (id),
    is_deleted       boolean default false
);

create table if not exists public.university_group
(
    id            bigserial primary key,
    group_name    varchar,
    department_id bigint references department (id),
    faculty_id    bigint references faculty (id),
    speciality_id    bigint references speciality (id)
);

create table if not exists public.student
(
    id              bigserial primary key,
    student_name    varchar,
    student_surname varchar,
    group_id        bigint references university_group (id),
    user_id     bigint unique references app_user (id),
    faculty_id      bigint references faculty (id),
    department_id   bigint references department (id),
    speciality_id   bigint references speciality (id),
    is_deleted      boolean default false
);

create table if not exists public.app_role
(
    id             bigserial primary key,
    app_role_title varchar
);

create table if not exists public.m2m_user_role
(
    user_id     bigint references public.app_user (id),
    app_role_id bigint references public.app_role (id)
);

create table if not exists public.orders
(
    id      bigserial primary key,
    from_whom bigint references employee(id),
    order_content varchar,
    is_done boolean,
    employee_id bigint references employee(id)
);
create table if not exists public.message
(
    id bigserial primary key,
    message_content varchar,
    title varchar
);

create table if not exists m2m_user_message(
    user_id bigint references app_user(id),
    message_id bigint references message(id)
    );
create table if not exists auditorium(
                                         id bigserial primary key,
                                         number integer,
                                         student_id bigint unique references student(id)
    );
create table if not exists public.equipment
(
    id                  bigserial primary key,
    equipment_title     varchar,
    date_of_receipt     timestamp,
    equipment_condition varchar,
    auditorium_id bigint references auditorium(id)
    );
create table if not exists public.application_form(
                                                      id bigserial primary key,
                                                      content_text varchar,
                                                      user_id bigint references app_user(id),
    faculty_id bigint references faculty(id),
    department_id bigint references department(id),
    speciality_id bigint references speciality(id),
    approved boolean
    );
create table teacher(
                        id bigserial primary key,
                        employee_id bigint unique references employee(id)
);
create table if not exists public.subject
(
    id           bigserial primary key,
    subject_name varchar
);

create table if not exists lesson (
                                      id bigserial primary key,
                                      teacher_id bigint references teacher(id),
    group_id bigint references university_group(id),
    subject_id bigint references subject(id),
    date timestamp,
    lesson_status varchar
    );

create table if not exists student_assignment (
                                                  id bigserial primary key,
                                                  content varchar,
--                                                   mark integer,
                                                  max_mark integer,
                                                  deadline timestamp,
--                                                   task_solution varchar,
--                                                   assignment_sent boolean,
                                                  group_id bigint references university_group(id),
    subject_id bigint references subject(id),
    lesson_id bigint references lesson (id)
    );
create table if not exists finished_assignment
(
    id bigserial primary key,
    solution varchar,
    student_id bigint references student(id),
    assignment_id bigint references student_assignment(id),
    sent_time timestamp
);
create table if not exists mark (
    id bigserial primary key,
    mark integer,
    student_id bigint references student(id),
    subject_id bigint references subject(id),
    finished_assignment_id bigint references finished_assignment(id)
);
create table if not exists public.schedule
(
    id         bigserial primary key,
    lesson_id bigint references lesson (id)
    );
create table if not exists public.lesson_attendance
(
    id bigserial primary key,
    lesson_id bigint references lesson (id),
    student_id bigint references student (id),
    is_present boolean
);
create table if not exists public.m2m_subject_speciality
(
    subject_id    bigint references subject (id),
    speciality_id bigint references speciality (id)
    );
create table if not exists public.m2m_subject_teacher (
    teacher_id bigint references teacher(id),
    subject_id bigint references subject(id)
    );
insert into position (position_title)
values ('SYSTEM ADMINISTRATOR'),
       ('RECTOR'),
       ('RECTORS SECRETARY'),
       ('DEAN'),
       ('DEANS SECRETARY'),
       ('HEAD OF THE DEPARTMENT'),
       ('EMPLOYEE'),
       ('LABORATORY TECHNICIAN'),
       ('TEACHER');

insert into app_role (app_role_title)
values ('ADMIN'),
       ('RECTOR'),
       ('RECTOR_SEC'),
       ('DEAN'),
       ('DEAN_SEC'),
       ('DEPARTMENT_HEAD'),
       ('EMPLOYEE'),
       ('LABORATORIAN'),
       ('STUDENT'),
       ('TEACHER');
insert into app_user(user_name, user_surname, user_login, user_password)
values ('Admin', 'Adminov', 'admin', '$2a$08$SwoH.KLuVJ7JwNE9E2dCt.I6fmsOp/gFFpQYnFumNMEYEJd0dZOhK'),
       ('Rector', 'Rectorov', 'rector', '$2a$08$CWRO5uzasRx37xWmC2jjv.HDcnFU6gtihNoLzep1AGOfhjaep6jtu');

insert into employee (employee_name, employee_surname, user_id, position_id)
values ('Admin', 'Adminov', 1, 1),
       ('Rector', 'Rectorov', 2, 2);

insert into m2m_user_role (user_id, app_role_id)
values (1, 1),
       (1, 7),
       (2, 2),
       (2, 7);
insert into faculty(faculty_title)
values ('Институт информационных технологий');

insert into department(department_title, faculty_id)
values ('Программное обеспечение компьютерных систем', 1),
       ('Прикладная математика и информатика', 1),
       ('Информатика и вычислительная техника', 1);

insert into subject(subject_name)
values ('Математика'),
       ('Информатика'),
       ('Физика');
insert into speciality(speciality_title, department_id)
values ('Инф.Без', 1);
insert into university_group (group_name, department_id, faculty_id, speciality_id)
values ('ИБ-1-23', 1,  1, 1);


