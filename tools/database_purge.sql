-- Nicolas Mauger
-- database_purge.sql

-- DELETE user with non-correct format email
DELETE  FROM users
        WHERE email NOT LIKE '%@%.%'
;
