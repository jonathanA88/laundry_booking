CREATE TABLE booking(
    id INTEGER PRIMARY KEY,
    household_id INTEGER,
    laundry_room_id INTEGER,
    date DATE,
    hour_slot INTEGER
);

CREATE TABLE laundry_room(
    id INTEGER PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE household(
    id INTEGER PRIMARY KEY,
    apartment_number INTEGER,
    owner_name VARCHAR(50)
);