CREATE OR REPLACE FUNCTION back.order_cancel_by_timeout() RETURNS text
    LANGUAGE plpgsql
AS $body$
    DECLARE
        timeout INTERVAL;
        v_order back.orders%Rowtype;
        result text;
    BEGIN
        SET TIMEZONE TO 'Europe/Bucharest';

        SELECT (value ->> 'timeout') :: INTERVAL
        INTO timeout
        FROM back.config
        WHERE name = 'order';

        FOR v_order IN
            SELECT id FROM back.orders
            WHERE coalesce(changed, created) + timeout < now()
              AND status = 'REGISTERED'
            LOOP
                UPDATE back.orders
                SET status = 'TIMEOUT_CANCEL',
                    changed = now()
                WHERE id = v_order.id;
                result = concat(result, v_order.id || ',');
            END LOOP;
        result = substring(result, 0, length(result));
    RETURN result;
    END
$body$;