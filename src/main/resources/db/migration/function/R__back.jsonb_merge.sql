CREATE OR REPLACE FUNCTION back.jsonb_merge(a jsonb, b jsonb) RETURNS jsonb
    LANGUAGE sql
AS
$$
SELECT JSONB_OBJECT_AGG(
               COALESCE(key_a, key_b),
               CASE
                   WHEN val_a ISNULL
                       THEN val_b
                   WHEN val_b ISNULL
                       THEN val_a
                   WHEN JSONB_TYPEOF(val_a) = 'array' AND JSONB_TYPEOF(val_b) = 'array'
                       THEN val_a || val_b
                   WHEN JSONB_TYPEOF(val_a) <> 'object' OR JSONB_TYPEOF(val_b) <> 'object'
                       THEN val_b
                   ELSE back.jsonb_merge(val_a, val_b) END
           )
FROM JSONB_EACH(a) e1(key_a, val_a)
         FULL JOIN JSONB_EACH(b) e2(key_b, val_b) ON key_a = key_b
WHERE JSONB_TYPEOF(val_b) != 'null'
   OR key_b IS NULL
$$;