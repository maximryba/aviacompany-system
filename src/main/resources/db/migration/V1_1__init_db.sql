CREATE TYPE if not exists role_enum AS ENUM ('USER', 'ADMIN', 'MANAGER');
CREATE TYPE if not exists state_enum AS ENUM ('CREATED', 'PAID', 'CANCELED', 'ARCHIVED');
CREATE TYPE if not exists seat_type_enum AS ENUM ('SIMPLE', 'BUSINESS');
CREATE TYPE if not exists resource_type_enum AS ENUM ('FUEL', 'SPARES', 'FOOD', 'EQUIPMENT');

create table if not exists users(
    id serail primary key,
    username varchar(30) not null,
    password varchar(30) not null,
    email varchar(50) not null unique,
    phone varchar(10) not null unique,
    CHECK (phone !~ '[a-zA-Z()]'),
    role role_enum not null,
);

create table if not exists buckets(
    id serial primary key,
    user_id int not null,
    sum decimal(10, 2);,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

create table if not exists orders (
    id serial primary key,
    flight_id int not null,
    user_id int int null,
    creation_date timestamp not null,
    state state_enum not null,
    modified_date timestamp,
    CONSTRAINT fk_flight FOREIGN KEY (flight_id) REFERENCES flights(id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

create table if not exists passengers (
    id serial primary key,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    user_id int not null,
    flight_id int int null,
    seat_id int not null,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_flight FOREIGN KEY (flight_id) REFERENCES flights(id) ON DELETE CASCADE,
    CONSTRAINT fk_seat FOREIGN KEY (seat_id) REFERENCES seats(id) ON DELETE CASCADE
);

create table if not exists flights (
    id serial primary key,
    departure_airport int not null,
    arrival_airport int not null,
    departure_time timestamp not null,
    arrival_time timestamp not null,
    cost decimal(10, 2) not null,
    passenger_count int,
    distance int not null,
    flight_duration interval not null,
    airplane_id int not null,
    CONSTRAINT fk_departure_airport FOREIGN KEY (departure_airport) REFERENCES airports(id) ON DELETE CASCADE,
    CONSTRAINT fk_arrival_airport FOREIGN KEY (arrival_airport) REFERENCES airports(id) ON DELETE CASCADE,
    CONSTRAINT fk_airplane FOREIGN KEY (airplane_id) REFERENCES airplanes(id) on delete cascade
);

create table if not exists airports(
    id serial primary key,
    name varchar(30) unique not null,
    city varchar(40) not null,
    country varchar(40) not null,
    goods_service boolean not null default false
);

create table if not exists airplanes (
    id serial primary key,
    fuel_capacity int not null,
    passenger_capacity int not null,
    name varchar(20) not null,
    service_cost int not null
);

create table if not exists seats (
    id serial primary key,
    airplane_id int not null,
    type int not null,
    number int not null,
    CONSTRAINT fk_airplane FOREIGN KEY (airplane_id) REFERENCES airplanes(id) on delete cascade,
    CONSTRAINT fk_type FOREIGN KEY (type) REFERENCES seat_types(id) on delete cascade
);

create table if not exists seat_types(
    id serial primary key,
    type seat_type_enum not null,
    cost_coeff decimal(8, 2) not null
);

create table if not exists employee (
    id serial primary key,
    name varchar(60) not null,
    phone varchar(10) not null unique,
    CHECK (phone !~ '[a-zA-Z()]'),
    salary int not null,
    hire_date timestamp not null,
    modified_date timestamp,
);

create table if not exists flights_employee (
    flight_id int,
    employee_id int,
    primary key(flight_id, employee_id),
    CONSTRAINT fk_flight FOREIGN KEY (flight_id) REFERENCES flights(id),
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);

create table if not exists resources (
    id serial primary key,
    resource_type resource_type_enum not null,
    cost decimal (10, 2) not null,
    flight_id int not null,
    CONSTRAINT fk_flight FOREIGN KEY (flight_id) REFERENCES flight(id) on delete cascade
);

create table if not exists goods(
    id serial primary key,
    goods_type int not null,
    flight_id int not null,
    amount int not null,
    profit int not null,
    CONSTRAINT fk_goods_type FOREIGN KEY (goods_type) REFERENCES goods_types(id) on delete cascade,
    CONSTRAINT fk_flight FOREIGN KEY (flight_id) REFERENCES flight(id) on delete cascade
);

create table if not exists goods_types (
    id serial primary key,
    type varchar(40) not null,
    profit decimal (8, 2) not null
);