--Grouping hosts by CPU number and sorting them by their total memory size in descending order
SELECT cpu_number, id, total_mem FROM host_info GROUP BY id ORDER BY COUNT(total_mem) DESC;

--Averaging the memory usage in percentage over 5 minute intervals per host
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp
LANGUAGE PLPGSQL
AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$;

CREATE FUNCTION avgp(total decimal,free decimal ) RETURNS decimal
LANGUAGE PLPGSQL
AS
$$
BEGIN
    RETURN ROUND((total/1024.0 -free)/(total/1024.0)*100);
END;
$$;


SELECT host_id,hi.hostname, round5(hu.timestamp) as timestamp,avgp(total_mem,memory_free) as avg_used_mem_percentage
FROM host_info hi
INNER JOIN host_usage hu
ON hi.id=hu.host_id
GROUP BY hu.timestamp, host_id, hi.hostname,total_mem,memory_free;

-- Detecting host failure
SELECT hu.host_id, round5(hu.timestamp), count(round5(hu.timestamp)) AS num_data_points
FROM host_usage hu
INNER JOIN host_info hi
ON hu.host_id = hi.id
GROUP BY hu.host_id, round5
HAVING COUNT(round5(hu.timestamp)) < 3
ORDER BY round5;