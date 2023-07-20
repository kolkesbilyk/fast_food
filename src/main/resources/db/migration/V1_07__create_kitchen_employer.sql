INSERT INTO back.employer(id, first_name, last_name, login, password, email, author)
VALUES (3, 'kitchen', 'kitchen', 'kitchen',
        'db3222efe460e81977a7d543a12a99fadcc9304b42228c5ce7255666fe41ac2ad50d5a070bfd34b27f262a1583096c941da08872b89055cd67e2ac3a0a7dc57a',
        'kitchen', 1)
ON CONFLICT DO NOTHING ;

INSERT INTO back.employer_roles(role_id, employer_id)
VALUES (4, 3);