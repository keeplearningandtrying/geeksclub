
DELETE FROM link_tag;
DELETE FROM tags;
DELETE FROM links;
DELETE FROM users;

INSERT INTO users (id, email, password, name, website, bio, role) VALUES
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS','Admin', 'http://techcorner.com', 'I am the creator of TechCorner', 'ROLE_ADMIN'),
(2, 'siva@gmail.com', '$2a$10$UFEPYW7Rx1qZqdHajzOnB.VBR3rvm7OI7uSix4RadfQiNhkZOi2fi','Siva', 'https://sivalabs.in','I am Siva, a passionate software developer, blogger and book author. My current technical focus is on modern architectures including microservices, continuous integration and continuous delivery (CI/CD), and DevOps. My technical skills include Java 8, Kotlin, Groovy, Spring, SpringBoot, Spring Security, Spring Cloud, Hibernate/JPA, MySQL, ElasticSearch, Cassandra, Redis, MongoDB, Kafka, Jenkins, Ansible etc.', 'ROLE_USER')
;

INSERT INTO tags(id, name) VALUES
(1, 'java'),
(2, 'spring'),
(3, 'spring-boot'),
(4, 'spring-cloud'),
(5, 'jpa')
;

INSERT INTO links(id, title, url, created_by, created_on) VALUES
(1, 'Session Management using Spring Session with JDBC DataStore','https://sivalabs.in/2018/02/session-management-using-spring-session-jdbc-datastore/', 2, '2018-03-06'),
(2, 'CI/CD for SpringBoot applications using Travis-CI','https://sivalabs.in/2018/01/ci-cd-springboot-applications-using-travis-ci/', 2, '2018-03-06'),
(3, 'MicroServices – Part 2 : Configuration Management with Spring Cloud Config and Vault','https://sivalabs.in/2018/03/microservices-part-2-configuration-management-spring-cloud-config-vault/', 1, '2018-03-06'),
(4, 'MicroServices using Spring Boot & Spring Cloud – Part 1 : Overview','https://sivalabs.in/2018/03/microservices-using-springboot-spring-cloud-part-1-overview/', 1, '2018-03-06')
;

INSERT INTO link_tag(link_id, tag_id) VALUES
(1,1),
(1,2),
(2,1),
(2,3),
(2,4),
(3,3),
(4,3)
;
