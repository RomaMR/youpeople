databaseChangeLog {
    changeSet(id: 'envers_t_volunteering', author: 'odisseus') {
        comment('Added columns for envers changes auditing.')
        addColumn(tableName: 't_volunteering') {
            column(name: 'created_by', type: 'VARCHAR(255)') { constraints(nullable: false) }
            column(name: 'created_date', type: 'TIMESTAMP WITHOUT TIME ZONE') { constraints(nullable: false) }
            column(name: 'last_modified_by', type: 'VARCHAR(255)')
            column(name: 'last_modified_date', type: 'TIMESTAMP WITHOUT TIME ZONE')
        }
    }
}