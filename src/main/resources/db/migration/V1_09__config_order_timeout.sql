INSERT INTO back.config as c(name, value)
VALUES ('order', '{
    "timeout": "5 minutes"
}' :: jsonb)
ON CONFLICT (name) DO UPDATE
    SET value = back.jsonb_merge(c.value, excluded.value);