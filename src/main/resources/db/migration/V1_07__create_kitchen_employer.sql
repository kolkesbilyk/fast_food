INSERT INTO back.employer(id, first_name, last_name, login, password, email, author)
VALUES (3, 'kitchen', 'kitchen', 'kitchen', 'kitchen', 'kitchen', 1)
ON CONFLICT DO NOTHING ;

INSERT INTO back.employer_roles(role_id, employer_id)
VALUES (4, 3);