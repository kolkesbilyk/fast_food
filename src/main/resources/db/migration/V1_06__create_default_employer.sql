INSERT INTO back.employer(id, first_name, last_name, login, password, email, author)
VALUES (2, 'default', 'default', 'demo', 'demo', 'default', 1)
ON CONFLICT DO NOTHING ;

INSERT INTO back.dir_roles(name, description)
VALUES ('DEFAULT', 'звичайний користувач');

INSERT INTO back.employer_roles(role_id, employer_id)
VALUES (5, 2);