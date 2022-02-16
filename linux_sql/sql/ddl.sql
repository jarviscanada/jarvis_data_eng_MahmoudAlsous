CREATE TABLE IF NOT EXISTS PUBLIC.host_info(
   id SERIAL NOT NULL,
   hostname VARCHAR(255) NOT NULL UNIQUE,
   cpu_number int NOT NULL,
   cpu_architecture VARCHAR(255) NOT NULL,
   cpu_model VARCHAR(255) NOT NULL,
   cpu_mhz decimal NOT NULL,
   L2_cache int NOT NULL,
   total_mem int NOT NULL,
   "timestamp" TIMESTAMP NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PUBLIC.host_usage(
     "timestamp" TIMESTAMP NOT NULL,
     host_id SERIAL NOT NULL,
     memory_free int NOT NULL,
     cpu_idle int NOT NULL,
     cpu_kernel int NOT NULL,
     disk_io int NOT NULL,
     disk_available int NOT NULL,
     FOREIGN KEY (host_id) REFERENCES host_info(id)
);


