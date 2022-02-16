SELECT host_usage.cpu_number, host_usage.host_id, host_usage.total_mem
GROUP BY host_usage.cpu_number
SORT BY host_usage.total_mem;