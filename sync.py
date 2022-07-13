from rgsync import RGWriteBehind, RGWriteThrough
from rgsync.Connectors import PostgresConnector, PostgresConnection

connection = PostgresConnection( "postgres", "postgres", "postgres:5432/persons")

# 
persons_connector = PostgresConnector( connection, "persons", "person_id")

# Only write through desired fields for archive
persons_Mappings = {
    "first_name": "first_name",
    "last_name": "last_name",
    "age": "age",
}

RGWriteBehind(GB,  keysPrefix='person', mappings=persons_Mappings, connector=persons_connector, name='PersonsWriteBehind',  version='99.99.99')
RGWriteThrough(GB, keysPrefix='__',     mappings=persons_Mappings, connector=persons_connector, name='PersonsWriteThrough', version='99.99.99')

